package com.example.sample.domain.model.gamemode;

import lombok.Getter;

@Getter
public class GameMode {
  private GameModeType gameModeType;

  public GameMode(final GameModeType gameModeType) {
    this.gameModeType = gameModeType;
  }

  public void gameClear() {
    gameModeType = GameModeType.GAME_CLEAR;
  }

  public boolean isGameCleared() {
    return gameModeType == GameModeType.GAME_CLEAR;
  }
}
