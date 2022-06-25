package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collidable;

import java.awt.image.BufferedImage;

public interface Item extends Collidable {
  public BufferedImage getImage();
}
