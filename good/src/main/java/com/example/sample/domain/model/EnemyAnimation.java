package com.example.sample.domain.model;

import java.awt.image.BufferedImage;
import java.util.Map;

public class EnemyAnimation {
  private final Map<EnemyAnimationType, BufferedImage> animationMap;

  private BufferedImage previousImage;
  private int count;

  public EnemyAnimation(Map<EnemyAnimationType, BufferedImage> animationMap) {
    this.animationMap = animationMap;
    previousImage = animationMap.get(EnemyAnimationType.SLIME_DOWN_ONE);
  }

  public BufferedImage getAnimatedImage() {
    BufferedImage result = count / 30 < 1 ? animationMap.get(EnemyAnimationType.SLIME_DOWN_ONE) : animationMap.get(EnemyAnimationType.SLIME_DOWN_TWO);
    previousImage = result;
    count++;
    count %= 60;
    return result;
  }
}
