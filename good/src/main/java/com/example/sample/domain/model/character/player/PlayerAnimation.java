package com.example.sample.domain.model.character.player;

import com.example.sample.domain.model.worldmap.Direction;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * プレイヤーのアニメーション
 */
public class PlayerAnimation {
  private final Map<PlayerAnimationType, BufferedImage> animationMap;

  private BufferedImage previousImage;
  private int count = 0;

  public PlayerAnimation(Map<PlayerAnimationType, BufferedImage> animationMap) {
    this.animationMap = animationMap;
    previousImage = animationMap.get(PlayerAnimationType.UP_ONE);
  }

  public BufferedImage getAnimatedImage(Direction direction) {
    switch (direction) {
      case UP:
        return upAnimation();
      case DOWN:
        return downAnimation();
      case LEFT:
        return leftAnimation();
      case RIGHT:
        return rightAnimation();
      default:
        return previousImage;
    }
  }

  public BufferedImage getImage() {
    return animationMap.get(PlayerAnimationType.DOWN_ONE);
  }

  private BufferedImage upAnimation() {
    BufferedImage result = count / 15 < 1 ? animationMap.get(PlayerAnimationType.UP_ONE) : animationMap.get(PlayerAnimationType.UP_TWO);
    previousImage = result;
    count++;
    count %= 30;
    return result;
  }

  private BufferedImage downAnimation() {
    BufferedImage result = count / 15 < 1 ? animationMap.get(PlayerAnimationType.DOWN_ONE) : animationMap.get(PlayerAnimationType.DOWN_TWO);
    previousImage = result;
    count++;
    count %= 30;
    return result;
  }

  private BufferedImage leftAnimation() {
    BufferedImage result = count / 15 < 1 ? animationMap.get(PlayerAnimationType.LEFT_ONE) : animationMap.get(PlayerAnimationType.LEFT_TWO);
    previousImage = result;
    count++;
    count %= 30;
    return result;
  }

  private BufferedImage rightAnimation() {
    BufferedImage result = count / 15 < 1 ? animationMap.get(PlayerAnimationType.RIGHT_ONE) : animationMap.get(PlayerAnimationType.RIGHT_TWO);
    previousImage = result;
    count++;
    count %= 30;
    return result;
  }
}
