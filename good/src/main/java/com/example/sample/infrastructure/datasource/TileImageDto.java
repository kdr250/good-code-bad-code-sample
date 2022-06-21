package com.example.sample.infrastructure.datasource;

import com.example.sample.domain.model.TileType;
import com.example.sample.presentation.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class TileImageDto {
  private String name;
  private String image;

  public TileType toTileType() {
    return TileType.valueOf(name);
  }

  public BufferedImage bufferedImage() {
    byte[] decodedBytes = Base64.getDecoder().decode(image);
    ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
    try {
      BufferedImage original = ImageIO.read(bis);
      BufferedImage scaledImage = new BufferedImage(GamePanel.tileSize, GamePanel.tileSize, original.getType());
      Graphics2D g2 = scaledImage.createGraphics();
      g2.drawImage(original, 0, 0, GamePanel.tileSize, GamePanel.tileSize, null);
      return scaledImage;
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
