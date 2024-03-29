package com.example.sample.presentation.battle;

import com.example.sample.application.service.item.ItemQueryService;
import com.example.sample.application.service.player.PlayerDomainService;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.battle.technique.Technique;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BattleController {

  private final ApplicationContext applicationContext;
  private final PlayerDomainService playerDomainService;
  private final ItemQueryService itemQueryService;

  private Player player;
  private Enemy enemy;

  private BattleView battleView;

  private BattleViewState battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
  private PlayerActionChoice playerActionChoice = PlayerActionChoice.ATTACK;
  private PlayerTechniqueChoice playerTechniqueChoice = PlayerTechniqueChoice.ONE;

  private int fpsCounter = 0;

  public BattleController(ApplicationContext applicationContext, PlayerDomainService playerDomainService, ItemQueryService itemQueryService) {
    this.applicationContext = applicationContext;
    this.playerDomainService = playerDomainService;
    this.itemQueryService = itemQueryService;
  }

  public void setUp(Player player, Enemy enemy, ItemImage crystalBlank, ItemImage crystalFull) {
    this.player = player;
    this.enemy = enemy;
    this.battleView = new BattleView(player, enemy, crystalBlank, crystalFull);
    this.battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
    this.playerActionChoice = PlayerActionChoice.ATTACK;
    this.playerTechniqueChoice = PlayerTechniqueChoice.ONE;
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
            gameMode.changeToWorldMap();
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
          Technique technique = player.techniques().get(playerTechniqueChoice.ordinal());

          if (!player.canAttack(technique)) {
            battleViewState = BattleViewState.PLAYER_TECHNIQUE_FAIL;
            return;
          }

          playerDomainService.attackEnemy(player, technique, enemy);
          battleViewState = BattleViewState.PLAYER_TECHNIQUE_RESULT;
          return;
      }
    }

    if (battleViewState == BattleViewState.PLAYER_TECHNIQUE_RESULT) {
      if (keyInputType == KeyInputType.DECIDE) {
        if (enemy.isDead()) {
          playerDomainService.pickUpDropItem(player, enemy);
          battleViewState = BattleViewState.BATTLE_RESULT_PLAYER_WIN;
          return;
        }

        playerDomainService.damaged(player, enemy);
        battleViewState = BattleViewState.ENEMY_ACTION_RESULT;
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
        if (player.isDead()) {
          battleViewState = BattleViewState.BATTLE_RESULT_PLAYER_LOSE;
          return;
        }

        battleViewState = BattleViewState.SELECTING_PLAYER_ACTION;
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_WIN) {
      if (keyInputType == KeyInputType.DECIDE) {
        boolean isLevelUp = player.gainExperienceAndIsLevelUp(enemy.experience());
        if (isLevelUp) {
          battleViewState = BattleViewState.BATTLE_RESULT_PLAYER_LEVEL_UP;
          return;
        }
        WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
        worldMapController.removeEnemy(enemy);
        gameMode.changeToWorldMap();
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_LEVEL_UP) {
      if (keyInputType == KeyInputType.DECIDE) {
        WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
        worldMapController.removeEnemy(enemy);
        gameMode.changeToWorldMap();
        return;
      }
    }

    if (battleViewState == BattleViewState.BATTLE_RESULT_PLAYER_LOSE) {
      if (keyInputType == KeyInputType.DECIDE) {
        player.deactivateAllEquipments();
        WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
        worldMapController.reset();
        gameMode.changeToWorldMap();
        return;
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || enemy == null || battleView == null) return;

    battleView.draw(g2, battleViewState, playerActionChoice, playerTechniqueChoice);
  }
}
