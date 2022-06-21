package com.example.sample.presentation;

import com.example.sample.application.service.WorldMapQueryService;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.Vector;
import com.example.sample.domain.model.WorldMap;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

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

  // FPS設定
  private static final int FPS = 60;
  private static final double DRAW_INTERVAL = 1000000000 / FPS;

  private Thread gameThread;

  private KeyInputHandler keyInputHandler = new KeyInputHandler();

  // TODO: キー入力の動作確認用、後でリファクタリングすること
  private Player player = new Player(new Location(100, 100));

  // TODO: Serviceの動作確認用、後でリファクタリングすること
  private final WorldMapQueryService worldMapQueryService;
  private WorldMap worldMap;

  private boolean isFinished = false;
  boolean isFirst = true;

  public GamePanel(WorldMapQueryService worldMapQueryService) {
    this.worldMapQueryService = worldMapQueryService;
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyInputHandler);
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

      // TODO: 要リファクタリング DoOnceを実装すること
      if (!isFinished) {
        update();
        isFinished = true;
      }

      if (isFirst) {
        worldMap = this.worldMapQueryService.find();
        isFirst = false;
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
    player.move(vector);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    g2.fillRect(player.getLocation().getX(), player.getLocation().getY(), tileSize, tileSize);
    // TODO: 動作確認用
    if (worldMap != null) {
      for (Tile[] tiles : worldMap.getTiles()) {
        for (Tile tile : tiles) {
          g2.drawImage(tile.getBufferedImage(), tile.getLocation().getX(), tile.getLocation().getY(), null);
        }
      }
    }
    g2.dispose();
  }
}
