package com.example.sample.domain.model.item;

import com.example.sample.domain.model.event.Event;

public interface Equipment extends Item {
  Equipment empty();
  Event equip();
}
