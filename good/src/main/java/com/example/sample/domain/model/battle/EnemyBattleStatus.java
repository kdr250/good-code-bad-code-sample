package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.ItemType;

/**
 * 敵の戦闘ステータス
 */
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
    AttackPower attackPower = new AttackPower(3);
    Experience experience = Experience.enemyExperience(2);
    return new EnemyBattleStatus(hitPoint, attackPower, experience);
  }

  public HitPoint hitPoint() {
    return hitPoint;
  }

  public void damageHitPoint(final Damage damage) {
    hitPoint = hitPoint.damage(damage);
  }

  public AttackPower attackPower() {
    return attackPower;
  }

  public boolean isDead() {
    return hitPoint.isZero();
  }

  public Experience experience() {
    return experience;
  }

  public ItemType dropItemType() {
    return dropItemType;
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
