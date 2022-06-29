/*
 * 装備中の装備一覧
 * ○リスト5.31 詳細なロジックは呼ぶ側ではなく、呼ばれる側に実装しよう
 */
package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.ItemWeapon;
import lombok.Getter;

/**
 * 装備一覧
 */
@Getter
public class Equipments {
  private Equipment head;
  private Equipment armor;
  private Equipment arm;

  public Equipments() {
    // TODO: 各装備のEMPTYを使うよう修正すること
    head = ItemWeapon.EMPTY;
    armor = ItemWeapon.EMPTY;
    arm = ItemWeapon.EMPTY;
  }

  /**
   * 鎧を装備する
   * @param newArmor 装備する鎧
   */
  public void equipArmor(final Equipment newArmor) {
    armor = newArmor;
  }

  /**
   * 全装備を解除する
   */
  public void deactivateAll() {
    // TODO: 各装備のEMPTYを使うよう修正すること
    head = ItemWeapon.EMPTY;
    armor = ItemWeapon.EMPTY;
    arm = ItemWeapon.EMPTY;
  }

  public boolean has(Equipment equipment) {
    // TODO: 他のも
    return armor == ItemWeapon.EMPTY;
  }
}
