/*
 * ヒットポイント（HP）を表現するクラス
 * ○リスト2.9 クラスにすると強く関係するデータとロジックをまとめられる
 * を参考にしたクラス
 */
package com.example.sample.domain.model.battle;

/**
 * ヒットポイント
 */
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

  /** ダメージを受ける */
  public HitPoint damage(final Damage damage) {
    final int damaged = value - damage.value();
    final int corrected = Math.max(damaged, MIN);
    return new HitPoint(corrected, maxValue);
  }

  /** 回復する */
  public HitPoint recover(final HitPoint recoveryAmount) {
    final int recovered = value + recoveryAmount.value;
    final int corrected = Math.min(maxValue, recovered);
    return new HitPoint(corrected, maxValue);
  }

  public HitPoint recoverMax() {
    return new HitPoint(maxValue);
  }

  public boolean isZero() {
    return value == MIN;
  }

  public int value() {
    return value;
  }

  public int maxValue() {
    return maxValue;
  }
}
