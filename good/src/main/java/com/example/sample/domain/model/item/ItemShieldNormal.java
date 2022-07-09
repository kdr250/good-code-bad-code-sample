package com.example.sample.domain.model.item;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.event.PlayerEquipEvent;
import com.example.sample.domain.model.event.Event;

import java.awt.image.BufferedImage;

/**
 * 普通の盾
 */
public class ItemShieldNormal implements Equipment {

  private final DefensePower defensePower;
  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  private final EquipmentType equipmentType = EquipmentType.ARM;
  public static final Equipment EMPTY = new ItemShieldNormal(new DefensePower(0), Location.EMPTY, new ItemImage(ItemType.SHIELD_NORMAL, null));

  private ItemShieldNormal(final DefensePower defensePower, final Location location, final ItemImage itemImage) {
    this.defensePower = defensePower;
    this.location = location;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  public ItemShieldNormal(final Location location, final ItemImage itemImage) {
    this.defensePower = new DefensePower(1);
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
    return "普通の盾";
  }

  @Override
  public int maxMagicPointIncrement() {
    return 0;
  }

  @Override
  public AttackPower attackPower() {
    return AttackPower.NONE;
  }

  @Override
  public DefensePower defensePower() {
    return defensePower;
  }

  @Override
  public EquipmentType equipmentType() {
    return equipmentType;
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
