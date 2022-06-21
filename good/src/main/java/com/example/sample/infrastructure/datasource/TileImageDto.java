package com.example.sample.infrastructure.datasource;

import com.example.sample.domain.model.TileType;
import com.example.sample.presentation.GamePanel;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
      return Thumbnails.of(original)
          .size(GamePanel.tileSize, GamePanel.tileSize)
          .asBufferedImage();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
