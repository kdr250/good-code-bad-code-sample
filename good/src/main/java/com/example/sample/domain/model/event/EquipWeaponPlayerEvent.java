package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Equipment;

public class EquipWeaponPlayerEvent implements PlayerEvent {

  private final Equipment equipment;

  public EquipWeaponPlayerEvent(final Equipment equipment) {
    this.equipment = equipment;
  }

  @Override
  public boolean execute(Player player) {
    // TODO: Not yet implemented
    return true;
  }
}
