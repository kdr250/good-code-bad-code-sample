package com.example.sample.presentation.worldmap;

import com.example.sample.application.service.EnemyQueryService;
import com.example.sample.application.service.ItemQueryService;
import com.example.sample.application.service.NpcQueryService;
import com.example.sample.application.service.PlayerQueryService;
import com.example.sample.application.service.WorldMapQueryService;
import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Npc;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.Vector;
import com.example.sample.domain.model.WorldMap;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.domain.model.item.Interactive;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.presentation.GamePanel;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.battle.BattleController;
import com.example.sample.presentation.itemlist.ItemListController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class WorldMapController {

  private final ApplicationContext applicationContext;
  private final WorldMapQueryService worldMapQueryService;
  private final PlayerQueryService playerQueryService;
  private final NpcQueryService npcQueryService;
  private final EnemyQueryService enemyQueryService;
  private final ItemQueryService itemQueryService;

  private WorldMap worldMap;
  private Player player;
  private List<Npc> npcList;
  private List<Enemy> enemyList;
  private List<Item> fieldItemList;

  private PlayerStatusView playerStatusView;

  private Location playerStartLocation;
  private GameMode gameMode;

  public void setUp(GameMode gameMode) {
    worldMap = worldMapQueryService.find();
    player = playerQueryService.find();
    npcList = npcQueryService.find();
    enemyList = enemyQueryService.find();
    fieldItemList = itemQueryService.find();

    ItemImage itemImageCrystalBlank = itemQueryService.findImage(ItemType.CRYSTAL_BLANK);
    ItemImage itemImageCrystalFull = itemQueryService.findImage(ItemType.CRYSTAL_FULL);
    playerStatusView = new PlayerStatusView(player, itemImageCrystalBlank, itemImageCrystalFull);

    playerStartLocation = player.getLocation();
    this.gameMode = gameMode;
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (keyInputType == KeyInputType.DISPLAY_ITEM_LIST) {
      gameMode.displayItemList();
      ItemListController itemListController = applicationContext.getBean(ItemListController.class);
      itemListController.setUp();
      return;
    }

    Vector vector = keyInputType.getVector();
    Location playerWillMoveLocation = player.getLocation().shift(vector);

    for (Item item : fieldItemList) {
      if (item.getCollision().isCollide(player.getCollision().shift(vector))) {
        if (item instanceof Interactive) {
          Event event = ((Interactive) item).interact();
          if (event instanceof GameModeEvent) {
            ((GameModeEvent) event).execute(gameMode);
            return;
          }
          if (event instanceof PlayerEvent) {
            if (((PlayerEvent) event).execute(player)) {
              fieldItemList.remove(item);
            }
          }
        } else {
          player.pickUp(item);
          fieldItemList.remove(item);
        }
        break;
      }
    }

    List<Collidable> collidableListForPlayer = worldMap.getTilesFromLocation(playerWillMoveLocation);
    collidableListForPlayer.addAll(npcList);
    collidableListForPlayer.addAll(fieldItemList.stream().filter(Interactive.class::isInstance).collect(Collectors.toList()));
    if (player.canMove(collidableListForPlayer, vector)) {
      player.move(vector);
    } else {
      player.changeDirection(vector);
    }
    for (Enemy enemy : enemyList) {
      if (player.isOverlap(enemy)) {
        gameMode.battle();
        BattleController battleController = applicationContext.getBean(BattleController.class);
        battleController.setUp(player, enemy, playerStatusView.getCrystalBlank(), playerStatusView.getCrystalFull());
      }
    }

    for (Npc npc : npcList) {
      Location npcWillMoveLocation = npc.getLocation().shift(npc.getNpcMovement().getVector());
      List<Collidable> collidableListForNpc = worldMap.getTilesFromLocation(npcWillMoveLocation);
      collidableListForNpc.add(player);
      collidableListForPlayer.addAll(enemyList);
      if (npc.updateMovementThenCanMove(collidableListForNpc)) {
        npc.move();
      } else {
        npc.changeDirection();
      }
    }

    for (Enemy enemy : enemyList) {
      Location enemyWillMoveLocation = enemy.getLocation().shift(enemy.getEnemyMovement().getVector());
      List<Collidable> collidableListForEnemy = worldMap.getTilesFromLocation(enemyWillMoveLocation);
      collidableListForEnemy.addAll(npcList);
      if (enemy.updateMovementThenCanMove(collidableListForEnemy)) {
        enemy.move();
      }
      if (player.isOverlap(enemy)) {
        gameMode.battle();
        BattleController battleController = applicationContext.getBean(BattleController.class);
        battleController.setUp(player, enemy, playerStatusView.getCrystalBlank(), playerStatusView.getCrystalFull());
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || worldMap == null) return;

    for (Tile[] tiles : worldMap.getTiles()) {
      for (Tile tile : tiles) {
        Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(tile.getLocation());
        if (result.getLeft()) {
          g2.drawImage(tile.getBufferedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
        }
      }
    }

    g2.drawImage(player.getAnimatedImage(), GamePanel.screenCenterX, GamePanel.screenCenterY, null);


    for (Npc npc : npcList) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(npc.getLocation());
      if (result.getLeft()) {
        g2.drawImage(npc.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Enemy enemy : enemyList) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(enemy.getLocation());
      if (result.getLeft()) {
        g2.drawImage(enemy.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Item item : fieldItemList) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(item.getLocation());
      if (result.getLeft()) {
        g2.drawImage(item.getImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    if (gameMode.getGameModeType() == GameModeType.WORLD_MAP) {
      playerStatusView.draw(g2);
    }
  }

  public Player getPlayer() {
    return player;
  }

  public void removeEnemy(Enemy enemy) {
    enemyList.remove(enemy);
  }

  public void restart() {
    player.warp(playerStartLocation);
    player.recoverHitPointMax();
    player.recoverMagicPointMax();
    enemyList = enemyQueryService.find();
    fieldItemList = itemQueryService.find();
  }

  private Triple<Boolean, Integer, Integer> canDisplayAndDifferenceFromPlayer(Location location) {
    Location playerLocation = player.getLocation();
    int diffX = location.getX() - playerLocation.getX();
    int diffY = location.getY() - playerLocation.getY();
    boolean canDisplay = Math.abs(diffX) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
      Math.abs(diffY) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE;

    return Triple.of(canDisplay, diffX, diffY);
  }
}
