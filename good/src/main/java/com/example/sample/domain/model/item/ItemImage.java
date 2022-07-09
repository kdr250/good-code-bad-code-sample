package com.example.sample.domain.model.item;

import java.awt.image.BufferedImage;

/**
 * アイテムのイメージ
 */
public class ItemImage {
  private final ItemType itemType;
  private final BufferedImage bufferedImage;

  public ItemImage(final ItemType itemType, final BufferedImage bufferedImage) {
    this.itemType = itemType;
    this.bufferedImage = bufferedImage;
  }

  public ItemType itemType() {
    return itemType;
  }

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }
}
