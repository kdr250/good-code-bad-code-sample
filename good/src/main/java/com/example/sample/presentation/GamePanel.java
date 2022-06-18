package com.example.sample.presentation;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Vector;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

  // スクリーン設定
  private final int originalTileSize = 16; // 16x16
  private final int scale = 3;

  private final int tileSize = originalTileSize * scale; // 48x48 tile
  private final int maxScreenCol = 16;
  private final int maxScreenRow = 12;
  private final int screenWidth = tileSize * maxScreenCol; // 768 px
  private final int screenHeight = tileSize * maxScreenRow; // 576 px

  // FPS設定
  private final int FPS = 60;
  private final double DRAW_INTERVAL = 1000000000 / FPS;

  private Thread gameThread;

  private KeyInputHandler keyInputHandler = new KeyInputHandler();

  // TODO: キー入力の動作確認用、後でリファクタリングすること
  private Player player = new Player(new Location(100, 100));

  private boolean isFinished = false;

  public GamePanel() {
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
    g2.dispose();
  }
}
