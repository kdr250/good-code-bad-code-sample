package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.ItemType;
import lombok.Getter;

@Getter
public class EnemyBattleStatus {
  private HitPoint hitPoint;
  private final AttackPower attackPower;
  private final Experience experience;
  private final ItemType dropItemType;

  private EnemyBattleStatus(final HitPoint hItPoint, final AttackPower attackPower, final Experience experience) {
    this.hitPoint = hItPoint;
    this.attackPower = attackPower;
    this.experience = experience;
    this.dropItemType = decideDropItemType();
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

  private ItemType decideDropItemType() {
    int random = (int)(Math.random() * 100);
    switch (random / 25) {
      case 0:
        return ItemType.POTION_RED;
      case 1:
        return ItemType.POTION_BLUE;
      default:
        return ItemType.KEY;
    }
  }
}
