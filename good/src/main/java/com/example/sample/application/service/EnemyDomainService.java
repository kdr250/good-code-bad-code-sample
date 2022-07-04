package com.example.sample.application.service;

import com.example.sample.domain.model.Enemy;
import org.springframework.stereotype.Service;

@Service
public class EnemyDomainService {

  public void applyDamage(Enemy enemy, int damageAmount) {
    enemy.damageHitPoint(damageAmount);
  }
}
