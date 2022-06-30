package com.example.sample.presentation.battle;

import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.presentation.KeyInputType;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BattleController {
  private Player player;
  private Enemy enemy;

  private BattleView battleView;

  public void start(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
    this.battleView = new BattleView(player, enemy);
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (player == null || enemy == null || battleView == null) return;

    // TODO: Not Yet Implemented
  }

  public void draw(Graphics2D g2) {
    if (player == null || enemy == null || battleView == null) return;

    battleView.draw(g2);
  }
}
