package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.event.DoorOpenPlayerEvent;
import com.example.sample.domain.model.event.PlayerEvent;
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
  public PlayerEvent interact() {
    return new DoorOpenPlayerEvent();
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
