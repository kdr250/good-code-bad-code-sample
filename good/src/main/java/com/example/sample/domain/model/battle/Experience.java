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
    this.value = value;
    this.nextLevelValue = nextLevelValue;
  }
}
