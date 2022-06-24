package com.example.sample.domain.model;

import lombok.Getter;

/**
 * 位置を表現するクラス
 */
@Getter
public class Location {
  private final int x;
  private final int y;

  public Location(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public Location shift(final int shiftX, final int shiftY) {
    final int nextX = x + shiftX;
    final int nextY = y + shiftY;
    return new Location(nextX, nextY);
  }

  public Location shift(final Vector vector) {
    final int nextX = x + vector.getX();
    final int nextY = y + vector.getY();
    return new Location(nextX, nextY);
  }

  public double distance(final Location other) {
    final int distanceX = this.x - other.x;
    final int distanceY = this.y - other.y;
    return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
  }
}
