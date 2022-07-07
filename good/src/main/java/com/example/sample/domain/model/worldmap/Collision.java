package com.example.sample.domain.model.worldmap;

import java.awt.*;

/**
 * 衝突
 */
public class Collision {
  private final Rectangle value;
  private static final int WIDTH = 48;
  private static final int HEIGHT = 48;
  public static final Collision NO_COLLISION = new Collision(new Rectangle(0, 0, 0, 0));

  private Collision(Rectangle value) {
    this.value = value;
  }

  public Collision(Location location) {
    value = new Rectangle(location.getX(), location.getY(), WIDTH, HEIGHT);
  }

  public Collision shift(Vector vector) {
    Rectangle rectangle = new Rectangle(value.x + vector.getX(), value.y + vector.getY(), WIDTH, HEIGHT);
    return new Collision(rectangle);
  }

  public boolean willCollide(Collision other, Vector vector) {
    Rectangle willMoveRectangle = value.getBounds();
    willMoveRectangle.translate(vector.getX(), vector.getY());
    return willMoveRectangle.intersects(other.value);
  }

  public boolean isCollide(Collision other) {
    return this.value.intersects(other.value);
  }

  @Override
  public String toString() {
    return "Collision{" +
        "value=" + value +
        '}';
  }
}
