package com.example.sample.presentation;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.presentation.battle.BattleController;
import com.example.sample.presentation.clear.GameClearController;
import com.example.sample.presentation.itemlist.ItemListController;
import com.example.sample.presentation.worldmap.WorldMapController;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class GamePanel extends JPanel implements Runnable {

  // スクリーン設定
  private static final int maxScreenCol = 16;
  private static final int maxScreenRow = 12;
  public static final int screenWidth = Tile.TILE_SIZE * maxScreenCol; // 768 px
  public static final int screenHeight = Tile.TILE_SIZE * maxScreenRow; // 576 px
  public static final int screenCenterX = screenWidth / 2;
  public static final int screenCenterY = screenHeight / 2;

  // FPS設定
  private static final int FPS = 60;
  private static final double DRAW_INTERVAL = 1000000000 / FPS;

  private Thread gameThread;

  private final KeyInputHandler keyInputHandler;

  private final WorldMapController worldMapController;

  private final ItemListController itemListController;

  private final BattleController battleController;

  private final GameClearController gameClearController;

  private boolean isFinished = false;

  private final GameMode gameMode = new GameMode(GameModeType.WORLD_MAP);
  Font arial30 = new Font("Arial", Font.PLAIN, 30);

  public GamePanel(KeyInputHandler keyInputHandler, WorldMapController worldMapController, ItemListController itemListController, BattleController battleController, GameClearController gameClearController) {
    this.keyInputHandler = keyInputHandler;
    this.worldMapController = worldMapController;
    this.itemListController = itemListController;
    this.battleController = battleController;
    this.gameClearController = gameClearController;
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyInputHandler);
    this.setFocusable(true);
  }

  public void startGameThread() {

    worldMapController.start();

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
    Player player = worldMapController.getPlayer();

    if (keyInputType == KeyInputType.DISPLAY_ITEM_LIST) {
      gameMode.displayItemList();
      itemListController.start(player);
    }

    if (gameMode.isDisplayingItemList()) {
      itemListController.update(keyInputType, gameMode);
    }

    if (gameMode.isBattle()) {
      // TODO: Not yet implemented
    }

    if (gameMode.isGameCleared()) {
      gameClearController.update(keyInputType);
      return;
    }

    if (gameMode.isWorldMap()) {
      worldMapController.update(keyInputType, gameMode);
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setFont(arial30);
    g2.setColor(Color.white);

    // TODO: 動作確認用、後でリファクタリングすること

    worldMapController.draw(g2);

    if (gameMode.isGameCleared()) {
      gameClearController.draw(g2);
    }

    if (gameMode.isDisplayingItemList()) {
      itemListController.draw(g2);
    }

    if (gameMode.isBattle()) {
      battleController.draw(g2);
    }

    g2.dispose();
  }
}
