package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemKey implements Key {

  private final Location location; // TODO: 敵からドロップするので後で削除すること
  private final Collision collision; // TODO: 敵からドロップするので後で削除すること
  private final ItemImage itemImage;

  public ItemKey(Location location, ItemImage itemImage) {
    if (itemImage.getItemType() != ItemType.KEY) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }
}
