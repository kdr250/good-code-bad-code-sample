package com.example.sample.domain.model.item;

import com.example.sample.domain.model.event.Event;

/**
 * 消費可能
 */
public interface Consumable extends Item {
  Event consume();
}
