package com.example.sample.domain.model.battle;

import lombok.Getter;

@Getter
public class EnemyBattleStatus {
  private HitPoint hitPoint;
  private AttackPower attackPower;

  private EnemyBattleStatus(final HitPoint hItPoint, final AttackPower attackPower) {
    this.hitPoint = hItPoint;
    this.attackPower = attackPower;
  }

  public static EnemyBattleStatus initialize() {
    HitPoint hitPoint = new HitPoint(6);
    AttackPower attackPower = new AttackPower(2);
    return new EnemyBattleStatus(hitPoint, attackPower);
  }

  public void damageHitPoint(final int damageAmount) {
    hitPoint = hitPoint.damage(damageAmount);
  }

  public int attack() {
    return attackPower.getValue();
  }

  public boolean isDead() {
    return hitPoint.isZero();
  }
}
