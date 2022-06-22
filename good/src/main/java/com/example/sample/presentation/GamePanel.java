package com.example.sample.presentation;

import com.example.sample.presentation.input.KeyInputHandler;
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

  private final KeyInputHandler keyInputHandler;

  public GamePanel(KeyInputHandler keyInputHandler) {
    this.keyInputHandler = keyInputHandler;
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

      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
    }
  }

  private void update() {
    // TODO: キー入力結果を受け取って更新する
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    // TODO: 動作確認用、後で修正すること
    g2.fillRect(100, 100, tileSize, tileSize);
    g2.dispose();
  }
}
