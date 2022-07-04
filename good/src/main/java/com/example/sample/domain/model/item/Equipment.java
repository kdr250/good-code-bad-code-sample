package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.event.Event;

public interface Equipment extends Item {
  Equipment EMPTY = new ItemWeapon(new AttackPower(0), Location.EMPTY, null);

  Event equip();

  EquipmentType getEquipmentType();

  int maxMagicPointIncrement();

  int attack();

  int defense();

  default boolean isEmpty() {
    return this == EMPTY;
  }
}
