package com.example.sample.presentation.worldmap;

import com.example.sample.application.service.EnemyDomainService;
import com.example.sample.application.service.EnemyQueryService;
import com.example.sample.application.service.ItemDomainService;
import com.example.sample.application.service.ItemQueryService;
import com.example.sample.application.service.NpcDomainService;
import com.example.sample.application.service.NpcQueryService;
import com.example.sample.application.service.PlayerDomainService;
import com.example.sample.application.service.PlayerQueryService;
import com.example.sample.application.service.WorldMapQueryService;
import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Enemies;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Items;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Npc;
import com.example.sample.domain.model.Npcs;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.Vector;
import com.example.sample.domain.model.WorldMap;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
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
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WorldMapController {

  private final ApplicationContext applicationContext;
  private final WorldMapQueryService worldMapQueryService;
  private final PlayerQueryService playerQueryService;
  private final PlayerDomainService playerDomainService;
  private final NpcQueryService npcQueryService;
  private final NpcDomainService npcDomainService;
  private final EnemyQueryService enemyQueryService;
  private final EnemyDomainService enemyDomainService;
  private final ItemQueryService itemQueryService;
  private final ItemDomainService itemDomainService;

  private WorldMap worldMap;
  private Player player;
  private Npcs npcs;
  private Enemies enemies;
  private Items items;

  private PlayerStatusView playerStatusView;

  private Location playerStartLocation;
  private GameMode gameMode;

  public void setUp(GameMode gameMode) {
    worldMap = worldMapQueryService.find();
    player = playerQueryService.find();
    npcs = npcQueryService.find();
    enemies = enemyQueryService.find();
    items = itemQueryService.find();

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
    List<Collidable> collidableList = createCollidableList(player.getLocation().shift(vector));

    // プレイヤー
    playerDomainService.move(player, collidableList, vector);

    // アイテム
    itemDomainService.pickedUpOrInteract(items, player, gameMode);

    // NPC
    for (Npc npc : npcs.npcs()) {
      List<Collidable> collidableListForNpc = createCollidableList(npc.getLocation().shift(npc.getNpcMovement().getVector()));
      npcDomainService.move(npc, collidableListForNpc);
    }

    // 敵
    for (Enemy enemy : enemies.enemies()) {
      List<Collidable> collidableListForEnemy = createCollidableList(enemy.getLocation().shift(enemy.getEnemyMovement().getVector()));
      enemyDomainService.move(enemy, collidableListForEnemy, player, gameMode);

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
        Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(tile.getLocation(), player.getLocation());
        if (result.getLeft()) {
          g2.drawImage(tile.getBufferedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
        }
      }
    }

    g2.drawImage(player.getAnimatedImage(), GamePanel.screenCenterX, GamePanel.screenCenterY, null);

    for (Npc npc : npcs.npcs()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(npc.getLocation(), player.getLocation());
      if (result.getLeft()) {
        g2.drawImage(npc.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Enemy enemy : enemies.enemies()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(enemy.getLocation(), player.getLocation());
      if (result.getLeft()) {
        g2.drawImage(enemy.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Item item : items.items()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDifferenceFromPlayer(item.getLocation(), player.getLocation());
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
    enemies.remove(enemy);
  }

  public void reset() {
    player.warp(playerStartLocation);
    player.recoverHitPointMax();
    player.recoverMagicPointMax();
    enemies = enemyQueryService.find();
    items = itemQueryService.find();
  }

  private Triple<Boolean, Integer, Integer> canDisplayAndDifferenceFromPlayer(Location location, Location playerLocation) {
    int diffX = location.getX() - playerLocation.getX();
    int diffY = location.getY() - playerLocation.getY();
    boolean canDisplay = Math.abs(diffX) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
      Math.abs(diffY) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE;

    return Triple.of(canDisplay, diffX, diffY);
  }

  private List<Collidable> createCollidableList(Location willMoveLocation) {
    List<Collidable> collidableList = new ArrayList<>();
    collidableList.add(player);
    collidableList.addAll(worldMap.getTilesFromLocation(willMoveLocation));
    collidableList.addAll(enemies.enemies());
    collidableList.addAll(npcs.npcs());
    collidableList.addAll(items.items());
    return collidableList;
  }
}
