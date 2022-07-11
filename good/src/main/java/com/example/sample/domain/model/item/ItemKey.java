package com.example.sample.domain.model.item;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;

import java.awt.image.BufferedImage;

/**
 * 鍵
 */
public class ItemKey implements Key {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;

  public ItemKey(ItemImage itemImage) {
    if (itemImage.itemType() != ItemType.KEY) {
      throw new IllegalArgumentException();
    }
    this.location = Location.EMPTY;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }

  @Override
  public String description() {
    return "扉を開ける鍵";
  }

  @Override
  public Location location() {
    return location;
  }

  @Override
  public Collision collision() {
    return collision;
  }
}
