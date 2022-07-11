package com.example.sample.infrastructure.datasource.interactive;

import com.example.sample.domain.model.interactive.InteractiveImage;
import com.example.sample.domain.model.interactive.InteractiveType;
import com.example.sample.domain.model.worldmap.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class InteractiveImageDto {
  private String name;
  private String image;

  public InteractiveImage toInteractiveImage() {
    return new InteractiveImage(interactiveType(), bufferedImage());
  }

  public InteractiveType interactiveType() {
    return InteractiveType.valueOf(name);
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
}
