/*
 * ○リスト5.29 魔法力に関係するロジックをカプセル化
 * を参考にしたクラス
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
  public static final MagicPoint NONE = new MagicPoint(MIN);

  public MagicPoint(final int currentAmount, final int originalMaxAmount) {
    if (currentAmount < MIN || currentAmount > originalMaxAmount) {
      throw new IllegalArgumentException();
    }

    this.currentAmount = currentAmount;
    this.originalMaxAmount = originalMaxAmount;
    this.maxIncrements = new ArrayList<>();
  }

  public MagicPoint(final int amount) {
    this.currentAmount = amount;
    this.originalMaxAmount = amount;
    this.maxIncrements = new ArrayList<>();
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
  public void recover(final MagicPoint recoveryAmount) {
    currentAmount = Math.min(currentAmount + recoveryAmount.currentAmount, max());
  }

  public void recoverOriginalMax() {
    maxIncrements.clear();
    currentAmount = originalMaxAmount;
  }

  /**
   * 魔法力最大値を増やす
   * @param maxIncrement 最大値増量
   */
  public void addMaxIncrements(final int maxIncrement) {
    maxIncrements.add(maxIncrement);
  }

  public void removeMaxIncrements(final int maxIncrement) {
    maxIncrements.remove((Integer)maxIncrement);
  }

  public void consume(final MagicPoint consumeAmount) {
    currentAmount = Math.max(currentAmount - consumeAmount.currentAmount, MIN);
  }

  public boolean canAttack(final MagicPoint cost) {
    return currentAmount >= cost.currentAmount;
  }
}
