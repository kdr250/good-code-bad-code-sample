package com.example.sample.domain.model.item;

import com.example.sample.domain.model.worldmap.Collidable;

import java.awt.image.BufferedImage;

/**
 * アイテム
 */
public interface Item extends Collidable {
  BufferedImage getImage();

  String description();
}
