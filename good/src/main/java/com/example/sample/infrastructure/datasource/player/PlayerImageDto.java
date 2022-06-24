package com.example.sample.infrastructure.datasource.player;

import com.example.sample.domain.model.PlayerAnimation;
import com.example.sample.domain.model.PlayerAnimationType;
import com.example.sample.presentation.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class PlayerImageDto {
  private String name;
  private String image;

  public PlayerAnimationType toPlayerAnimationType() {
    return PlayerAnimationType.valueOf(name);
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
