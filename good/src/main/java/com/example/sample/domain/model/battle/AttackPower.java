/*
 * ○リスト4.18 不変で堅牢になったAttackPowerクラス
 * を参考にしたクラス
 */
package com.example.sample.domain.model.battle;

/**
 * 攻撃力
 */
public class AttackPower {
  private static final int MIN = 0;
  private final int value; // finalで不変にする
  public static AttackPower NONE = new AttackPower(MIN);

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

  public Damage toDamage() {
    return Damage.of(value);
  }

  public int value() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
