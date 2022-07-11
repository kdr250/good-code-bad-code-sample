package com.example.sample.domain.model.battle;

/**
 * 経験値
 */
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

  public boolean needsLevelUpIfGained(final Experience increment) {
    return nextLevelValue <= value + increment.value;
  }

  public Experience gain(final Experience increment) {
    int newValue = (value + increment.value) % nextLevelValue;
    int newNextLevelValue = needsLevelUpIfGained(increment) ? (int)(nextLevelValue * 1.5) : nextLevelValue;
    return new Experience(newValue, newNextLevelValue);
  }

  public int untilNextLevel() {
    return nextLevelValue - value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
