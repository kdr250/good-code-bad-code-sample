package com.example.sample.domain.model.gamemode;

import lombok.Getter;

@Getter
public class GameMode {
  private GameModeType gameModeType;

  public GameMode(final GameModeType gameModeType) {
    this.gameModeType = gameModeType;
  }

  public void worldMap() {
    gameModeType = GameModeType.WORLD_MAP;
  }

  public boolean isWorldMap() {
    return gameModeType == GameModeType.WORLD_MAP;
  }

  public void toggleDisplayItemList() {
    if (gameModeType == GameModeType.WORLD_MAP) {
      gameModeType = GameModeType.DISPLAY_ITEM_LIST;
      return;
    }
    if (gameModeType == GameModeType.DISPLAY_ITEM_LIST ) {
      gameModeType = GameModeType.WORLD_MAP;
    }
  }

  private void displayItemList() {
    gameModeType = GameModeType.DISPLAY_ITEM_LIST;
  }

  public boolean isDisplayingItemList() {
    return gameModeType == GameModeType.DISPLAY_ITEM_LIST;
  }

  public void gameClear() {
    gameModeType = GameModeType.GAME_CLEAR;
  }

  public boolean isGameCleared() {
    return gameModeType == GameModeType.GAME_CLEAR;
  }
}
