package com.example.sample.domain.model.gamemode;

import lombok.Getter;

/**
 * ゲームモード
 */
@Getter
public class GameMode {
  private GameModeType gameModeType;

  public GameMode(final GameModeType gameModeType) {
    this.gameModeType = gameModeType;
  }

  public void displayTitle() {
    gameModeType = GameModeType.DISPLAY_TITLE;
  }

  public boolean isDisplayingTitle() {
    return gameModeType == GameModeType.DISPLAY_TITLE;
  }

  public void worldMap() {
    gameModeType = GameModeType.WORLD_MAP;
  }

  public boolean isWorldMap() {
    return gameModeType == GameModeType.WORLD_MAP;
  }

  public void battle() {
    gameModeType = GameModeType.BATTLE;
  }

  public boolean isBattle() {
    return gameModeType == GameModeType.BATTLE;
  }

  public void displayItemList() {
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
