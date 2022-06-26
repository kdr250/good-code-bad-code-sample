package com.example.sample.domain.model.event;

import com.example.sample.domain.model.gamemode.GameMode;

public class GameClearPlayerEvent implements GameModeEvent {

  @Override
  public boolean execute(GameMode gameMode) {
    gameMode.gameClear();
    return false;
  }
}
