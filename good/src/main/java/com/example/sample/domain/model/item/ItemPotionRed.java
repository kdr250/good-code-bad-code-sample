package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.HitPointRecoveryEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemPotionRed implements Consumable {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;

  public ItemPotionRed(final Location location, final ItemImage itemImage) {
    if (itemImage.getItemType() != ItemType.POTION_RED) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public Event consume() {
    return new HitPointRecoveryEvent(5);
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }
}
