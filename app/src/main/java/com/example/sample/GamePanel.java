package com.example.sample;

import com.example.sample.entity.Entity;
import com.example.sample.entity.Player;
import com.example.sample.objects.AssetManager;
import com.example.sample.tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

  // スクリーン設定
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3;

  public final int tileSize = originalTileSize * scale; // 48x48 tile
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

  // ワールド設定
  public final int maxWorldCol = 30;
  public final int maxWorldRow = 40;

  // FPS
  final int FPS = 60;

  // システム設定
  TileManager tileM = new TileManager(this);
  public KeyManager keyManager = new KeyManager(this);
  Thread gameThread;
  public CollisionChecker checker = new CollisionChecker(this);
  public AssetManager assetManager = new AssetManager(this);
  SoundManager music = new SoundManager();
  SoundManager se = new SoundManager();
  public UI ui = new UI(this);

  // ゲームオブジェクト
  public Player player = new Player(this, keyManager);
  public Entity[] objects = new Entity[20];
  public Entity[] npc = new Entity[5];
  public Entity[] monsters = new Entity[20];
  public List<Entity> entityList = new ArrayList<>();
  public List<Entity> projectileList = new ArrayList<>();
  public List<Entity> particleList = new ArrayList<>();
  public Entity battleMonster;

  public ActorManager actorManager = new ActorManager();
  public PhysicalAttackManager physicalAttackManager = new PhysicalAttackManager();
  public MagicManager magicManager = new MagicManager();

  // ゲーム状態
  public int gameState;
  public final int titleState = 0;
  public final int playState = 1;
  public final int pauseState = 2;
  public final int dialogState = 3;
  public final int characterState = 4;
  public final int optionState = 5;
  public final int gameOverState = 6;
  public final int battleState = 7;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.addKeyListener(keyManager);
    this.setDoubleBuffered(true);
  }

  public void setupGame() {
    assetManager.setObjects();
    assetManager.setNpc();
    assetManager.setMonsters();
    gameState = titleState;
  }

  public void retry() {
    player.setDefaultPositions();
    player.restoreLifeAndMana();
    assetManager.setNpc();
    assetManager.setMonsters();
  }

  public void restart() {
    player.setDefaultValues();
    player.setDefaultPositions();
    player.restoreLifeAndMana();
    player.setItems();
    assetManager.setNpc();
    assetManager.setMonsters();
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;

    while (gameThread != null) {
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta --;
      }

      if (timer >= 1000000000) {
        timer = 0;
      }
    }
  }

  public void update() {
    if (gameState == playState) {
      if (player.canMove()) {
        switch (player.direction) {
          case "up":
            actorManager.shift(player.location, 0, player.speed * -1);
            break;
          case "down":
            actorManager.shift(player.location, 0, player.speed);
            break;
          case "left":
            actorManager.shift(player.location, player.speed * -1, 0);
            break;
          case "right":
            actorManager.shift(player.location, player.speed, 0);
            break;
        }
      }
      player.update(); // プレイヤー
      for (Entity e : npc) {
        if (e != null) e.update(); // NPC
      }
      for (int i = 0; i < monsters.length; i++) {
        Entity e = monsters[i];
        if (e != null) {
          if (e.alive && !e.dying) {
            e.update(); // NPC
          }
          if (!e.alive) {
            monsters[i] = null;
          }
        }
      }
      for (int i = 0; i < projectileList.size(); i++) {
        if (projectileList.get(i) != null) {
          if (projectileList.get(i).alive) {
            projectileList.get(i).update();
          } else {
            projectileList.remove(i);
          }
        }
      }
      for (int i = 0; i < particleList.size(); i++) {
        if (particleList.get(i) != null) {
          if (particleList.get(i).alive) {
            particleList.get(i).update();
          } else {
            particleList.remove(i);
          }
        }
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;

    // タイトルを描画
    if (gameState == titleState) {
      ui.draw(g2);

    } else {

      // タイルを描画
      tileM.draw(g2);

      // Entityのリストにする
      entityList.add(player);
      for (Entity e : npc) {
        if (e != null) entityList.add(e);
      }
      for (Entity e : objects) {
        if (e != null) entityList.add(e);
      }
      for (Entity e : monsters) {
        if (e != null) entityList.add(e);
      }
      for (Entity e : projectileList) {
        if (e != null) entityList.add(e);
      }
      for (Entity e : particleList) {
        if (e != null) entityList.add(e);
      }

      // そーと
      entityList.sort(Comparator.comparingInt(e -> e.location.worldY));

      // Entityを描画
      for (Entity e : entityList) {
        e.draw(g2);
      }

      // クリアする
      entityList.clear();

      // UI
      ui.draw(g2);
    }

    g2.dispose();
  }

  public void playMusic(int i) {
    music.setFile(i);
    music.play();
    music.loop();
  }

  public void stopMusic() {
    music.stop();
  }

  public void playSE(int i) {
    se.setFile(i);
    se.play();
  }
}

