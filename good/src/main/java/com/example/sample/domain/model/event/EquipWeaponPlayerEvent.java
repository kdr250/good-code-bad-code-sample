package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Weapon;

public class EquipWeaponPlayerEvent implements PlayerEvent {

  private final Weapon weapon;

  public EquipWeaponPlayerEvent(final Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public boolean execute(Player player) {
    // TODO: Not yet implemented
    return true;
  }
}
