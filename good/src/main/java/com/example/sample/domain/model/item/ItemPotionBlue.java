package com.example.sample.domain.model.item;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.event.ConsumeItemAndMagicPointRecoveryPlayerEvent;
import com.example.sample.domain.model.event.PlayerEvent;

import java.awt.image.BufferedImage;

/**
 * 青ポーション
 */
public class ItemPotionBlue implements Consumable {

  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  private static final int RECOVERY_AMOUNT = 5;

  public ItemPotionBlue(final Location location, final ItemImage itemImage) {
    if (itemImage.itemType() != ItemType.POTION_BLUE) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public PlayerEvent consume() {
    return new ConsumeItemAndMagicPointRecoveryPlayerEvent(this, RECOVERY_AMOUNT);
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }

  @Override
  public String description() {
    return "魔法力" + RECOVERY_AMOUNT + "回復";
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
