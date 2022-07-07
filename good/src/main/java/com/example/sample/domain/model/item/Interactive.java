package com.example.sample.domain.model.item;

import com.example.sample.domain.model.event.Event;

/**
 * プレイヤーに働きかける
 */
public interface Interactive extends Item {
  Event interact();
}
