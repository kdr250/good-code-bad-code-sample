package com.example.sample.domain.model.battle;

/**
 * ダメージ
 */
public class Damage {
  private final int value;
  private static final int MIN = 1;

  private Damage(final int value) {
    if (value < MIN) {
      throw new IllegalArgumentException();
    }

    this.value = value;
  }

  public static Damage of(final int value) {
    int correctedValue = Math.max(value, MIN);
    return new Damage(correctedValue);
  }

  public Damage reduce(final DefensePower defensePower) {
    int correctedValue = value - defensePower.value();
    return of(correctedValue);
  }

  public int value() {
    return value;
  }
}
