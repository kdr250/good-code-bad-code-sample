package com.example.sample.domain.model.event;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.item.Equipment;

public class EquipEvent implements PlayerEvent {

  private final Equipment equipment;

  public EquipEvent(final Equipment equipment) {
    this.equipment = equipment;
  }

  @Override
  public boolean execute(Player player) {
    player.changeEquipment(equipment);
    return true;
  }
}
