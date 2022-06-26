package com.example.sample.infrastructure.datasource.item;

import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemImageDto {
  private String name;
  private String image;

  public ItemImage toItemImage() {
    return new ItemImage(itemType(), bufferedImage());
  }

  private ItemType itemType() {
    return ItemType.valueOf(name);
  }

  private BufferedImage bufferedImage() {
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
}