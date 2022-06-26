/*
 * ○リスト4.18 不変で堅牢になったAttackPowerクラス
 */
package com.example.sample.domain.model.battle;

import lombok.Getter;

/**
 * 攻撃力
 */
@Getter
public class AttackPower {
  private static final int MIN = 0;
  private final int value; // finalで不変にする

  public AttackPower(final int value) {
    if (value < MIN) {
      throw new IllegalArgumentException();
    }

    this.value = value;
  }

  /**
   * 攻撃力を強化する
   * @param increment 攻撃力の増分
   * @return 強化された攻撃力
   */
  public AttackPower reinForce(final AttackPower increment) {
    return new AttackPower(this.value + increment.value);
  }
}
