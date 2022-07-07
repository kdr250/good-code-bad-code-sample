package com.example.sample.domain.model.character.npc;

import com.example.sample.domain.model.worldmap.Direction;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * NPCのアニメーション
 */
public class NpcAnimation {
  private final Map<NpcAnimationType, BufferedImage> animationMap;

  private BufferedImage previousImage;
  private int count;

  public NpcAnimation(Map<NpcAnimationType, BufferedImage> animationMap) {
    this.animationMap = animationMap;
    previousImage = animationMap.get(NpcAnimationType.OLD_MAN_UP_ONE);
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

  private BufferedImage upAnimation() {
    BufferedImage result = count / 30 < 1 ? animationMap.get(NpcAnimationType.OLD_MAN_UP_ONE) : animationMap.get(NpcAnimationType.OLD_MAN_UP_TWO);
    previousImage = result;
    count++;
    count %= 60;
    return result;
  }

  private BufferedImage downAnimation() {
    BufferedImage result = count / 30 < 1 ? animationMap.get(NpcAnimationType.OLD_MAN_DOWN_ONE) : animationMap.get(NpcAnimationType.OLD_MAN_DOWN_TWO);
    previousImage = result;
    count++;
    count %= 60;
    return result;
  }

  private BufferedImage leftAnimation() {
    BufferedImage result = count / 30 < 1 ? animationMap.get(NpcAnimationType.OLD_MAN_LEFT_ONE) : animationMap.get(NpcAnimationType.OLD_MAN_LEFT_TWO);
    previousImage = result;
    count++;
    count %= 60;
    return result;
  }

  private BufferedImage rightAnimation() {
    BufferedImage result = count / 30 < 1 ? animationMap.get(NpcAnimationType.OLD_MAN_RIGHT_ONE) : animationMap.get(NpcAnimationType.OLD_MAN_RIGHT_TWO);
    previousImage = result;
    count++;
    count %= 60;
    return result;
  }
}
