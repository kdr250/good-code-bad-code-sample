package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Weapon;

public class EquipWeaponEvent implements Event {

  private final Weapon weapon;

  public EquipWeaponEvent(final Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public void execute(Player player) {
    // TODO: Not yet implemented
  }
}
