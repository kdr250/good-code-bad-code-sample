package com.example.sample.domain.model.item;

import com.example.sample.domain.model.event.Event;

public interface Equipment extends Item {
  Event equip();

  EquipmentType getEquipmentType();

  int maxMagicPointIncrement();

  int attack();

  int defense();
}
