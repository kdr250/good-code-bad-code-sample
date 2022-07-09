package com.example.sample.domain.model.interactive;

import java.awt.image.BufferedImage;

/**
 * 相互作用トリガーのイメージ
 */
public class InteractiveImage {
  private final InteractiveType interactiveType;
  private final BufferedImage bufferedImage;

  public InteractiveImage(final InteractiveType interactiveType, final BufferedImage bufferedImage) {
    this.interactiveType = interactiveType;
    this.bufferedImage = bufferedImage;
  }

  public InteractiveType getInteractiveType() {
    return interactiveType;
  }

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }
}
