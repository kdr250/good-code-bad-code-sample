package com.example.sample.presentation;

import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.presentation.battle.BattleController;
import com.example.sample.presentation.clear.GameClearController;
import com.example.sample.presentation.itemlist.ItemListController;
import com.example.sample.presentation.title.TitleController;
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
  private final TitleController titleController;
  private final WorldMapController worldMapController;
  private final ItemListController itemListController;
  private final BattleController battleController;
  private final GameClearController gameClearController;

  private boolean isFinished = false;

  private final GameMode gameMode = new GameMode(GameModeType.DISPLAY_TITLE);
  Font arial30 = new Font("Arial", Font.PLAIN, 30);

  public GamePanel(KeyInputHandler keyInputHandler, TitleController titleController, WorldMapController worldMapController, ItemListController itemListController, BattleController battleController, GameClearController gameClearController) {
    this.keyInputHandler = keyInputHandler;
    this.titleController = titleController;
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

    titleController.setUp();

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

    switch (gameMode.getGameModeType()) {
      case DISPLAY_TITLE:
        titleController.update(keyInputType, gameMode);
        return;
      case WORLD_MAP:
        worldMapController.update(keyInputType, gameMode);
        return;
      case DISPLAY_ITEM_LIST:
        itemListController.update(keyInputType, gameMode);
        return;
      case BATTLE:
        battleController.update(keyInputType, gameMode);
        return;
      case GAME_CLEAR:
        gameClearController.update(keyInputType);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setFont(arial30);
    g2.setColor(Color.white);

    switch (gameMode.getGameModeType()) {
      case DISPLAY_TITLE:
        titleController.draw(g2);
        break;
      case WORLD_MAP:
        worldMapController.draw(g2);
        break;
      case DISPLAY_ITEM_LIST:
        worldMapController.draw(g2);
        itemListController.draw(g2);
        break;
      case BATTLE:
        worldMapController.draw(g2);
        battleController.draw(g2);
        break;
      case GAME_CLEAR:
        worldMapController.draw(g2);
        gameClearController.draw(g2);
    }

    g2.dispose();
  }
}
