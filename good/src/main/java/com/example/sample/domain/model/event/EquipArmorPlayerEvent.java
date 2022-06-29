package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Equipment;

public class EquipArmorPlayerEvent implements PlayerEvent {

  private final Equipment equipment;

  public EquipArmorPlayerEvent(final Equipment equipment) {
    this.equipment  = equipment;
  }

  @Override
  public boolean execute(Player player) {
    player.changeEquipment(equipment);
    return true;
  }
}
