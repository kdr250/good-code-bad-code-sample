/*
 * ヒットポイント（HP）を表現するクラス
 * ○リスト2.9 クラスにすると強く関係するデータとロジックをまとめられる
 */
package com.example.sample.domain.model.battle;

import lombok.Getter;

/**
 * ヒットポイント
 */
@Getter
public class HitPoint {
  private static final int MIN = 0;
  private final int maxValue;
  private final int value;

  private HitPoint(final int value, final int maxValue) {
    if (value < MIN) throw new IllegalArgumentException(MIN + "以上を指定してください");
    if (maxValue < value) throw new IllegalArgumentException(maxValue + "以下を指定してください");

    this.maxValue = maxValue;
    this.value = value;
  }

  public HitPoint(final int value) {
    if (value < MIN) throw new IllegalArgumentException(MIN + "以上を指定してください");

    maxValue = value;
    this.value = value;
  }

  // ダメージを受ける
  public HitPoint damage(final int damageAmount) {
    final int damaged = value - damageAmount;
    final int corrected = Math.max(damaged, MIN);
    return new HitPoint(corrected, maxValue);
  }

  // 回復する
  public HitPoint recover(final int recoveryAmount) {
    final int recovered = value + recoveryAmount;
    final int corrected = Math.min(maxValue, recovered);
    return new HitPoint(corrected, maxValue);
  }
}
