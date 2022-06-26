package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public class DoorOpenPlayerEvent implements PlayerEvent {

  @Override
  public boolean execute(Player player) {
    if (player.hasKey()) {
      player.deleteKey();
      return true;
    }
    return false;
  }
}
