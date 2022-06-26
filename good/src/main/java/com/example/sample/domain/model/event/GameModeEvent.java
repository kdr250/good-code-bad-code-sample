package com.example.sample.domain.model.event;

import com.example.sample.domain.model.gamemode.GameMode;

public interface GameModeEvent extends Event {
  boolean execute(GameMode gameMode);
}
