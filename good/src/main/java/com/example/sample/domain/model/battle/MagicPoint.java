/*
 * ○リスト5.29 魔法力に関係するロジックをカプセル化
 */
package com.example.sample.domain.model.battle;

import java.util.ArrayList;
import java.util.List;

/**
 * 魔法力
 */
public class MagicPoint {
  private int currentAmount;
  private int originalMaxAmount;
  private final List<Integer> maxIncrements;
  private static final int MIN = 0;

  public MagicPoint(final int currentAmount, final int originalMaxAmount) {
    if (currentAmount < MIN || currentAmount > originalMaxAmount) {
      throw new IllegalArgumentException();
    }

    this.currentAmount = currentAmount;
    this.originalMaxAmount = originalMaxAmount;
    maxIncrements = new ArrayList<>();
  }

  /** @return 現在の魔法力残量 */
  public int current() {
    return currentAmount;
  }

  /** @return 魔法力の最大量 */
  public int max() {
    int amount = originalMaxAmount;
    for (int each : maxIncrements) {
      amount += each;
    }
    return amount;
  }

  /**
   * 魔法力を回復する
   * @param recoveryAmount 回復量
   */
  public void recover(final int recoveryAmount) {
    currentAmount = Math.min(currentAmount + recoveryAmount, max());
  }
}