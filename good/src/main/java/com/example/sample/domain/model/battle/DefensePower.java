package com.example.sample.domain.model.battle;

/**
 * 防御力
 */
public class DefensePower {
  private static final int MIN = 0;
  private final int value;
  public static DefensePower NONE = new DefensePower(MIN);

  public DefensePower(final int value) {
    if (value < MIN) {
      throw new IllegalArgumentException();
    }

    this.value = value;
  }

  public DefensePower reinforce(final DefensePower defensePower) {
    int correctedValue = value + defensePower.value;
    return new DefensePower(correctedValue);
  }

  public int value() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
