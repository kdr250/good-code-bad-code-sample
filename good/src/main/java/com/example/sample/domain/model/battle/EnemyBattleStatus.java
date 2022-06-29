package com.example.sample.domain.model.battle;

import lombok.Getter;

@Getter
public class EnemyBattleStatus {
  private HitPoint hitPoint;
  private AttackPower attackPower;

  public EnemyBattleStatus(final HitPoint hItPoint, final AttackPower attackPower) {
    this.hitPoint = hItPoint;
    this.attackPower = attackPower;
  }
}
