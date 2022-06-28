package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameClearEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemChest implements Interactive {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;

  public ItemChest(Location location, ItemImage itemImage) {
    if (itemImage.getItemType() != ItemType.CHEST) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public Event interact() {
    return new GameClearEvent();
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }

  @Override
  public String description() {
    return "";
  }
}
