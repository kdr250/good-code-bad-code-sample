package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.event.HitPointRecoveryPlayerEvent;
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
  public PlayerEvent consume() {
    return new HitPointRecoveryPlayerEvent(5);
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }
}
