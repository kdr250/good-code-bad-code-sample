package com.example.sample.infrastructure.datasource;

import com.example.sample.domain.model.TileType;

import javax.imageio.ImageIO;
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
    String base64 = image.split(",")[1];

    byte[] decodedBytes = Base64.getDecoder().decode(base64);
    ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
    try {
      return ImageIO.read(bis);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
