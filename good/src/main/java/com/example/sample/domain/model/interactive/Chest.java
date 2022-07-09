package com.example.sample.domain.model.interactive;

import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameClearEvent;
import com.example.sample.domain.model.worldmap.Vector;

import java.awt.image.BufferedImage;

/**
 * 宝箱
 */
public class Chest implements Interactive {

  private final Location location;
  private final Collision collision;
  private final InteractiveImage interactiveImage;
  private static final Vector vector = Vector.down(5);

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
  public boolean contains(Location location) {
    return collision.shift(vector).contains(location);
  }

  @Override
  public BufferedImage getImage() {
    return interactiveImage.getBufferedImage();
  }

  @Override
  public Location location() {
    return location;
  }

  @Override
  public Collision collision() {
    return collision;
  }
}
