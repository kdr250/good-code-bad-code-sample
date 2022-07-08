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

  public void changeToWorldMap() {
    gameModeType = GameModeType.WORLD_MAP;
  }

  public boolean isWorldMap() {
    return gameModeType == GameModeType.WORLD_MAP;
  }

  public void changeToBattle() {
    gameModeType = GameModeType.BATTLE;
  }

  public boolean isBattle() {
    return gameModeType == GameModeType.BATTLE;
  }

  public void changeToDisplayItemList() {
    gameModeType = GameModeType.DISPLAY_ITEM_LIST;
  }

  public boolean isDisplayingItemList() {
    return gameModeType == GameModeType.DISPLAY_ITEM_LIST;
  }

  public void changeToGameClear() {
    gameModeType = GameModeType.GAME_CLEAR;
  }

  public boolean isGameCleared() {
    return gameModeType == GameModeType.GAME_CLEAR;
  }
}
