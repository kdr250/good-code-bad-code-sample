package com.example.sample.domain.model.item;

import com.example.sample.domain.model.Collision;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.PlayerEquipEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class ItemArmor implements Equipment {

  private final DefensePower defensePower;
  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  private final EquipmentType equipmentType = EquipmentType.ARMOR;
  public static final Equipment EMPTY = new ItemArmor(new DefensePower(0), Location.EMPTY, new ItemImage(ItemType.BODY_ARMOR, null));

  private ItemArmor(final DefensePower defensePower, final Location location, final ItemImage itemImage) {
    this.defensePower = defensePower;
    this.location = location;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  public ItemArmor(final Location location, final ItemImage itemImage) {
    this.defensePower = new DefensePower(2);
    this.location = location;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  @Override
  public BufferedImage getImage() {
    return itemImage.getBufferedImage();
  }

  @Override
  public Event equip() {
    return new PlayerEquipEvent(this);
  }

  @Override
  public String description() {
    return "普通の鎧";
  }

  @Override
  public int maxMagicPointIncrement() {
    return 0;
  }

  @Override
  public int attack() {
    return 0;
  }

  @Override
  public int defense() {
    return defensePower.getValue();
  }
}