package com.example.sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

  public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
    BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
    Graphics2D g2 = scaledImage.createGraphics();
    g2.drawImage(original, 0, 0, width, height, null);

    return scaledImage;
  }
}
