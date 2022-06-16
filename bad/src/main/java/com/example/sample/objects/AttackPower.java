package com.example.sample.objects;

/**
 * ×リスト4.6 攻撃力を表現するクラス
 */
public class AttackPower {
  static final int MIN = 0;
  int value; // finalが付いていないので可変

  public AttackPower(int value) {
    if (value < MIN) {
      throw new IllegalArgumentException();
    }

    this.value = value;
  }

  /**
   * ×リスト4.13 攻撃力を変化させるメソッドを追加
   * 攻撃力を強化する
   * @param increment 攻撃力の増分
   */
  public void reinForce(int increment) {
    value += increment;
  }

  /**
   * ×リスト4.13 攻撃力を変化させるメソッドを追加
   * 無力化する
   */
  public void disable() {
    value = MIN;
  }

  public int value() {
    return value;
  }
}
