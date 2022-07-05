package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemKey implements Key {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;

  public ItemKey(ItemImage itemImage) {
    if (itemImage.getItemType() != ItemType.KEY) {
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
}
