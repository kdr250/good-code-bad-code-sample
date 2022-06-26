package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public interface PlayerEvent extends Event {
  boolean execute(Player player);
}
