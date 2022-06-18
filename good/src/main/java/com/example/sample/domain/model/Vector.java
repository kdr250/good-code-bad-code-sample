package com.example.sample.domain.model;

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
}
