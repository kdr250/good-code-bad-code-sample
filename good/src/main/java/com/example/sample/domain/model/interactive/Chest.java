package com.example.sample.domain.model.interactive;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameClearEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * 宝箱
 */
@Getter
public class Chest implements Interactive {

  private final Location location;
  private final Collision collision;
  private final InteractiveImage interactiveImage;

  public Chest(final Location location, final InteractiveImage interactiveImage) {
    if (interactiveImage.getInteractiveType() != InteractiveType.CHEST) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    this.collision = new Collision(location);
    this.interactiveImage = interactiveImage;
  }

  @Override
  public Event interact() {
    return new GameClearEvent();
  }

  @Override
  public BufferedImage getImage() {
    return interactiveImage.getBufferedImage();
  }
}
