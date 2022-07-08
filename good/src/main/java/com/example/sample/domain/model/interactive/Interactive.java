package com.example.sample.domain.model.interactive;

import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.worldmap.Collidable;

import java.awt.image.BufferedImage;

/**
 * 相互作用トリガー
 */
public interface Interactive extends Collidable {
  Event interact();

  BufferedImage getImage();
}
