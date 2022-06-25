package com.example.sample.presentation;

import com.example.sample.application.service.EnemyQueryService;
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
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

  // TODO: Serviceの動作確認用、後でリファクタリングすること
  private final WorldMapQueryService worldMapQueryService;
  private WorldMap worldMap;

  private boolean isFinished = false;
  boolean isFirst = true;

  public GamePanel(WorldMapQueryService worldMapQueryService, KeyInputHandler keyInputHandler, PlayerQueryService playerQueryService, NpcQueryService npcQueryService, EnemyQueryService enemyQueryService) {
    this.worldMapQueryService = worldMapQueryService;
    this.keyInputHandler = keyInputHandler;
    this.playerQueryService = playerQueryService;
    this.npcQueryService = npcQueryService;
    this.enemyQueryService = enemyQueryService;
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
        enemy = new Enemy(new Location(tileSize * 14, tileSize * 21), enemyAnimation);
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
    // TODO: 動作確認用
    if (worldMap != null) {
      Location playerWillMoveLocation = player.getLocation().shift(vector);
      List<Collidable> collidableListForPlayer = worldMap.getTilesFromLocation(playerWillMoveLocation);
      collidableListForPlayer.add(npc);
      collidableListForPlayer.add(enemy);
      if (player.canMove(collidableListForPlayer, vector)) {
        player.move(vector);
      } else {
        player.changeDirection(vector);
      }

      Location npcWillMoveLocation = npc.getLocation().shift(npc.getNpcMovement().getVector());
      List<Collidable> collidableListForNpc = worldMap.getTilesFromLocation(npcWillMoveLocation);
      collidableListForNpc.add(player);
      collidableListForPlayer.add(enemy);
      if (npc.updateMovementCountThenCanMove(collidableListForNpc)) {
        npc.move();
      } else {
        npc.changeDirection();
      }

      Location enemyWillMoveLocation = enemy.getLocation().shift(enemy.getEnemyMovement().getVector());
      List<Collidable> collidableListForEnemy = worldMap.getTilesFromLocation(enemyWillMoveLocation);
      collidableListForEnemy.add(player);
      collidableListForEnemy.add(npc);
      if (enemy.updateMovementCountThenCanMove(collidableListForEnemy)) {
        enemy.move();
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    // TODO: 動作確認用
    if (worldMap != null) {
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
    }
    g2.setColor(Color.white);
    if (player != null) {
      g2.drawImage(player.getAnimatedImage(), screenCenterX, screenCenterY, null);

      Location playerLocation = player.getLocation();

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
      }
    }

    g2.dispose();
  }
}
