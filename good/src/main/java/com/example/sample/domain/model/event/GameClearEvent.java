package com.example.sample.domain.model.event;

import com.example.sample.domain.model.gamemode.GameMode;

/**
 * ゲームクリアイベント
 */
public class GameClearEvent implements GameModeEvent {

  @Override
  public boolean execute(GameMode gameMode) {
    gameMode.gameClear();
    return true;
  }
}
