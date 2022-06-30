package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.event.EquipArmorPlayerEvent;
import com.example.sample.domain.model.event.Event;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemShieldNormal implements Equipment {

  private final DefensePower defensePower;
  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  public static final Equipment EMPTY = new ItemShieldNormal(new DefensePower(0), Location.EMPTY, new ItemImage(ItemType.WEAPON, null));

  public ItemShieldNormal(final DefensePower defensePower, final Location location, final ItemImage itemImage) {
    this.defensePower = defensePower;
    this.location = location;
    collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }

  @Override
  public Event equip() {
    return new EquipArmorPlayerEvent(this);
  }

  @Override
  public String description() {
    return "普通の盾";
  }
}
