package com.example.sample.domain.model;

import com.example.sample.domain.type.Direction;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * プレイヤーを表現するクラス
 */
@Getter
public class Player {
  private Location location;
  private Collision collision;
  private Direction direction;
  private final PlayerAnimation playerAnimation;

  public Player(final Location location, PlayerAnimation playerAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.playerAnimation = playerAnimation;
  }

  public void move(final Vector vector) {
    location = location.shift(vector);
    collision = collision.shift(vector);
    direction = vector.getDirection();
  }

  public boolean canMove(final List<Tile> tiles, final Vector vector) {
    return tiles.stream().noneMatch(tile -> collision.willCollide(tile.getCollision(), vector));
  }

  public BufferedImage getAnimatedImage() {
    return playerAnimation.getAnimatedImage(direction);
  }

  public boolean isOverlap(final Tile tile) {
    return collision.isCollide(tile.getCollision());
  }
}
