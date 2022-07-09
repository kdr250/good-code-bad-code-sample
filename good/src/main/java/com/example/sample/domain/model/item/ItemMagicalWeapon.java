package com.example.sample.domain.model.item;

import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.PlayerEquipEvent;

import java.awt.image.BufferedImage;

/**
 * 魔法の杖
 */
public class ItemMagicalWeapon implements Equipment {

  private final AttackPower attackPower;
  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  private final EquipmentType equipmentType = EquipmentType.WEAPON;

  public ItemMagicalWeapon(final AttackPower attackPower, final Location location, final ItemImage itemImage) {
    this.attackPower = attackPower;
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
    return "魔法の杖\n魔法力最大値+2";
  }

  @Override
  public int maxMagicPointIncrement() {
    return 2;
  }

  @Override
  public AttackPower attackPower() {
    return attackPower;
  }

  @Override
  public DefensePower defensePower() {
    return DefensePower.NONE;
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
