package com.example.sample.presentation.battle;

public enum PlayerActionChoice {
  ATTACK,
  ESCAPE;

  public PlayerActionChoice down() {
    PlayerActionChoice[] values = values();
    int ordinal = ordinal();
    return values[ordinal + 1 < values.length ? ordinal + 1 : 0];
  }

  public PlayerActionChoice up() {
    PlayerActionChoice[] values = values();
    int ordinal = ordinal();
    return values[ordinal - 1 >= 0 ? ordinal - 1 : values.length - 1];
  }
}
