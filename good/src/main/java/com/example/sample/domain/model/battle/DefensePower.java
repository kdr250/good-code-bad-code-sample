package com.example.sample.domain.model.battle;

import lombok.Getter;

/**
 * 防御力
 */
@Getter
public class DefensePower {
  private static final int MIN = 0;
  private final int value;

  public DefensePower(final int value) {
    if (value < MIN) {
      throw new IllegalArgumentException();
    }

    this.value = value;
  }
}
