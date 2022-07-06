package com.example.sample.application.service;

import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyDomainService {

  public void move(final Enemy enemy, final List<Collidable> collidableList, final Player player, final GameMode gameMode) {
    if (!enemy.updateMovementThenCanMove(collidableList)) return;

    enemy.move();
  }
}
