package com.example.sample.domain.model.battle;

import lombok.Getter;

/**
 * 経験値
 */
@Getter
public class Experience {
  private int value;
  private final int nextLevelValue;

  public Experience(final int value, final int nextLevelValue) {
    if (nextLevelValue <= value) {
      throw new IllegalArgumentException();
    }

    this.value = value;
    this.nextLevelValue = nextLevelValue;
  }

  public static Experience enemyExperience(final int value) {
    return new Experience(value, value + 1);
  }

  public boolean needsLevelUpIfGained(final int increment) {
    return nextLevelValue <= value + increment;
  }

  public Experience gain(final int increment) {
    int newValue = (value + increment) % nextLevelValue;
    int newNextLevelValue = needsLevelUpIfGained(increment) ? (int)(nextLevelValue * 1.5) : nextLevelValue;
    return new Experience(newValue, newNextLevelValue);
  }

  public int untilNextLevel() {
    return nextLevelValue - value;
  }
}
