/*
 * 装備中の装備一覧
 * ○リスト5.31 詳細なロジックは呼ぶ側ではなく、呼ばれる側に実装しよう
 * ○リスト9.11 「装備なし」をnullでない方法で表現
 * を参考にしたクラス
 */
package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;

/**
 * 装備一覧
 */
public class Equipments {
  private Equipment armor;
  private Equipment arm;
  private Equipment weapon;

  public Equipments() {
    armor = Equipment.EMPTY;
    arm = Equipment.EMPTY;
    weapon = Equipment.EMPTY;
  }

  /**
   * 鎧を装備する
   * @param newArmor 装備する鎧
   */
  private void equipArmor(final Equipment newArmor) {
    armor = newArmor;
  }

  /**
   * 盾を装備する
   * @param newArm 装備する盾
   */
  private void equipArm(final Equipment newArm) {
    arm = newArm;
  }

  /**
   * 武器を装備する
   * @param newWeapon 装備する武器
   */
  private void equipWeapon(final Equipment newWeapon) {
    weapon = newWeapon;
  }

  /**
   * 全装備を解除する
   */
  public void deactivateAll() {
    armor = Equipment.EMPTY;
    arm = Equipment.EMPTY;
    weapon = Equipment.EMPTY;
  }

  public void deactivate(EquipmentType equipmentType) {
    switch (equipmentType) {
      case ARMOR:
        armor = Equipment.EMPTY;
        break;
      case ARM:
        arm = Equipment.EMPTY;
        break;
      case WEAPON:
        weapon = Equipment.EMPTY;
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  public void equip(Equipment equipment) {
    switch (equipment.equipmentType()) {
      case ARMOR:
        equipArmor(equipment);
        break;
      case ARM:
        equipArm(equipment);
        break;
      case WEAPON:
        equipWeapon(equipment);
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  public boolean has(EquipmentType equipmentType) {
    switch (equipmentType) {
      case ARMOR:
        return armor != Equipment.EMPTY;
      case ARM:
        return arm != Equipment.EMPTY;
      case WEAPON:
        return weapon != Equipment.EMPTY;
      default:
        throw new IllegalArgumentException();
    }
  }

  public Equipment getEquipment(EquipmentType equipmentType) {
    switch (equipmentType) {
      case ARMOR:
        return getArmor();
      case ARM:
        return getArm();
      case WEAPON:
        return getWeapon();
      default:
        throw new IllegalArgumentException();
    }
  }

  public AttackPower totalAttackPower() {
    return weapon.attackPower()
        .reinForce(armor.attackPower())
        .reinForce(arm.attackPower());
  }

  public DefensePower totalDefensePower() {
    return armor.defensePower()
        .reinforce(arm.defensePower())
        .reinforce(weapon.defensePower());
  }

  public Equipment getArmor() {
    return armor;
  }

  public Equipment getArm() {
    return arm;
  }

  public Equipment getWeapon() {
    return weapon;
  }
}
