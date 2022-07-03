package com.example.sample.infrastructure.datasource.item;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemChest;
import com.example.sample.domain.model.item.ItemDoor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemPotionRed;
import com.example.sample.domain.model.item.ItemShieldNormal;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.item.ItemWeapon;

import java.awt.image.BufferedImage;
import java.util.Map;

public class ItemDto {
  private String name;
  private int locationX;
  private int locationY;

  private Location getLocation() {
    return new Location(locationX * Tile.TILE_SIZE, locationY * Tile.TILE_SIZE);
  }

  public Item toItem(Map<ItemType, BufferedImage> imageMap) {
    ItemType itemType = ItemType.valueOf(name);
    BufferedImage image = imageMap.get(itemType);
    ItemImage itemImage = new ItemImage(itemType, image);

    switch (itemType) {
      case KEY:
        return new ItemKey(getLocation(), itemImage);
      case CHEST:
        return new ItemChest(getLocation(), itemImage);
      case DOOR:
        return new ItemDoor(getLocation(), itemImage);
      case WEAPON:
        return new ItemWeapon(new AttackPower(2), getLocation(), itemImage);
      case MAGICAL_WEAPON:
        return null; // TODO: Not Yet Implemented
      case BODY_ARMOR:
        return null; // TODO: Not Yet Implemented
      case POTION_RED:
        return new ItemPotionRed(getLocation(), itemImage);
      case POTION_BLUE:
        return null; // TODO: Not Yet Implemented
      case SHIELD_NORMAL:
        return new ItemShieldNormal(new DefensePower(2), getLocation(), itemImage);
      case SHIELD_BLUE:
        return null; // TODO: Not Yet Implemented
      default:
        throw new IllegalArgumentException();
    }
  }
}
