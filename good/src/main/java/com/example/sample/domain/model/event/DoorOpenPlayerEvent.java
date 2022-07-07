package com.example.sample.domain.model.event;

import com.example.sample.domain.model.character.player.Player;

/**
 * 門を開くイベント
 */
public class DoorOpenPlayerEvent implements PlayerEvent {

  @Override
  public boolean execute(Player player) {
    if (player.hasKey()) {
      player.removeKey();
      return true;
    }
    return false;
  }
}
