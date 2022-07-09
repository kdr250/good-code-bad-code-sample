package com.example.sample.infrastructure.datasource.interactive;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.interactive.Chest;
import com.example.sample.domain.model.interactive.Door;
import com.example.sample.domain.model.interactive.Interactive;
import com.example.sample.domain.model.interactive.InteractiveImage;
import com.example.sample.domain.model.interactive.InteractiveType;
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
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Tile;

import java.awt.image.BufferedImage;
import java.util.Map;

public class InteractiveDto {
  private String name;
  private int locationX;
  private int locationY;

  private Location getLocation() {
    return new Location(locationX * Tile.TILE_SIZE, locationY * Tile.TILE_SIZE);
  }

  public Interactive toInteractive(Map<InteractiveType, BufferedImage> imageMap) {
    InteractiveType interactiveType = InteractiveType.valueOf(name);
    BufferedImage image = imageMap.get(interactiveType);
    InteractiveImage interactiveImage = new InteractiveImage(interactiveType, image);

    switch (interactiveType) {
      case CHEST:
        return new Chest(getLocation(), interactiveImage);
      case DOOR:
        return new Door(getLocation(), interactiveImage);
      default:
        throw new IllegalArgumentException();
    }
  }
}
