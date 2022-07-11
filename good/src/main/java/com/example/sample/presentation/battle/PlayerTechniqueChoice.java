package com.example.sample.presentation.battle;

public enum PlayerTechniqueChoice {
  ONE,
  TWO,
  THREE,
  FOUR;

  public PlayerTechniqueChoice down() {
    PlayerTechniqueChoice[] values = values();
    int ordinal = ordinal();
    return values[ordinal + 1 < values.length ? ordinal + 1 : 0];
  }

  public PlayerTechniqueChoice up() {
    PlayerTechniqueChoice[] values = values();
    int ordinal = ordinal();
    return values[ordinal - 1 >= 0 ? ordinal - 1 : values.length - 1];
  }
}
