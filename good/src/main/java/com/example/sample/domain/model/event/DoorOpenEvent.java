package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public class DoorOpenEvent implements Event {

  @Override
  public void execute(Player player) {
    // TODO: Not yet implemented
    if (player.hasKey()) {
      player.deleteKey();
    }
  }
}
