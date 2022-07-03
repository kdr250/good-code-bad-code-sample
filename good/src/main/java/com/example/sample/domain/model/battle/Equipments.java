/*
 * 装備中の装備一覧
 * ○リスト5.31 詳細なロジックは呼ぶ側ではなく、呼ばれる側に実装しよう
 */
package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.item.ItemShieldNormal;
import com.example.sample.domain.model.item.ItemWeapon;
import lombok.Getter;

/**
 * 装備一覧
 */
@Getter
public class Equipments {
  private Equipment armor;
  private Equipment arm;
  private Equipment weapon;

  public Equipments() {
    armor = ItemWeapon.EMPTY;
    arm = ItemShieldNormal.EMPTY;
    weapon = ItemWeapon.EMPTY;
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
    armor = ItemWeapon.EMPTY;
    arm = ItemShieldNormal.EMPTY;
    weapon = ItemWeapon.EMPTY;
  }

  public void equip(Equipment equipment) {
    switch (equipment.getEquipmentType()) {
      case ARMOR:
        equipArmor(equipment);
        break;
      case ARM:
        equipArm(equipment);
        break;
      case WEAPON:
        equipWeapon(equipment);
        break;
    }
  }

  public boolean has(EquipmentType equipmentType) {
    switch (equipmentType) {
      case ARMOR:
        return armor != ItemWeapon.EMPTY;
      case ARM:
        return arm != ItemShieldNormal.EMPTY;
      case WEAPON:
        return weapon != ItemWeapon.EMPTY;
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
}
