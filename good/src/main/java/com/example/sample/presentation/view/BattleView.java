package com.example.sample.presentation.view;

import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.presentation.GamePanel;

import java.awt.*;

public class BattleView {

  private final Player player;
  private final Enemy enemy;

  private BattleViewState battleViewState;
  private PlayerActionChoice playerActionChoice;
  private PlayerTechniqueChoice playerTechniqueChoice;

  public BattleView(final Player player, final Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
    battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
    playerActionChoice = PlayerActionChoice.ATTACK;
    playerTechniqueChoice = PlayerTechniqueChoice.ONE;
  }

  public void draw(Graphics2D g2) {
    drawBattleScreen(g2);
  }

  private void drawBattleScreen(Graphics2D g2) {
    g2.setColor(Color.black);
    g2.fillRect(Tile.TILE_SIZE, Tile.TILE_SIZE, GamePanel.screenWidth - Tile.TILE_SIZE * 2, GamePanel.screenHeight - Tile.TILE_SIZE * 2);
    // モンスターの画像
    int monsterImageX = GamePanel.screenWidth / 2 - Tile.TILE_SIZE * 5;
    int monsterImageY = (int)(Tile.TILE_SIZE * 1.5);
    int monsterImageWidth = 150;
    int monsterImageHeight = 150;
    g2.drawImage(enemy.getImage(), monsterImageX, monsterImageY, monsterImageWidth, monsterImageHeight, null);
    // モンスターのライフバー
    int monsterLifeBarX = monsterImageX + 10;
    int monsterLifeBarY = monsterImageY + monsterImageHeight;
    int monsterLifeBarMaxWidth = 120;
    g2.setColor(Color.red);
    g2.setStroke(new BasicStroke(3));
    g2.drawRect(monsterLifeBarX, monsterLifeBarY, monsterLifeBarMaxWidth, 20);
    double monsterLifeBarWidth = monsterLifeBarMaxWidth * ((double) enemy.getEnemyBattleStatus().getHitPoint().getValue() / (double) enemy.getEnemyBattleStatus().getHitPoint().getMaxValue());
    g2.fillRect(monsterLifeBarX, monsterLifeBarY, (int)monsterLifeBarWidth, 20);
    // プレイヤーの画像
    int playerImageX = GamePanel.screenWidth / 2 + Tile.TILE_SIZE * 2;
    int playerImageY = (int)(Tile.TILE_SIZE * 1.5);
    int playerImageWidth = 150;
    int playerImageHeight = 150;
    g2.drawImage(player.getImage(), playerImageX, playerImageY, playerImageWidth, playerImageHeight, null);
    // プレイヤーのライフバー
    int playerLifeBarX = playerImageX + 10;
    int playerLifeBarY = playerImageY + playerImageHeight;
    int playerLifeBarMaxWidth = 120;
    g2.drawRect(playerLifeBarX, playerLifeBarY, playerLifeBarMaxWidth, 20);
    double playerLifeBarWidth = playerLifeBarMaxWidth * ((double) player.getPlayerBattleStatus().getHitPoint().getValue() / (double) player.getPlayerBattleStatus().getHitPoint().getMaxValue());
    g2.fillRect(playerLifeBarX, playerLifeBarY, (int)playerLifeBarWidth, 20);
    // プレイヤーの魔法力
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(24f));
    for (int i = 0; i < player.getPlayerBattleStatus().getMagicPoint().max(); i++) {
      if (i < player.getPlayerBattleStatus().getMagicPoint().current()) {
        g2.drawString("⭐️", playerLifeBarX + i * 15, playerLifeBarY + 20);
        //g2.drawImage(crystalFull, playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
      } else {
        g2.drawString("★", playerLifeBarX + i * 15, playerLifeBarY + 20);
        //g2.drawImage(crystalBlank, playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
      }
    }

    g2.setFont(g2.getFont());
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(Tile.TILE_SIZE + 15, GamePanel.screenHeight / 2, Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 5 - 15, 10, 10);
    g2.drawRoundRect(Tile.TILE_SIZE + Tile.TILE_SIZE * 9 + 30, GamePanel.screenHeight / 2, Tile.TILE_SIZE * 4, Tile.TILE_SIZE * 5 - 15, 10, 10);

    if (battleViewState == BattleViewState.SELECTING_PLAYER_ACTION) {
      g2.drawString(enemy.getName() + "が現れた", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("戦う", Tile.TILE_SIZE + Tile.TILE_SIZE * 10, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("にげる", Tile.TILE_SIZE + Tile.TILE_SIZE * 10, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);

      g2.drawString(">", Tile.TILE_SIZE + Tile.TILE_SIZE * 10 - 15, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * (playerActionChoice.ordinal() + 1));
//      if (GamePanel.keyManager.enterPressed) {
//        subState = commandNum == 0 ? 1 : 4;
//        commandNum = 0;
//        GamePanel.keyManager.enterPressed = false;
//      }
    }
//    else if (subState == 1) {
//      isLackOfMana = false;
//      String description = player.techniques[commandNum] instanceof MagicType ?
//          GamePanel.magicManager.description((MagicType) player.techniques[commandNum]) :
//          GamePanel.physicalAttackManager.description((PhysicalAttackType) player.techniques[commandNum]);
//      g2.drawString(description, Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
//      for (int i = 0; i < player.techniques.length; i++) {
//        String name = player.techniques[i] instanceof MagicType ?
//            GamePanel.magicManager.getName((MagicType) player.techniques[i]) :
//            GamePanel.physicalAttackManager.getName((PhysicalAttackType) player.techniques[i]);
//        g2.drawString(name, Tile.TILE_SIZE + Tile.TILE_SIZE * 10, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * (i + 1));
//      }
//      g2.drawString(">", Tile.TILE_SIZE + Tile.TILE_SIZE * 10 - 15, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * (commandNum + 1));
//      if (GamePanel.keyManager.enterPressed) {
//        GamePanel.keyManager.enterPressed = false;
//        int consumeMagicPoint = player.techniques[commandNum] instanceof MagicType ?
//            GamePanel.magicManager.costMagicPoint((MagicType) player.techniques[commandNum]) : 0;
//        if (player.magicPoint >= consumeMagicPoint) {
//          BattleTechnique technique = player.techniques[commandNum];
//          if (technique instanceof PhysicalAttackType) {
//            enemy.hitPoint -= attackPowerByPhysicalAttack((PhysicalAttackType)technique, player);
//          } else if (technique instanceof MagicType) {
//            player.magicPoint -= GamePanel.magicManager.costMagicPoint((MagicType)technique);
//            enemy.hitPoint -= attackPower((MagicType)technique, player);
//          }
//        } else {
//          isLackOfMana = true;
//        }
//        subState = 2;
//        return;
//      }
//    }
//    else if (subState == 2) {
//      if (isLackOfMana) {
//        g2.drawString("魔力が足りない!", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
//      } else {
//        int damage = 0;
//        BattleTechnique technique = player.techniques[commandNum];
//        if (technique instanceof PhysicalAttackType) {
//          damage = attackPowerByPhysicalAttack((PhysicalAttackType)technique, player);
//        } else if (technique instanceof MagicType) {
//          damage = attackPower((MagicType)technique, player);
//        }
//        g2.drawString(enemy.name + "に" + damage + "ダメージ！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
//      }
//      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
//      if (GamePanel.keyManager.enterPressed) {
//        GamePanel.keyManager.enterPressed = false;
//        if (isLackOfMana) {
//          subState = 1;
//          commandNum = 0;
//          return;
//        }
//        else if (enemy.hitPoint <= 0) {
//          enemy.addItemToItemList(player.inventory);
//          subState = 4;
//          commandNum = 0;
//          return;
//        }
//        int damageAmount = Math.max(enemy.attack.value() - player.totalDefense(), 1);
//        /** ×リスト2.7 どこかに書かれるヒットポイント減少ロジック */
//        player.hitPoint = player.hitPoint - damageAmount;
//        subState = 3;
//        commandNum = 0;
//      }
//    }
//    else if (subState == 3) {
//      g2.drawString("プレイヤーに" + Math.max(enemy.attack.value() - player.totalDefense(), 1) + "ダメージ！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
//      if (player.hitPoint <= 0) {
//        g2.drawString("敗北！防具が壊れた！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
//        player.deleteEquipmentFromItemList();
//        player.takeOffAllEquipments();
//      }
//      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 3);
//      if (GamePanel.keyManager.enterPressed) {
//        GamePanel.keyManager.enterPressed = false;
//        if (player.hitPoint <= 0) {
//          GamePanel.gameState = GamePanel.gameOverState;
//          subState = 0;
//          commandNum = 0;
//          return;
//        }
//        subState = 0;
//        commandNum = 0;
//      }
//    }
//    else if (subState == 4) {
//      String text;
//      if (enemy.hitPoint <= 0) {
//        levelUp();
//        text = "勝利！ " + player.inventory.get(player.inventory.size() - 1).name + "を手に入れた!";
//      } else {
//        text = "逃げた!";
//      }
//      g2.drawString(text, Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
//      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
//      if (GamePanel.keyManager.enterPressed) {
//        GamePanel.gameState = GamePanel.playState;
//        subState = 0;
//        commandNum = 0;
//        GamePanel.keyManager.enterPressed = false;
//        player.exp += enemy.exp;
//        for (int i = 0; i < GamePanel.monsters.length; i++) {
//          if (enemy == GamePanel.monsters[i]) {
//            enemy = null;
//            GamePanel.monsters[i] = null;
//          }
//        }
//      }
//    }
  }

  private enum BattleViewState {
    SELECTING_PLAYER_ACTION,
    SELECTING_PLAYER_TECHNIQUE,
    PLAYER_TECHNIQUE_RESULT,
    ENEMY_ACTION_RESULT,
    BATTLE_RESULT,
    ESCAPE;
  }

  private enum PlayerActionChoice {
    ATTACK,
    ESCAPE;
  }

  private enum PlayerTechniqueChoice {
    ONE,
    TWO,
    THREE,
    FOUR;
  }
}
