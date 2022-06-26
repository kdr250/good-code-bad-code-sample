package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.event.DoorOpenEvent;
import com.example.sample.domain.model.event.Event;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemDoor implements Interactive {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;

  public ItemDoor(Location location, ItemImage itemImage) {
    if (itemImage.getItemType() != ItemType.DOOR) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public Event interact() {
    return new DoorOpenEvent();
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }
}
