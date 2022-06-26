package com.example.sample.domain.model.item;

import com.example.sample.domain.model.event.Event;

public interface Interactive extends Item {
  Event interact();
}
