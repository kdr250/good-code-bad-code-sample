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
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameClearEvent;
import com.example.sample.domain.model.item.Interactive;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemChest;
import com.example.sample.domain.model.item.ItemDoor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.item.ItemPotionRed;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GamePanel extends JPanel implements Runnable {

  // スクリーン設定
  private static final int originalTileSize = 16; // 16x16
  private static final int scale = 3;

  public static final int tileSize = originalTileSize * scale; // 48x48 tile
  private static final int maxScreenCol = 16;
  private static final int maxScreenRow = 12;
  private static final int screenWidth = tileSize * maxScreenCol; // 768 px
  private static final int screenHeight = tileSize * maxScreenRow; // 576 px
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
  boolean isFirst = true;

  private GameMode gameMode = GameMode.WORLD_MAP;
  Font arial30 = new Font("Arial", Font.PLAIN, 30);

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
    this.addKeyListener(this.keyInputHandler);
    this.setFocusable(true);
  }

  public void startGameThread() {
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

      if (isFirst) {
        worldMap = this.worldMapQueryService.find();
        // TODO: 動作確認用、後でリファクタリングすること
        PlayerAnimation playerAnimation = playerQueryService.find();
        player = new Player(new Location(tileSize * 23, tileSize * 21), playerAnimation);
        NpcAnimation npcAnimation = npcQueryService.find();
        npc = new Npc(new Location(tileSize * 21, tileSize * 20), npcAnimation);
        EnemyAnimation enemyAnimation = enemyQueryService.find();
        enemy = new Enemy(new Location(tileSize * 9, tileSize * 30), enemyAnimation);
        ItemImage itemImage = itemQueryService.find(ItemType.POTION_RED);
        Item potionRed = new ItemPotionRed(new Location(tileSize * 22, tileSize * 7), itemImage);
        fieldItemList.add(potionRed);
        ItemImage itemImageKey = itemQueryService.find(ItemType.KEY);
        Item key = new ItemKey(new Location(tileSize * 22, tileSize * 9), itemImageKey);
        fieldItemList.add(key);
        ItemImage itemImageDoor = itemQueryService.find(ItemType.DOOR);
        Item door = new ItemDoor(new Location(tileSize * 10, tileSize * 11), itemImageDoor);
        fieldItemList.add(door);
        ItemImage itemImageChest = itemQueryService.find(ItemType.CHEST);
        Item chest = new ItemChest(new Location(tileSize * 10, tileSize * 7), itemImageChest);
        fieldItemList.add(chest);
        isFirst = false;
      }

      // TODO: 要リファクタリング DoOnceを実装すること
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
    Vector vector = keyInputHandler.getKeyInputType().getVector();

    if (gameMode == GameMode.GAME_CLEAR) {
      if (keyInputHandler.getKeyInputType() == KeyInputType.DECIDE) {
        System.exit(0);
      }
      return;
    }

    // TODO: 動作確認用
    if (worldMap != null) {
      Location playerWillMoveLocation = player.getLocation().shift(vector);

      for (Item item : fieldItemList) {
        if (item.getCollision().isCollide(player.getCollision().shift(vector))) {
          if (item instanceof Interactive) {
            Event event = ((Interactive) item).interact();
            if (event instanceof GameClearEvent) {
              gameMode = GameMode.GAME_CLEAR;
              return;
            }
            if (event.execute(player)) {
              fieldItemList.remove(item);
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
      collidableListForPlayer.add(enemy);
      collidableListForPlayer.addAll(fieldItemList.stream().filter(item -> item instanceof Interactive).collect(Collectors.toList()));
      if (player.canMove(collidableListForPlayer, vector)) {
        player.move(vector);
      } else {
        player.changeDirection(vector);
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
      collidableListForEnemy.add(player);
      collidableListForEnemy.add(npc);
      if (enemy.updateMovementThenCanMove(collidableListForEnemy)) {
        enemy.move();
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setFont(arial30);
    g2.setColor(Color.white);

    // TODO: 動作確認用
    if (worldMap != null && player != null) {
      Location playerLocation = player.getLocation();
      for (Tile[] tiles : worldMap.getTiles()) {
        for (Tile tile : tiles) {
          Location tileLocation = tile.getLocation();
          int diffX = tileLocation.getX() - playerLocation.getX();
          int diffY = tileLocation.getY() - playerLocation.getY();
          if (
            Math.abs(diffX) <= screenWidth / 2 + tileSize &&
            Math.abs(diffY) <= screenHeight / 2 + tileSize
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
            Math.abs(diffX) <= screenWidth / 2 + tileSize &&
            Math.abs(diffY) <= screenHeight / 2 + tileSize
        ) {
          g2.drawImage(npc.getAnimatedImage(), screenCenterX + diffX, screenCenterY + diffY, null);
        }

        if (enemy != null) {
          Location enemyLocation = enemy.getLocation();
          int diffXForEnemy = enemyLocation.getX() - playerLocation.getX();
          int diffYForEnemy = enemyLocation.getY() - playerLocation.getY();
          if (
              Math.abs(diffXForEnemy) <= screenWidth / 2 + tileSize &&
              Math.abs(diffYForEnemy) <= screenHeight / 2 + tileSize
          ) {
            g2.drawImage(enemy.getAnimatedImage(), screenCenterX + diffXForEnemy, screenCenterY + diffYForEnemy, null);
          }
        }

        for (Item item : fieldItemList) {
          Location potionRedLocation = item.getLocation();
          int diffXForPotionRed = potionRedLocation.getX() - playerLocation.getX();
          int diffYForPotionRed = potionRedLocation.getY() - playerLocation.getY();
          if (
              Math.abs(diffXForPotionRed) <= screenWidth / 2 + tileSize &&
              Math.abs(diffYForPotionRed) <= screenHeight / 2 + tileSize
          ) {
            g2.drawImage(item.getImage(), screenCenterX + diffXForPotionRed, screenCenterY + diffYForPotionRed, null);
          }
        }
      }
    }

    if (gameMode == GameMode.GAME_CLEAR) {
      g2.drawString("クリア!", screenWidth / 2 - tileSize * 3, screenHeight / 2);
      g2.setColor(Color.black);
      g2.drawString("> Press Enter to Quit Game", screenWidth / 2 - tileSize * 3, screenHeight / 2 + tileSize * 3);
    }

    g2.dispose();
  }
}
