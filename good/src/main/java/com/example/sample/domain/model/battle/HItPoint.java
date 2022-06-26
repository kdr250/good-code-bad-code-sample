/*
 * ヒットポイント（HP）を表現するクラス
 * ○リスト2.9 クラスにすると強く関係するデータとロジックをまとめられる
 */
package com.example.sample.domain.model.battle;

/**
 * ヒットポイント
 */
public class HItPoint {
  private static final int MIN = 0;
  private static final int MAX = 999;
  private final int value;

  public HItPoint(final int value) {
    if (value < MIN) throw new IllegalArgumentException(MIN + "以上を指定してください");
    if (MAX < value) throw new IllegalArgumentException(MAX + "以下を指定してください");

    this.value = value;
  }

  // ダメージを受ける
  public HItPoint damage(final int damageAmount) {
    final int damaged = value - damageAmount;
    final int corrected = Math.max(damaged, MIN);
    return new HItPoint(corrected);
  }

  // 回復する
  public HItPoint recover(final int recoveryAmount) {
    final int recovered = value + recoveryAmount;
    final int corrected = Math.max(MAX, recovered);
    return new HItPoint(corrected);
  }
}
