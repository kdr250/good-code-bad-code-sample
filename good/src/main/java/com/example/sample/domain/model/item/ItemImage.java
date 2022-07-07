package com.example.sample.domain.model.item;

import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * アイテムのイメージ
 */
@Getter
public class ItemImage {
  private final ItemType itemType;
  private final BufferedImage bufferedImage;

  public ItemImage(final ItemType itemType, final BufferedImage bufferedImage) {
    this.itemType = itemType;
    this.bufferedImage = bufferedImage;
  }
}
