/*
 * ○リスト4.21 武器を表現するクラス（改良版）
 * を参考にしたクラス
 */
package com.example.sample.domain.model.item;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.PlayerEquipEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * 武器
 */
@Getter
public class ItemWeapon implements Equipment {

  private final AttackPower attackPower;
  private final Location location;
  private final Collision collision;
  private final ItemImage itemImage;
  private final EquipmentType equipmentType = EquipmentType.WEAPON;
  public static final Equipment EMPTY = new ItemWeapon(new AttackPower(0), Location.EMPTY, new ItemImage(ItemType.WEAPON, null));

  public ItemWeapon(final AttackPower attackPower, final Location location, final ItemImage itemImage) {
    this.attackPower = attackPower;
    this.location = location;
    this.collision = new Collision(location);
    this.itemImage = itemImage;
  }

  /**
   * 武器を強化する
   * @param increment 攻撃力の増分
   * @return 強化した武器
   */
  public ItemWeapon reinForce(final AttackPower increment) {
    final AttackPower reinForced = attackPower.reinForce(increment);
    return new ItemWeapon(reinForced, Location.EMPTY, itemImage);
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
    return "普通の剣";
  }

  @Override
  public int maxMagicPointIncrement() {
    return 0;
  }

  @Override
  public int attack() {
    return attackPower.getValue();
  }

  @Override
  public int defense() {
    return 0;
  }
}
