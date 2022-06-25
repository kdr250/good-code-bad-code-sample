package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public interface Event {
  public void execute(Player player);
}
