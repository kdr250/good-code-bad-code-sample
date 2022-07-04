package com.example.sample.domain.model.battle;

import lombok.Getter;

@Getter
public class EnemyBattleStatus {
  private HitPoint hitPoint;
  private final AttackPower attackPower;
  private final Experience experience;

  private EnemyBattleStatus(final HitPoint hItPoint, final AttackPower attackPower, final Experience experience) {
    this.hitPoint = hItPoint;
    this.attackPower = attackPower;
    this.experience = experience;
  }

  public static EnemyBattleStatus initialize() {
    HitPoint hitPoint = new HitPoint(6);
    AttackPower attackPower = new AttackPower(2);
    Experience experience = Experience.enemyExperience(2);
    return new EnemyBattleStatus(hitPoint, attackPower, experience);
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
