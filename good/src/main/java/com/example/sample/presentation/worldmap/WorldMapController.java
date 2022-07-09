package com.example.sample.presentation.worldmap;

import com.example.sample.application.service.enemy.EnemyDomainService;
import com.example.sample.application.service.enemy.EnemyQueryService;
import com.example.sample.application.service.interactive.InteractiveDomainService;
import com.example.sample.application.service.interactive.InteractiveQueryService;
import com.example.sample.application.service.item.ItemDomainService;
import com.example.sample.application.service.item.ItemQueryService;
import com.example.sample.application.service.npc.NpcDomainService;
import com.example.sample.application.service.npc.NpcQueryService;
import com.example.sample.application.service.player.PlayerDomainService;
import com.example.sample.application.service.player.PlayerQueryService;
import com.example.sample.application.service.worldmap.WorldMapQueryService;
import com.example.sample.domain.model.interactive.Interactions;
import com.example.sample.domain.model.interactive.Interactive;
import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.character.enemy.Enemies;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.item.Items;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.character.npc.Npc;
import com.example.sample.domain.model.character.npc.Npcs;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.worldmap.Vector;
import com.example.sample.domain.model.worldmap.WorldMap;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.presentation.GamePanel;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.battle.BattleController;
import com.example.sample.presentation.itemlist.ItemListController;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
  private final InteractiveQueryService interactiveQueryService;
  private final InteractiveDomainService interactiveDomainService;

  private WorldMap worldMap;
  private Player player;
  private Npcs npcs;
  private Enemies enemies;
  private Items items;
  private Interactions interactions;

  private PlayerStatusView playerStatusView;

  private Location playerStartLocation;
  private GameMode gameMode;

  public WorldMapController(ApplicationContext applicationContext, WorldMapQueryService worldMapQueryService, PlayerQueryService playerQueryService, PlayerDomainService playerDomainService, NpcQueryService npcQueryService, NpcDomainService npcDomainService, EnemyQueryService enemyQueryService, EnemyDomainService enemyDomainService, ItemQueryService itemQueryService, ItemDomainService itemDomainService, InteractiveQueryService interactiveQueryService, InteractiveDomainService interactiveDomainService) {
    this.applicationContext = applicationContext;
    this.worldMapQueryService = worldMapQueryService;
    this.playerQueryService = playerQueryService;
    this.playerDomainService = playerDomainService;
    this.npcQueryService = npcQueryService;
    this.npcDomainService = npcDomainService;
    this.enemyQueryService = enemyQueryService;
    this.enemyDomainService = enemyDomainService;
    this.itemQueryService = itemQueryService;
    this.itemDomainService = itemDomainService;
    this.interactiveQueryService = interactiveQueryService;
    this.interactiveDomainService = interactiveDomainService;
  }

  public void setUp(GameMode gameMode) {
    worldMap = worldMapQueryService.find();
    player = playerQueryService.find();
    npcs = npcQueryService.find();
    enemies = enemyQueryService.find();
    items = itemQueryService.find();
    interactions = interactiveQueryService.find();

    ItemImage itemImageCrystalBlank = itemQueryService.findImage(ItemType.CRYSTAL_BLANK);
    ItemImage itemImageCrystalFull = itemQueryService.findImage(ItemType.CRYSTAL_FULL);
    playerStatusView = new PlayerStatusView(player, itemImageCrystalBlank, itemImageCrystalFull);

    playerStartLocation = player.location();
    this.gameMode = gameMode;
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (keyInputType == KeyInputType.DISPLAY_ITEM_LIST) {
      gameMode.changeToDisplayItemList();
      ItemListController itemListController = applicationContext.getBean(ItemListController.class);
      itemListController.setUp();
      return;
    }

    Vector vector = keyInputType.getVector();
    List<Collidable> collidableList = createCollidableList(player.location().shift(vector));

    // プレイヤー
    playerDomainService.move(player, collidableList, vector);

    // アイテム
    itemDomainService.pickedUpOrInteract(items, player, gameMode);

    // 相互作用トリガー
    interactiveDomainService.interact(interactions, player, gameMode);

    // NPC
    for (Npc npc : npcs.npcs()) {
      List<Collidable> collidableListForNpc = createCollidableList(npc.location().shift(npc.movingVector()));
      npcDomainService.move(npc, collidableListForNpc);
    }

    // 敵
    for (Enemy enemy : enemies.enemies()) {
      List<Collidable> collidableListForEnemy = createCollidableList(enemy.location().shift(enemy.movingVector()));
      enemyDomainService.move(enemy, collidableListForEnemy);

      if (player.isOverlap(enemy)) {
        gameMode.changeToBattle();
        BattleController battleController = applicationContext.getBean(BattleController.class);
        battleController.setUp(player, enemy, playerStatusView.getCrystalBlank(), playerStatusView.getCrystalFull());
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || worldMap == null) return;

    for (Tile[] tiles : worldMap.tiles()) {
      for (Tile tile : tiles) {
        Triple<Boolean, Integer, Integer> result = canDisplayAndDistanceFromPlayer(tile.location(), player.location());
        if (Boolean.TRUE.equals(result.getLeft())) {
          g2.drawImage(tile.getBufferedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
        }
      }
    }

    g2.drawImage(player.getAnimatedImage(), GamePanel.screenCenterX, GamePanel.screenCenterY, null);

    for (Npc npc : npcs.npcs()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDistanceFromPlayer(npc.location(), player.location());
      if (Boolean.TRUE.equals(result.getLeft())) {
        g2.drawImage(npc.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Enemy enemy : enemies.enemies()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDistanceFromPlayer(enemy.location(), player.location());
      if (Boolean.TRUE.equals(result.getLeft())) {
        g2.drawImage(enemy.getAnimatedImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Item item : items.items()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDistanceFromPlayer(item.location(), player.location());
      if (Boolean.TRUE.equals(result.getLeft())) {
        g2.drawImage(item.getImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    for (Interactive interactive : interactions.interactions()) {
      Triple<Boolean, Integer, Integer> result = canDisplayAndDistanceFromPlayer(interactive.location(), player.location());
      if (Boolean.TRUE.equals(result.getLeft())) {
        g2.drawImage(interactive.getImage(), GamePanel.screenCenterX + result.getMiddle(), GamePanel.screenCenterY + result.getRight(), null);
      }
    }

    if (gameMode.gameModeType() == GameModeType.WORLD_MAP) {
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

  private Triple<Boolean, Integer, Integer> canDisplayAndDistanceFromPlayer(Location location, Location playerLocation) {
    int distanceX = location.getX() - playerLocation.getX();
    int distanceY = location.getY() - playerLocation.getY();
    boolean canDisplay = Math.abs(distanceX) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
      Math.abs(distanceY) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE;

    return Triple.of(canDisplay, distanceX, distanceY);
  }

  private List<Collidable> createCollidableList(Location willMoveLocation) {
    List<Collidable> collidableList = new ArrayList<>();
    collidableList.add(player);
    collidableList.addAll(worldMap.getTilesFromLocation(willMoveLocation));
    collidableList.addAll(enemies.enemies());
    collidableList.addAll(npcs.npcs());
    collidableList.addAll(items.items());
    collidableList.addAll(interactions.interactions());
    return collidableList;
  }
}
