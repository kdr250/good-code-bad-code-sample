package com.example.sample.infrastructure.datasource.item;

import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemArmor;
import com.example.sample.domain.model.item.ItemChest;
import com.example.sample.domain.model.item.ItemDoor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemMagicalWeapon;
import com.example.sample.domain.model.item.ItemPotionBlue;
import com.example.sample.domain.model.item.ItemPotionRed;
import com.example.sample.domain.model.item.ItemShieldBlue;
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
        return new ItemKey(itemImage);
      case CHEST:
        return new ItemChest(getLocation(), itemImage);
      case DOOR:
        return new ItemDoor(getLocation(), itemImage);
      case WEAPON:
        return new ItemWeapon(new AttackPower(2), getLocation(), itemImage);
      case MAGICAL_WEAPON:
        return new ItemMagicalWeapon(new AttackPower(3), getLocation(), itemImage);
      case BODY_ARMOR:
        return new ItemArmor(getLocation(), itemImage);
      case POTION_RED:
        return new ItemPotionRed(getLocation(), itemImage);
      case POTION_BLUE:
        return new ItemPotionBlue(getLocation(), itemImage);
      case SHIELD_NORMAL:
        return new ItemShieldNormal(getLocation(), itemImage);
      case SHIELD_BLUE:
        return new ItemShieldBlue(getLocation(), itemImage);
      default:
        throw new IllegalArgumentException();
    }
  }
}
