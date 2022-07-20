package com.example.sample.infrastructure.datasource.item;

import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemArmor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemMagicalWeapon;
import com.example.sample.domain.model.item.ItemPotionBlue;
import com.example.sample.domain.model.item.ItemPotionRed;
import com.example.sample.domain.model.item.ItemShieldBlue;
import com.example.sample.domain.model.item.ItemShieldNormal;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.item.ItemWeapon;

import java.awt.*;
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
      case WEAPON:
        AttackPower attackPower = AttackPower.random();
        return new ItemWeapon(attackPower, getLocation(), overwriteNumber(itemImage, attackPower.value()));
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

  private static final Font arial20 = new Font("Arial", Font.PLAIN, 20);

  private ItemImage overwriteNumber(ItemImage original, int number) {
    BufferedImage originalImage = original.getBufferedImage();
    BufferedImage updatedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
    updatedImage.setData(originalImage.getData());
    Graphics2D g2 = updatedImage.createGraphics();
    g2.setColor(Color.white);
    g2.setFont(arial20);
    g2.drawString(String.valueOf(number), Tile.TILE_SIZE - 15, Tile.TILE_SIZE - 10);
    return new ItemImage(original.itemType(), updatedImage);
  }
}
