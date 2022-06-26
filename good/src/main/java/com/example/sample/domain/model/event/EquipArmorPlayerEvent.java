package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Armor;

public class EquipArmorPlayerEvent implements PlayerEvent {

  private final Armor equipment;

  public EquipArmorPlayerEvent(final Armor equipment) {
    this.equipment  = equipment;
  }

  @Override
  public boolean execute(Player player) {
    // TODO: Not yet implemented
    return true;
  }
}
