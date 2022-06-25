package com.example.sample.domain.model;

import com.example.sample.domain.type.Direction;
import lombok.Getter;

/**
 * ベクトルを表現するクラス
 */
@Getter
public class Vector {
  private final int x;
  private final int y;

  public static final Vector NONE = new Vector(0, 0);

  public Vector(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public static Vector up(final int length) {
    return new Vector(0, length * -1);
  }

  public static Vector down(final int length) {
    return new Vector(0, length);
  }

  public static Vector left(final int length) {
    return new Vector(length * -1, 0);
  }

  public static Vector right(final int length) {
    return new Vector(length, 0);
  }

  public static Vector random(final int length) {
    int random = (int)(Math.random() * 100);

    switch (random / 25) {
      case 0:
        return Vector.up(length);
      case 1:
        return Vector.down(length);
      case 2:
        return Vector.left(length);
      default:
        return Vector.right(length);
    }
  }

  public Direction getDirection() {
    if (x == 0 && y < 0) return Direction.UP;
    if (x == 0 && y > 0) return Direction.DOWN;
    if (x < 0 && y == 0) return Direction.LEFT;
    if (x > 0 && y == 0) return Direction.RIGHT;
    return Direction.NONE;
  }
}
