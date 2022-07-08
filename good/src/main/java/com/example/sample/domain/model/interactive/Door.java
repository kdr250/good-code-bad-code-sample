package com.example.sample.domain.model.interactive;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.event.DoorOpenPlayerEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import lombok.Getter;

import java.awt.image.BufferedImage;

/**
 * 門
 */
@Getter
public class Door implements Interactive {

  private final Location location;
  private final Collision collision;
  private final InteractiveImage interactiveImage;

  public Door(final Location location, final InteractiveImage interactiveImage) {
    if (interactiveImage.getInteractiveType() != InteractiveType.DOOR) {
      throw new IllegalArgumentException();
    }
    this.location = location;
    this.collision = new Collision(location);
    this.interactiveImage = interactiveImage;
  }

  @Override
  public PlayerEvent interact() {
    return new DoorOpenPlayerEvent();
  }

  @Override
  public BufferedImage getImage() {
    return interactiveImage.getBufferedImage();
  }
}
