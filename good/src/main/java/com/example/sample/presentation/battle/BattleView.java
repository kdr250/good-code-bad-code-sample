package com.example.sample.presentation.battle;

import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.technique.Technique;
import com.example.sample.presentation.GamePanel;

import java.awt.*;
import java.util.List;

public class BattleView {

  private final Player player;
  private final Enemy enemy;
  private final ItemImage crystalBlank;
  private final ItemImage crystalFull;

  public BattleView(final Player player, final Enemy enemy, final ItemImage crystalBlank, final ItemImage crystalFull) {
    this.player = player;
    this.enemy = enemy;
    this.crystalBlank = crystalBlank;
    this.crystalFull = crystalFull;
  }

  public void draw(Graphics2D g2, BattleViewState battleViewState, PlayerActionChoice playerActionChoice, PlayerTechniqueChoice playerTechniqueChoice) {
    drawBattleScreen(g2, battleViewState, playerActionChoice, playerTechniqueChoice);
  }

  private void drawBattleScreen(Graphics2D g2, BattleViewState battleViewState, PlayerActionChoice playerActionChoice, PlayerTechniqueChoice playerTechniqueChoice) {
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
        g2.drawImage(crystalFull.getBufferedImage(), playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
      } else {
        g2.drawImage(crystalBlank.getBufferedImage(), playerLifeBarX + i * 15, playerLifeBarY + 20, 30, 30, null);
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
    }

    if (battleViewState == BattleViewState.SELECTING_PLAYER_TECHNIQUE) {
      List<Technique> playerTechniques = player.getPlayerBattleStatus().getPlayerTechniques().getList();
      for (int i = 0; i < playerTechniques.size(); i++) {
        Technique technique = playerTechniques.get(i);
        g2.drawString(technique.displayName(), Tile.TILE_SIZE + Tile.TILE_SIZE * 10, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * (i + 1));
        if (i == playerTechniqueChoice.ordinal()) g2.drawString(technique.description(), Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      }
      g2.drawString(">", Tile.TILE_SIZE + Tile.TILE_SIZE * 10 - 15, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * (playerTechniqueChoice.ordinal() + 1));
    }

    if (battleViewState == BattleViewState.PLAYER_TECHNIQUE_RESULT) {
      g2.drawString("敵にXXダメージ！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
    }

    if (battleViewState == BattleViewState.PLAYER_TECHNIQUE_FAIL) {
      g2.drawString("魔力が足りない！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
    }

    if (battleViewState == BattleViewState.ENEMY_ACTION_RESULT) {
      g2.drawString("プレイヤーにXXダメージ！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_WIN) {
      g2.drawString("プレイヤーの勝利！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_LOSE) {
      g2.drawString("プレイヤーの敗北！", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE);
      g2.drawString("> Press Enter", Tile.TILE_SIZE + 30, GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2);
    }
  }
}
