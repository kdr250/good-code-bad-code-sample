package com.example.sample;

import com.example.sample.entity.Entity;
import com.example.sample.entity.Player;
import com.example.sample.objects.BattleTechnique;
import com.example.sample.objects.ItemMagicalCrystal;
import com.example.sample.objects.Level;
import com.example.sample.objects.MagicType;
import com.example.sample.objects.PhysicalAttackType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UI {

  Graphics2D g2;
  GamePanel panel;
  Font arial30, arial40, arialBold80;
  BufferedImage crystalFull, crystalBlank;
  List<String> message = new ArrayList<>();
  List<Integer> messageCounter = new ArrayList<>();
  public String currentDialog = "";
  public int commandNum = 0;
  public int slotCol = 0;
  public int slotRow = 0;
  int subState = 0;

  public boolean isGameFinished = false;

  boolean isLackOfMana = false;

  public UI(GamePanel panel) {
    this.panel = panel;

    arial30 = new Font("Arial", Font.PLAIN, 30);
    arial40 = new Font("Arial", Font.PLAIN, 40);
    arialBold80 = new Font("Arial", Font.BOLD, 80);

    Entity crystal = new ItemMagicalCrystal(panel);
    crystalFull = crystal.image;
    crystalBlank = crystal.image2;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;
    g2.setFont(arial30);
    g2.setColor(Color.white);

    if (isGameFinished) {
      String text = "宝物を見つけた!";
      int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      int x = panel.screenWidth / 2 - textLength / 2;
      int y = panel.screenHeight / 2 - panel.tileSize * 3;
      g2.drawString(text, x, y);

      g2.setFont(arialBold80);
      g2.setColor(Color.YELLOW);
      String text3 = "ゲームクリア!";
      int textLength3 = (int)g2.getFontMetrics().getStringBounds(text3, g2).getWidth();
      int x3 = panel.screenWidth / 2 - textLength3 / 2;
      int y3 = panel.screenHeight / 2 + panel.tileSize * 2;
      g2.drawString(text3, x3, y3);

      g2.setFont(arial30);
      g2.setColor(Color.white);
      String text4 = "> Press Enter to Quit Game";
      int textLength4 = (int)g2.getFontMetrics().getStringBounds(text4, g2).getWidth();
      int x4 = panel.screenWidth / 2 - textLength4 / 2;
      int y4 = panel.screenHeight / 2 + panel.tileSize * 4;
      g2.drawString(text4, x4, y4);
      if (panel.keyManager.enterPressed) {
        System.exit(0);
      }

      return;
    }

    panel.player.checkLevelUp();

    if (panel.gameState == panel.titleState) {
      drawTitleScreen();
    }
    else if (panel.gameState == panel.playState) {
      drawPlayerLife();
      drawMessage();
    }
    else if (panel.gameState == panel.pauseState) {
      drawPauseScreen();
    }
    else if (panel.gameState == panel.dialogState) {
      drawDialogScreen();
    }
    else if (panel.gameState == panel.characterState) {
      drawCharacterScreen();
      drawInventory();
    }
    else if (panel.gameState == panel.optionState) {
      drawOptionScreen();
    }
    else if (panel.gameState == panel.gameOverState) {
      drawGameOverScreen();
    }
    else if (panel.gameState == panel.battleState) {
      drawBattleScreen();
    }
  }

  private void drawBattleScreen() {
    g2.setColor(Color.black);
    g2.fillRect(panel.tileSize, panel.tileSize, panel.screenWidth - panel.tileSize * 2, panel.screenHeight - panel.tileSize * 2);
    // モンスターの画像
    int monsterImageX = panel.screenWidth / 2 - panel.tileSize * 5;
    int monsterImageY = (int)(panel.tileSize * 1.5);
    int monsterImageWidth = 150;
    int monsterImageHeight = 150;
    g2.drawImage(panel.battleMonster.down1, monsterImageX, monsterImageY, monsterImageWidth, monsterImageHeight, null);
    // モンスターのライフバー
    int monsterLifeBarX = monsterImageX + 10;
    int monsterLifeBarY = monsterImageY + monsterImageHeight;
    int monsterLifeBarMaxWidth = 120;
    g2.setColor(Color.red);
    g2.setStroke(new BasicStroke(3));
    g2.drawRect(monsterLifeBarX, monsterLifeBarY, monsterLifeBarMaxWidth, 20);
    double monsterLifeBarWidth = monsterLifeBarMaxWidth * ((double) panel.battleMonster.hitPoint / (double) panel.battleMonster.maxHitPoint);
    g2.fillRect(monsterLifeBarX, monsterLifeBarY, (int)monsterLifeBarWidth, 20);
    // プレイヤーの画像
    int playerImageX = panel.screenWidth / 2 + panel.tileSize * 2;
    int playerImageY = (int)(panel.tileSize * 1.5);
    int playerImageWidth = 150;
    int playerImageHeight = 150;
    g2.drawImage(panel.player.down1, playerImageX, playerImageY, playerImageWidth, playerImageHeight, null);
    // プレイヤーのライフバー
    int playerLifeBarX = playerImageX + 10;
    int playerLifeBarY = playerImageY + playerImageHeight;
    int playerLifeBarMaxWidth = 120;
    g2.drawRect(playerLifeBarX, playerLifeBarY, playerLifeBarMaxWidth, 20);
    double playerLifeBarWidth = playerLifeBarMaxWidth * ((double) panel.player.hitPoint / (double) panel.player.maxHitPoint);
    g2.fillRect(playerLifeBarX, playerLifeBarY, (int)playerLifeBarWidth, 20);
    // プレイヤーの魔法力
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(24f));
    for (int i = 0; i < panel.player.maxMagicPoint(); i++) {
      if (i < panel.player.magicPoint) {
        g2.drawImage(crystalFull, playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
      } else {
        g2.drawImage(crystalBlank, playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
      }
    }

    g2.setFont(g2.getFont());
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(panel.tileSize + 15, panel.screenHeight / 2, panel.tileSize * 9, panel.tileSize * 5 - 15, 10, 10);
    g2.drawRoundRect(panel.tileSize + panel.tileSize * 9 + 30, panel.screenHeight / 2, panel.tileSize * 4, panel.tileSize * 5 - 15, 10, 10);

    if (subState == 0) {
      g2.drawString(panel.battleMonster.name + "が現れた", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      g2.drawString("戦う", panel.tileSize + panel.tileSize * 10, panel.screenHeight / 2 + panel.tileSize);
      g2.drawString("にげる", panel.tileSize + panel.tileSize * 10, panel.screenHeight / 2 + panel.tileSize * 2);

      g2.drawString(">", panel.tileSize + panel.tileSize * 10 - 15, panel.screenHeight / 2 + panel.tileSize * (commandNum + 1));
      if (panel.keyManager.enterPressed) {
        subState = commandNum == 0 ? 1 : 4;
        commandNum = 0;
        panel.keyManager.enterPressed = false;
      }
    }
    else if (subState == 1) {
      isLackOfMana = false;
      String description = panel.player.techniques[commandNum] instanceof MagicType ?
          panel.magicManager.description((MagicType) panel.player.techniques[commandNum]) :
          panel.physicalAttackManager.description((PhysicalAttackType) panel.player.techniques[commandNum]);
      g2.drawString(description, panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      for (int i = 0; i < panel.player.techniques.length; i++) {
        String name = panel.player.techniques[i] instanceof MagicType ?
            panel.magicManager.getName((MagicType) panel.player.techniques[i]) :
            panel.physicalAttackManager.getName((PhysicalAttackType) panel.player.techniques[i]);
        g2.drawString(name, panel.tileSize + panel.tileSize * 10, panel.screenHeight / 2 + panel.tileSize * (i + 1));
      }
      g2.drawString(">", panel.tileSize + panel.tileSize * 10 - 15, panel.screenHeight / 2 + panel.tileSize * (commandNum + 1));
      if (panel.keyManager.enterPressed) {
        panel.keyManager.enterPressed = false;
        int consumeMagicPoint = panel.player.techniques[commandNum] instanceof MagicType ?
            panel.magicManager.costMagicPoint((MagicType) panel.player.techniques[commandNum]) : 0;
        if (panel.player.magicPoint >= consumeMagicPoint) {
          BattleTechnique technique = panel.player.techniques[commandNum];
          if (technique instanceof PhysicalAttackType) {
            panel.battleMonster.hitPoint -= attackPowerByPhysicalAttack((PhysicalAttackType)technique, panel.player);
          } else if (technique instanceof MagicType) {
            panel.player.magicPoint -= panel.magicManager.costMagicPoint((MagicType)technique);
            panel.battleMonster.hitPoint -= attackPower((MagicType)technique, panel.player);
          }
        } else {
          isLackOfMana = true;
        }
        subState = 2;
        return;
      }
    }
    else if (subState == 2) {
      if (isLackOfMana) {
        g2.drawString("魔力が足りない!", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      } else {
        int damage = 0;
        BattleTechnique technique = panel.player.techniques[commandNum];
        if (technique instanceof PhysicalAttackType) {
          damage = attackPowerByPhysicalAttack((PhysicalAttackType)technique, panel.player);
        } else if (technique instanceof MagicType) {
          damage = attackPower((MagicType)technique, panel.player);
        }
        g2.drawString(panel.battleMonster.name + "に" + damage + "ダメージ！", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      }
      g2.drawString("> Press Enter", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize * 2);
      if (panel.keyManager.enterPressed) {
        panel.keyManager.enterPressed = false;
        if (isLackOfMana) {
          subState = 1;
          commandNum = 0;
          return;
        }
        else if (panel.battleMonster.hitPoint <= 0) {
          panel.battleMonster.addItemToItemList(panel.player.inventory);
          subState = 4;
          commandNum = 0;
          return;
        }
        int damageAmount = Math.max(panel.battleMonster.attack.value() - panel.player.totalDefense(), 1);
        /** ×リスト2.7 どこかに書かれるヒットポイント減少ロジック */
        panel.player.hitPoint = panel.player.hitPoint - damageAmount;
        subState = 3;
        commandNum = 0;
      }
    }
    else if (subState == 3) {
      g2.drawString("プレイヤーに" + Math.max(panel.battleMonster.attack.value() - panel.player.totalDefense(), 1) + "ダメージ！", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      if (panel.player.hitPoint <= 0) {
        g2.drawString("敗北！防具が壊れた！", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize * 2);
        panel.player.deleteEquipmentFromItemList();
        panel.player.takeOffAllEquipments();
      }
      g2.drawString("> Press Enter", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize * 3);
      if (panel.keyManager.enterPressed) {
        panel.keyManager.enterPressed = false;
        if (panel.player.hitPoint <= 0) {
          panel.gameState = panel.gameOverState;
          subState = 0;
          commandNum = 0;
          return;
        }
        subState = 0;
        commandNum = 0;
      }
    }
    else if (subState == 4) {
      String text;
      if (panel.battleMonster.hitPoint <= 0) {
        levelUp();
        text = "勝利！ " + panel.player.inventory.get(panel.player.inventory.size() - 1).name + "を手に入れた!";
      } else {
        text = "逃げた!";
      }
      g2.drawString(text, panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize);
      g2.drawString("> Press Enter", panel.tileSize + 30, panel.screenHeight / 2 + panel.tileSize * 2);
      if (panel.keyManager.enterPressed) {
        panel.gameState = panel.playState;
        subState = 0;
        commandNum = 0;
        panel.keyManager.enterPressed = false;
        panel.player.exp += panel.battleMonster.exp;
        for (int i = 0; i < panel.monsters.length; i++) {
          if (panel.battleMonster == panel.monsters[i]) {
            panel.battleMonster = null;
            panel.monsters[i] = null;
          }
        }
      }
    }
  }

  private void drawMessage() {
    int messageX = panel.tileSize;
    int messageY = panel.tileSize * 4;
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));

    for (int i = 0; i < message.size(); i++) {
      if (message.get(i) != null) {
        g2.setColor(Color.black);
        g2.drawString(message.get(i), messageX + 2, messageY + 2);
        g2.setColor(Color.white);
        g2.drawString(message.get(i), messageX, messageY);

        int counter = messageCounter.get(i) + 1;
        messageCounter.set(i, counter);
        messageY += 50;

        if (messageCounter.get(i) > 180) {
          message.remove(i);
          messageCounter.remove(i);
        }
      }
    }
  }

  private void drawTitleScreen() {
    g2.setColor(new Color(0, 0, 0));
    g2.fillRect(0, 0, panel.screenWidth, panel.screenHeight);

    // タイトル名
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
    String text = "Java Sample Adventure";
    int x = getXForCenteredText(text);
    int y = panel.tileSize * 3;

    // 文字に影を入れる
    g2.setColor(Color.gray);
    g2.drawString(text, x + 5, y + 5);

    // メインの色
    g2.setColor(Color.white);
    g2.drawString(text, x, y);

    // プレイヤーの画像
    int playerX = panel.screenWidth / 2 - (panel.tileSize * 2) / 2;
    int playerY = y + panel.tileSize * 2;
    g2.drawImage(panel.player.down1, playerX, playerY, panel.tileSize * 2, panel.tileSize * 2, null);

    // メニュー
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

    String text2 = "New Game";
    int newGameX = getXForCenteredText(text2);
    int newGameY = playerY + panel.tileSize * 4;
    g2.drawString(text2, newGameX, newGameY);
    if (commandNum == 0) g2.drawString(">", newGameX - panel.tileSize, newGameY);

    String text3 = "Quit";
    int loadGameX = getXForCenteredText(text3);
    int loadGameY = newGameY + panel.tileSize;
    g2.drawString(text3, loadGameX, loadGameY);
    if (commandNum == 1) g2.drawString(">", loadGameX - panel.tileSize, loadGameY);
  }

  private void drawPlayerLife() {
    int x = panel.tileSize / 2;
    int y = panel.tileSize / 2;
    // 現在のライフを表示する
    int playerLifeBarX = x + 15;
    int playerLifeBarMaxWidth = 120;
    g2.setColor(Color.black);
    g2.fillRect(playerLifeBarX, y, playerLifeBarMaxWidth, 20);
    double playerLifeBarWidth = playerLifeBarMaxWidth * ((double) panel.player.hitPoint / (double) panel.player.maxHitPoint);
    g2.setColor(Color.red);
    g2.fillRect(playerLifeBarX, y, (int)playerLifeBarWidth, 20);
    // 魔法力最大値を表示する
    x = panel.tileSize / 2 - 5;
    y = (int)(panel.tileSize * 1.5);
    for (int i = 0; i < panel.player.maxMagicPoint(); i++) {
      g2.drawImage(crystalBlank, x, y, null);
      x += 35;
    }

    // 現在の魔法力を表示する
    x = panel.tileSize / 2 - 5;
    y = (int)(panel.tileSize * 1.5);
    for (int i = 0; i < panel.player.magicPoint; i++) {
      g2.drawImage(crystalFull, x, y, null);
      x += 35;
    }
  }

  public void drawPauseScreen() {
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
    String text = "PAUSED";
    int x = getXForCenteredText(text);
    int y = panel.screenHeight / 2;
    g2.drawString(text, x, y);
  }

  private void drawDialogScreen() {
    if (currentDialog.isBlank()) return;
    // ウィンドウ
    int x = panel.tileSize * 2;
    int y = panel.tileSize / 2;
    int width = panel.screenWidth - (panel.tileSize * 4);
    int height = panel.tileSize * 4;
    drawSubWindow(x, y, width, height);

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32));
    x += panel.tileSize;
    y += panel.tileSize;
    for (String line : currentDialog.split("\n")) {
      g2.drawString(line, x, y);
      y += 40;
    }
  }

  private void drawCharacterScreen() {
    // フレームを作成
    final int frameX = panel.tileSize;
    final int frameY = panel.tileSize;
    final int frameWidth = panel.tileSize * 5;
    final int frameHeight =  panel.tileSize * 10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    // テキスト
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(32F));
    int textX = frameX + 20;
    int textY = frameY + panel.tileSize;
    final int lineHeight = 35;

    // 項目名
    g2.drawString("Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Life", textX, textY);
    textY += lineHeight;
    g2.drawString("Mana", textX, textY);
    textY += lineHeight;
    g2.drawString("Attack", textX, textY);
    textY += lineHeight;
    g2.drawString("Defense", textX, textY);
    textY += lineHeight;
    g2.drawString("Exp", textX, textY);
    textY += lineHeight;
    g2.drawString("Next Level", textX, textY);
    textY += lineHeight + 10;
    g2.drawString("Weapon", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Body", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Arm", textX, textY);

    // 値
    int tailX = (frameX + frameWidth) - 30;
    textY = frameY + panel.tileSize;
    String value;
    value = String.valueOf(panel.player.level.value());
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = panel.player.hitPoint + "/" + panel.player.maxHitPoint;
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = panel.player.magicPoint + "/" + panel.player.maxMagicPoint();
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(panel.player.getAttack());
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(panel.player.totalDefense());
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(panel.player.exp);
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(panel.player.nextLevelExp);
    textX = getXForAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    g2.drawImage(panel.player.currentWeapon.down1, tailX - panel.tileSize, textY - 24, null);
    textY += panel.tileSize;
    if (panel.player.body != null) {
      g2.drawImage(panel.player.body.image(), tailX - panel.tileSize, textY - 24, null);
    }
    textY += panel.tileSize;
    if (panel.player.arm != null) {
      g2.drawImage(panel.player.arm.image(), tailX - panel.tileSize, textY - 24, null);
    }
  }

  private void drawInventory() {
    // フレーム
    int frameX = panel.tileSize * 9;
    int frameY = panel.tileSize;
    int frameWidth = panel.tileSize * 6;
    int frameHeight = panel.tileSize * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    // スロット
    final int slotXStart = frameX + 20;
    final int slotYStart = frameY + 20;
    int slotX = slotXStart;
    int slotY = slotYStart;
    int slotSize = panel.tileSize + 3;

    // プレイヤーの所持アイテムを表示
    for (int i = 0; i < panel.player.inventory.size(); i++) {

      // 装備・使用カーソル
      if (
          panel.player.inventory.get(i) == panel.player.currentWeapon ||
              panel.player.inventory.get(i) == panel.player.body ||
              panel.player.inventory.get(i) == panel.player.arm
      ) {
        g2.setColor(new Color(240, 190, 90));
        g2.fillRoundRect(slotX, slotY, panel.tileSize, panel.tileSize, 10, 10);
      }

      g2.drawImage(panel.player.inventory.get(i).down1, slotX, slotY, null);
      slotX += slotSize;
      if (i == 4 || i == 9 || i == 14) {
        slotX = slotXStart;
        slotY += slotSize;
      }
    }

    // カーソル
    int cursorX = slotXStart + (slotSize * slotCol);
    int cursorY = slotYStart + (slotSize * slotRow);
    int cursorWidth = panel.tileSize;
    int cursorHeight = panel.tileSize;

    // カーソル描画
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

    // アイテム説明
    int dFrameX = frameX;
    int dFrameY = frameY + frameHeight;
    int dFrameWidth = frameWidth;
    int dFrameHeight = panel.tileSize * 3;

    int textX = dFrameX + 20;
    int textY = dFrameY + panel.tileSize;
    g2.setFont(g2.getFont().deriveFont(28F));

    int itemIndex = getItemIndexOnSlot();
    if (itemIndex < panel.player.inventory.size()) {
      drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
      for (String line : panel.player.inventory.get(itemIndex).description.split("\n")) {
        g2.drawString(line, textX, textY);
        textY += 32;
      }
    }
  }

  private void drawOptionScreen() {
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(32F));

    int frameX = panel.tileSize * 6;
    int frameY = panel.tileSize;
    int frameWidth = panel.tileSize * 8;
    int frameHeight = panel.tileSize * 10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    switch (subState) {
      case 0:
        optionsTop(frameX, frameY);
        break;
      case 1:
        optionsControl(frameX, frameY);
        break;
      case 2:
        System.exit(0);
        break;
    }

    panel.keyManager.enterPressed = false;
  }

  private void drawGameOverScreen() {
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRect(0, 0, panel.screenWidth, panel.screenHeight);

    String text = "Game Over";
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

    // 影
    g2.setColor(Color.black);
    int x = getXForCenteredText(text);
    int y = panel.tileSize * 4;
    g2.drawString(text, x, y);
    // メイン色
    g2.setColor(Color.white);
    g2.drawString(text, x - 4, y - 4);

    // リトライ
    g2.setFont(g2.getFont().deriveFont(50f));
    text = "リトライ";
    x = getXForCenteredText(text);
    y += panel.tileSize * 4;
    g2.drawString(text, x, y);
    if (commandNum == 0) {
      g2.drawString(">", x - 40, y);
    }
    // やめる
    text = "Quit";
    x = getXForCenteredText(text);
    y += 55;
    g2.drawString(text, x, y);
    if (commandNum == 1) {
      g2.drawString(">", x - 40, y);
    }
  }

  private void optionsTop(int frameX, int frameY) {
    String text = "オプション";
    int textX = getXForCenteredText(text);
    int textY = frameY + panel.tileSize;
    g2.drawString(text, textX, textY);

    // SE
    textX = frameX + panel.tileSize;
    textY += panel.tileSize * 2;
    g2.drawString("SE", textX, textY);
    if (commandNum == 1) {
      g2.drawString(">", textX - 25, textY);
    }
    // オプション
    textY += panel.tileSize;
    g2.drawString("操作方法", textX, textY);
    if (commandNum == 2) {
      g2.drawString(">", textX - 25, textY);
      if (panel.keyManager.enterPressed) {
        subState = 1;
        commandNum = 0;
      }
    }
    // Quit Game
    textY += panel.tileSize;
    g2.drawString("Quit Game", textX, textY);
    if (commandNum == 3) {
      g2.drawString(">", textX - 25, textY);
      if (panel.keyManager.enterPressed) {
        System.exit(0);
      }
    }

    // SEの音量バー
    textX = frameX + panel.tileSize * 5;
    textY = frameY + panel.tileSize * 2 + 24;
    g2.drawRect(textX, textY, 120, 24);
    int volumeWidth = 24 * panel.se.volumeScale;
    g2.fillRect(textX, textY, volumeWidth, 24);
  }

  private void optionsControl(int frameX, int frameY) {
    String text = "操作方法";
    int textX = getXForCenteredText(text);
    int textY = frameY + panel.tileSize;
    g2.drawString(text, textX, textY);

    textX = frameX + panel.tileSize;
    textY += panel.tileSize;
    g2.drawString("移動", textX, textY);
    textY += panel.tileSize;
    g2.drawString("選択/会話", textX, textY);
    textY += panel.tileSize;
    g2.drawString("アイテム", textX, textY);
    textY += panel.tileSize;
    g2.drawString("パース", textX, textY);
    textY += panel.tileSize;
    g2.drawString("操作方法", textX, textY);

    textX = frameX + panel.tileSize * 5;
    textY = frameY + panel.tileSize * 2;
    g2.drawString("WASD", textX, textY);
    textY += panel.tileSize;
    g2.drawString("ENTER", textX, textY);
    textY += panel.tileSize;
    g2.drawString("I", textX, textY);
    textY += panel.tileSize;
    g2.drawString("P", textX, textY);
    textY += panel.tileSize;
    g2.drawString("ESC", textX, textY);

    // Back
    textX = frameX + panel.tileSize;
    textY = frameY + panel.tileSize * 9;
    g2.drawString("Back", textX, textY);
    if (commandNum == 0) {
      g2.drawString(">", textX - 25, textY);
      if (panel.keyManager.enterPressed) {
        subState = 0;
      }
    }
  }

  public void drawSubWindow(int x, int y, int width, int height) {
    g2.setColor(new Color(0, 0, 0, 210));
    g2.fillRoundRect(x, y, width, height, 35, 35);
    g2.setColor(new Color(255, 255, 255));
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
  }

  public int getXForCenteredText(String text) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return panel.screenWidth / 2 - length / 2;
  }

  public int getXForAlignToRightText(String text, int tailX) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return tailX - length;
  }

  public void addMessage(String text) {
    message.add(text);
    messageCounter.add(0);
  }

  public int getItemIndexOnSlot() {
    return slotCol + (slotRow * 5);
  }

  /**
   * ×リスト6.16 case文の追加漏れ
   */
  private int attackPower(MagicType magicType, Player player) {
    int attackPower = 0;

    switch (magicType) {
      case FIRE:
        attackPower = player.attack.value() * 2;
        break;
      case SHIDEN:
        attackPower = player.attack.value() * 3;
        break;
      // case HELL_FIRE の追加を忘れていた
    }

    return attackPower;
  }

  private int attackPowerByPhysicalAttack(PhysicalAttackType physicalAttackType, Player player) {
    int attackPower = 0;

    switch (physicalAttackType) {
      case PUNCH:
        attackPower = (int)(player.attack.value() * 1.5);
        break;
      case KICK:
        attackPower = player.attack.value() * 2;
        break;
    }

    return attackPower;
  }

  /**
   * ×リスト9.17 リフレクションを用いた値の書き換え
   * ×リスト9.18 不正な値に書き換わってしまう
   */
  private void levelUp() {
    try {
      Level level = Level.initialize();
      System.out.println("Level : " + level.value()); // 1

      Field field = Level.class.getDeclaredField("value");

      field.setAccessible(true);
      field.setInt(level, 999);

      System.out.println("Level : " + level.value()); // 999 不正な値に書き換わってしまう

      panel.player.level = level;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
