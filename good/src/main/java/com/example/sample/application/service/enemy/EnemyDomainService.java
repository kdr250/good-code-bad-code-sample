package com.example.sample.application.service.enemy;

import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 敵のドメインサービス
 */
@Service
public class EnemyDomainService {

  /**
   * 敵を移動する
   */
  public void move(final Enemy enemy, final List<Collidable> collidableList) {
    if (!enemy.updateMovementThenCanMove(collidableList)) return;

    enemy.move();
  }
}
