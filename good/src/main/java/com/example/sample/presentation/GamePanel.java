package com.example.sample.presentation;

import com.example.sample.application.service.EnemyQueryService;
import com.example.sample.application.service.ItemQueryService;
import com.example.sample.application.service.NpcQueryService;
import com.example.sample.application.service.PlayerQueryService;
import com.example.sample.application.service.WorldMapQueryService;
import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.EnemyAnimation;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Npc;
import com.example.sample.domain.model.NpcAnimation;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.PlayerAnimation;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.Vector;
import com.example.sample.domain.model.WorldMap;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.battle.EnemyBattleStatus;
import com.example.sample.domain.model.battle.HitPoint;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.domain.model.item.Consumable;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.Interactive;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemChest;
import com.example.sample.domain.model.item.ItemDoor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemShieldNormal;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.item.ItemPotionRed;
import com.example.sample.domain.model.item.ItemWeapon;
import com.example.sample.presentation.view.BattleView;
import com.example.sample.presentation.view.ItemListView;
import com.example.sample.presentation.view.ItemListViewChoice;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GamePanel extends JPanel implements Runnable {

  // スクリーン設定
  private static final int maxScreenCol = 16;
  private static final int maxScreenRow = 12;
  public static final int screenWidth = Tile.TILE_SIZE * maxScreenCol; // 768 px
  public static final int screenHeight = Tile.TILE_SIZE * maxScreenRow; // 576 px
  private static final int screenCenterX = screenWidth / 2;
  private static final int screenCenterY = screenHeight / 2;

  // FPS設定
  private static final int FPS = 60;
  private static final double DRAW_INTERVAL = 1000000000 / FPS;

  private Thread gameThread;

  private final KeyInputHandler keyInputHandler;

  // TODO: 動作確認用、後でリファクタリングすること
  private Player player;
  private final PlayerQueryService playerQueryService;
  private Npc npc;
  private final NpcQueryService npcQueryService;
  private Enemy enemy;
  private final EnemyQueryService enemyQueryService;
  private List<Item> fieldItemList = new ArrayList<>();
  private final ItemQueryService itemQueryService;

  // TODO: Serviceの動作確認用、後でリファクタリングすること
  private final WorldMapQueryService worldMapQueryService;
  private WorldMap worldMap;

  private boolean isFinished = false;

  private final GameMode gameMode = new GameMode(GameModeType.WORLD_MAP);
  Font arial30 = new Font("Arial", Font.PLAIN, 30);

  // TODO: Viewの動作確認用、リファクタリングすること
  private ItemListView itemListView;
  private BattleView battleView;

  public GamePanel(WorldMapQueryService worldMapQueryService, KeyInputHandler keyInputHandler, PlayerQueryService playerQueryService, NpcQueryService npcQueryService, EnemyQueryService enemyQueryService, ItemQueryService itemQueryService) {
    this.worldMapQueryService = worldMapQueryService;
    this.keyInputHandler = keyInputHandler;
    this.playerQueryService = playerQueryService;
    this.npcQueryService = npcQueryService;
    this.enemyQueryService = enemyQueryService;
    this.itemQueryService = itemQueryService;
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyInputHandler);
    this.setFocusable(true);
  }

  public void startGameThread() {
    // TODO: 動作確認用、後でリファクタリングすること
    worldMap = this.worldMapQueryService.find();
    PlayerAnimation playerAnimation = playerQueryService.find();
    player = new Player(new Location(Tile.TILE_SIZE * 23, Tile.TILE_SIZE * 21), playerAnimation);
    NpcAnimation npcAnimation = npcQueryService.find();
    npc = new Npc(new Location(Tile.TILE_SIZE * 21, Tile.TILE_SIZE * 20), npcAnimation);
    EnemyAnimation enemyAnimation = enemyQueryService.find();
    enemy = new Enemy("スライム", new Location(Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 30), enemyAnimation, new EnemyBattleStatus(new HitPoint(6), new AttackPower(2)));
    ItemImage itemImage = itemQueryService.find(ItemType.POTION_RED);
    Item potionRed = new ItemPotionRed(new Location(Tile.TILE_SIZE * 22, Tile.TILE_SIZE * 7), itemImage);
    fieldItemList.add(potionRed);
    ItemImage itemImageKey = itemQueryService.find(ItemType.KEY);
    Item key = new ItemKey(new Location(Tile.TILE_SIZE * 22, Tile.TILE_SIZE * 9), itemImageKey);
    fieldItemList.add(key);
    ItemImage itemImageDoor = itemQueryService.find(ItemType.DOOR);
    Item door = new ItemDoor(new Location(Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 11), itemImageDoor);
    fieldItemList.add(door);
    ItemImage itemImageChest = itemQueryService.find(ItemType.CHEST);
    Item chest = new ItemChest(new Location(Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 7), itemImageChest);
    fieldItemList.add(chest);
    ItemImage itemImageShield = itemQueryService.find(ItemType.SHIELD_NORMAL);
    Item shieldNormal = new ItemShieldNormal(new DefensePower(2), new Location(Tile.TILE_SIZE * 23, Tile.TILE_SIZE * 7), itemImageShield);
    fieldItemList.add(shieldNormal);
    ItemImage itemImageSword = itemQueryService.find(ItemType.WEAPON);
    Item sword = new ItemWeapon(new AttackPower(2), new Location(Tile.TILE_SIZE * 24, Tile.TILE_SIZE * 7), itemImageSword);
    fieldItemList.add(sword);

    itemListView = new ItemListView(player);

    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / DRAW_INTERVAL;
      lastTime = currentTime;

      // TODO: 要リファクタリング
      if (!isFinished) {
        update();
        isFinished = true;
      }

      if (delta >= 1) {
        repaint();
        delta--;
        isFinished = false;
      }
    }
  }

  private void update() {
    KeyInputType keyInputType = keyInputHandler.getKeyInputType();

    // TODO: 動作確認用、後でリファクタリングすること

    if (gameMode.isDisplayingItemList()) {
      if (keyInputType == KeyInputType.DECIDE) {
        ItemListViewChoice choice = itemListView.choice();
        if (choice == ItemListViewChoice.BACK) {
          gameMode.worldMap();
          return;
        }
        Item item = itemListView.selectingItem();
        if (item instanceof Consumable) {
          Event event = ((Consumable) item).consume();
          if (event instanceof PlayerEvent) {
            ((PlayerEvent) event).execute(player);
          }
        }
        if (item instanceof Equipment) {
          Event event = ((Equipment) item).equip();
          if (event instanceof PlayerEvent) {
            ((PlayerEvent) event).execute(player);
          }
        }
        return;
      }
      itemListView.moveCursor(keyInputType);
      return;
    }

    if (gameMode.isGameCleared()) {
      if (keyInputHandler.getKeyInputType() == KeyInputType.DECIDE) {
        System.exit(0);
      }
      return;
    }

    if (gameMode.isWorldMap()) {
      if (keyInputType == KeyInputType.DISPLAY_ITEM_LIST) {
        gameMode.displayItemList();
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
      collidableListForPlayer.add(npc);
      collidableListForPlayer.addAll(fieldItemList.stream().filter(item -> item instanceof Interactive).collect(Collectors.toList()));
      if (player.canMove(collidableListForPlayer, vector)) {
        player.move(vector);
      } else {
        player.changeDirection(vector);
      }
      if (player.isOverlap(enemy)) {
        battleView = new BattleView(player, enemy);
        gameMode.battle();
      }

      Location npcWillMoveLocation = npc.getLocation().shift(npc.getNpcMovement().getVector());
      List<Collidable> collidableListForNpc = worldMap.getTilesFromLocation(npcWillMoveLocation);
      collidableListForNpc.add(player);
      collidableListForPlayer.add(enemy);
      if (npc.updateMovementThenCanMove(collidableListForNpc)) {
        npc.move();
      } else {
        npc.changeDirection();
      }

      Location enemyWillMoveLocation = enemy.getLocation().shift(enemy.getEnemyMovement().getVector());
      List<Collidable> collidableListForEnemy = worldMap.getTilesFromLocation(enemyWillMoveLocation);
      collidableListForEnemy.add(npc);
      if (enemy.updateMovementThenCanMove(collidableListForEnemy)) {
        enemy.move();
      }
      if (player.isOverlap(enemy)) {
        gameMode.battle();
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setFont(arial30);
    g2.setColor(Color.white);

    // TODO: 動作確認用、後でリファクタリングすること

    if (worldMap != null && player != null) {
      Location playerLocation = player.getLocation();
      for (Tile[] tiles : worldMap.getTiles()) {
        for (Tile tile : tiles) {
          Location tileLocation = tile.getLocation();
          int diffX = tileLocation.getX() - playerLocation.getX();
          int diffY = tileLocation.getY() - playerLocation.getY();
          if (
            Math.abs(diffX) <= screenWidth / 2 + Tile.TILE_SIZE &&
            Math.abs(diffY) <= screenHeight / 2 + Tile.TILE_SIZE
          ) {
            g2.drawImage(tile.getBufferedImage(), screenCenterX + diffX, screenCenterY + diffY, null);
          }
        }
      }
      g2.drawImage(player.getAnimatedImage(), screenCenterX, screenCenterY, null);

      if (npc != null) {
        Location npcLocation = npc.getLocation();
        int diffX = npcLocation.getX() - playerLocation.getX();
        int diffY = npcLocation.getY() - playerLocation.getY();
        if (
            Math.abs(diffX) <= screenWidth / 2 + Tile.TILE_SIZE &&
            Math.abs(diffY) <= screenHeight / 2 + Tile.TILE_SIZE
        ) {
          g2.drawImage(npc.getAnimatedImage(), screenCenterX + diffX, screenCenterY + diffY, null);
        }

        if (enemy != null) {
          Location enemyLocation = enemy.getLocation();
          int diffXForEnemy = enemyLocation.getX() - playerLocation.getX();
          int diffYForEnemy = enemyLocation.getY() - playerLocation.getY();
          if (
              Math.abs(diffXForEnemy) <= screenWidth / 2 + Tile.TILE_SIZE &&
              Math.abs(diffYForEnemy) <= screenHeight / 2 + Tile.TILE_SIZE
          ) {
            g2.drawImage(enemy.getAnimatedImage(), screenCenterX + diffXForEnemy, screenCenterY + diffYForEnemy, null);
          }
        }

        for (Item item : fieldItemList) {
          Location potionRedLocation = item.getLocation();
          int diffXForPotionRed = potionRedLocation.getX() - playerLocation.getX();
          int diffYForPotionRed = potionRedLocation.getY() - playerLocation.getY();
          if (
              Math.abs(diffXForPotionRed) <= screenWidth / 2 + Tile.TILE_SIZE &&
              Math.abs(diffYForPotionRed) <= screenHeight / 2 + Tile.TILE_SIZE
          ) {
            g2.drawImage(item.getImage(), screenCenterX + diffXForPotionRed, screenCenterY + diffYForPotionRed, null);
          }
        }
      }
    }

    if (gameMode.isGameCleared()) {
      g2.drawString("クリア!", screenWidth / 2 - Tile.TILE_SIZE * 3, screenHeight / 2);
      g2.setColor(Color.black);
      g2.drawString("> Press Enter to Quit Game", screenWidth / 2 - Tile.TILE_SIZE * 3, screenHeight / 2 + Tile.TILE_SIZE * 3);
    }

    if (gameMode.isDisplayingItemList()) {
      itemListView.draw(g2);
    }

    if (gameMode.isBattle()) {
      battleView.draw(g2);
    }

    g2.dispose();
  }
}
