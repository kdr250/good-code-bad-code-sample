package com.example.sample.presentation.battle;

import com.example.sample.application.service.EnemyDomainService;
import com.example.sample.application.service.PlayerDomainService;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.technique.Technique;
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
  private final PlayerDomainService playerDomainService;
  private final EnemyDomainService enemyDomainService;

  private Player player;
  private Enemy enemy;

  private BattleView battleView;

  private BattleViewState battleViewState;
  private PlayerActionChoice playerActionChoice;
  private PlayerTechniqueChoice playerTechniqueChoice;

  private int fpsCounter = 0;

  public void setUp(Player player, Enemy enemy, ItemImage crystalBlank, ItemImage crystalFull) {
    this.player = player;
    this.enemy = enemy;
    this.battleView = new BattleView(player, enemy, crystalBlank, crystalFull);
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
            WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
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
          Technique technique = player.getPlayerBattleStatus().getPlayerTechniques().getList().get(playerTechniqueChoice.ordinal());

          if (!player.canAttack(technique)) {
            battleViewState = BattleViewState.PLAYER_TECHNIQUE_FAIL;
            return;
          }

          player.consumeCostForAttack(technique);
          enemyDomainService.applyDamage(enemy, player.attack(technique));
          battleViewState = BattleViewState.PLAYER_TECHNIQUE_RESULT;
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

    if (battleViewState == BattleViewState.PLAYER_TECHNIQUE_FAIL) {
      if (keyInputType == KeyInputType.DECIDE) {
        battleViewState = BattleViewState.SELECTING_PLAYER_TECHNIQUE;
      }
      return;
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
        WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
        worldMapController.removeEnemy(enemy);
        gameMode.worldMap();
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_LOSE) {
      if (keyInputType == KeyInputType.DECIDE) {
        // TODO: 装備をdeactivateしてスタート位置に戻す
        WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
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
