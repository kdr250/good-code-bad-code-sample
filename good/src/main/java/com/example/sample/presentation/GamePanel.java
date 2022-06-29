package com.example.sample.presentation;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.gamemode.GameModeType;
import com.example.sample.domain.model.item.Consumable;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.Item;
import com.example.sample.presentation.battle.BattleView;
import com.example.sample.presentation.itemlist.ItemListView;
import com.example.sample.presentation.itemlist.ItemListViewChoice;
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


  private boolean isFinished = false;

  private final GameMode gameMode = new GameMode(GameModeType.WORLD_MAP);
  Font arial30 = new Font("Arial", Font.PLAIN, 30);

  // TODO: Viewの動作確認用、リファクタリングすること
  private ItemListView itemListView;
  private BattleView battleView;

  public GamePanel(KeyInputHandler keyInputHandler, WorldMapController worldMapController) {
    this.keyInputHandler = keyInputHandler;
    this.worldMapController = worldMapController;
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyInputHandler);
    this.setFocusable(true);
  }

  public void startGameThread() {

    worldMapController.start();

    itemListView = new ItemListView(worldMapController.getPlayer());

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
    }

    if (gameMode.isDisplayingItemList()) {
      if (itemListView.countUpFpsThenIsKeyInputAllowed()) {
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
        } else {
          itemListView.moveCursor(keyInputType);
        }
      }
      return;
    }

    if (gameMode.isGameCleared()) {
      if (keyInputHandler.getKeyInputType() == KeyInputType.DECIDE) {
        System.exit(0);
      }
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
      g2.drawString("クリア!", screenWidth / 2 - Tile.TILE_SIZE * 3, screenHeight / 2);
      g2.setColor(Color.black);
      g2.drawString("> Press Enter to Quit Game", screenWidth / 2 - Tile.TILE_SIZE * 3, screenHeight / 2 + Tile.TILE_SIZE * 3);
    }

    if (gameMode.isDisplayingItemList()) {
      itemListView.draw(g2);
    }

    if (gameMode.isBattle()) {
      if (battleView == null) {
        battleView = new BattleView(worldMapController.getPlayer(), worldMapController.getEnemy());
      }
      battleView.draw(g2);
    }

    g2.dispose();
  }
}
