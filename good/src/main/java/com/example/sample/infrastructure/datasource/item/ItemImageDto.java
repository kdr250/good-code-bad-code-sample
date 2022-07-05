package com.example.sample.infrastructure.datasource.item;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Tile;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class ItemImageDto {
  private String name;
  private String image;

  public ItemImage toItemImage() {
    return new ItemImage(itemType(), bufferedImage());
  }

  public ItemType itemType() {
    return ItemType.valueOf(name);
  }

  public BufferedImage bufferedImage() {
    byte[] decodedBytes = Base64.getDecoder().decode(image);
    ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
    try {
      BufferedImage original = ImageIO.read(bis);
      BufferedImage scaledImage = new BufferedImage(Tile.TILE_SIZE, Tile.TILE_SIZE, original.getType());
      Graphics2D g2 = scaledImage.createGraphics();
      g2.drawImage(original, 0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
      return scaledImage;
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  public Item toItem() {
    ItemType itemType = ItemType.valueOf(name);
    BufferedImage image = bufferedImage();
    ItemImage itemImage = new ItemImage(itemType, image);

    switch (itemType) {
      case KEY:
        return new ItemKey(itemImage);
      case CHEST:
        return new ItemChest(Location.EMPTY, itemImage);
      case DOOR:
        return new ItemDoor(Location.EMPTY, itemImage);
      case WEAPON:
        return new ItemWeapon(new AttackPower(2), Location.EMPTY, itemImage);
      case MAGICAL_WEAPON:
        return new ItemMagicalWeapon(new AttackPower(3), Location.EMPTY, itemImage);
      case BODY_ARMOR:
        return new ItemArmor(Location.EMPTY, itemImage);
      case POTION_RED:
        return new ItemPotionRed(Location.EMPTY, itemImage);
      case POTION_BLUE:
        return new ItemPotionBlue(Location.EMPTY, itemImage);
      case SHIELD_NORMAL:
        return new ItemShieldNormal(Location.EMPTY, itemImage);
      case SHIELD_BLUE:
        return new ItemShieldBlue(Location.EMPTY, itemImage);
      default:
        throw new IllegalArgumentException();
    }
  }
}
