package com.example.sample.domain.model.event;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.item.Equipment;

public class EquipWeaponEvent implements PlayerEvent {

  private final Equipment equipment;

  public EquipWeaponEvent(final Equipment equipment) {
    this.equipment = equipment;
  }

  @Override
  public boolean execute(Player player) {
    player.changeEquipment(equipment);
    return true;
  }
}
