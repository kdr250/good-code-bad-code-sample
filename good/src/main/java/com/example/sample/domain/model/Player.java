package com.example.sample.domain.model;

import lombok.Getter;

import java.util.List;

/**
 * プレイヤーを表現するクラス
 */
@Getter
public class Player {
  private Location location;
  private Collision collision;

  public Player(final Location location) {
    this.location = location;
    this.collision = new Collision(location);
  }

  public void move(final Vector vector) {
    location = location.shift(vector);
    collision = collision.shift(vector);
  }

  public boolean canMove(final List<Tile> tiles, final Vector vector) {
    return tiles.stream().noneMatch(tile -> collision.willCollide(tile.getCollision(), vector));
  }

  public boolean isOverlap(final Tile tile) {
    return collision.isCollide(tile.getCollision());
  }
}
