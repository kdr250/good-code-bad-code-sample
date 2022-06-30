package com.example.sample.presentation.battle;

import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;

@RequiredArgsConstructor
@Component
public class BattleController {

  private final ApplicationContext applicationContext;

  private Player player;
  private Enemy enemy;

  private BattleView battleView;

  private BattleViewState battleViewState;
  private PlayerActionChoice playerActionChoice;
  private PlayerTechniqueChoice playerTechniqueChoice;

  private int fpsCounter = 0;

  public void start(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
    this.battleView = new BattleView(player, enemy);
    battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
    playerActionChoice = PlayerActionChoice.ATTACK;
    playerTechniqueChoice = PlayerTechniqueChoice.ONE;
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (player == null || enemy == null || battleView == null) return;

    fpsCounter++;
    if (fpsCounter <= 5) return;
    fpsCounter = 0;

    if (battleViewState == BattleViewState.SELECTING_PLAYER_ACTION) {
      switch (keyInputType) {
        case UP:
        case LEFT:
          playerActionChoice = playerActionChoice.up();
          break;
        case DOWN:
        case RIGHT:
          playerActionChoice = playerActionChoice.down();
          break;
        case DECIDE:
          if (playerActionChoice == PlayerActionChoice.ATTACK) {
            battleViewState = BattleViewState.SELECTING_PLAYER_TECHNIQUE;
          }
          if (playerActionChoice == PlayerActionChoice.ESCAPE) {
            WorldMapController worldMapController = (WorldMapController) applicationContext.getBean("worldMapController");
            worldMapController.removeEnemy(enemy);
            gameMode.worldMap();
          }
          return;
      }
    }

    if (battleViewState == BattleViewState.SELECTING_PLAYER_TECHNIQUE) {
      switch (keyInputType) {
        case UP:
        case LEFT:
          playerTechniqueChoice = playerTechniqueChoice.up();
          break;
        case DOWN:
        case RIGHT:
          playerTechniqueChoice = playerTechniqueChoice.down();
          break;
        case DECIDE:
          // TODO: Player->Enemyへのダメージ処理
          // TODO: Enemyの生存判定処理
          battleViewState = BattleViewState.PLAYER_TECHNIQUE_RESULT;
          return;
      }
    }

    if (battleViewState == BattleViewState.PLAYER_TECHNIQUE_RESULT) {
      if (keyInputType == KeyInputType.DECIDE) {
        // TODO: 後で削除すること
        int random = (int)(Math.random() * 100);
        if (random > 50) {
          battleViewState = BattleViewState.ENEMY_ACTION_RESULT;
        } else {
          battleViewState = BattleViewState.BATTLE_RESULT_PLAYER_WIN;
        }
        return;
      }
    }

    if (battleViewState == BattleViewState.ENEMY_ACTION_RESULT) {
      if (keyInputType == KeyInputType.DECIDE) {
        // TODO: Playerの生存判定処理
        int random = (int)(Math.random() * 100);
        if (random > 50) {
          battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
        } else {
          battleViewState = BattleViewState.BATTLE_RESULT_PLAYER_LOSE;
        }
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_WIN) {
      if (keyInputType == KeyInputType.DECIDE) {
        // TODO: 経験値・レベルUP
        WorldMapController worldMapController = (WorldMapController) applicationContext.getBean("worldMapController");
        worldMapController.removeEnemy(enemy);
        gameMode.worldMap();
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_LOSE) {
      if (keyInputType == KeyInputType.DECIDE) {
        // TODO: 装備をdeactivateしてスタート位置に戻す
        WorldMapController worldMapController = (WorldMapController) applicationContext.getBean("worldMapController");
        worldMapController.removeEnemy(enemy);
        gameMode.worldMap();
        return;
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || enemy == null || battleView == null) return;

    battleView.draw(g2, battleViewState, playerActionChoice, playerTechniqueChoice);
  }
}
