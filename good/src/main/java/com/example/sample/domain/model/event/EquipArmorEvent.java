package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Armor;

public class EquipArmorEvent implements Event {

  private final Armor equipment;

  public EquipArmorEvent(final Armor equipment) {
    this.equipment  = equipment;
  }

  @Override
  public void execute(Player player) {
    // TODO: Not yet implemented
  }
}
