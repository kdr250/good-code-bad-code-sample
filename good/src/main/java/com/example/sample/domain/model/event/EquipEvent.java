package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Equippable;

public class EquipEvent implements Event {

  private final Equippable equipment;

  public EquipEvent(final Equippable equipment) {
    this.equipment  = equipment;
  }

  @Override
  public void execute(Player player) {
    // TODO: Not yet implemented
  }
}
