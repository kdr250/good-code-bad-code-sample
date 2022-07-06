package com.example.sample.domain.model.event;

import com.example.sample.domain.model.character.player.Player;

public interface PlayerEvent extends Event {
  boolean execute(Player player);
}
