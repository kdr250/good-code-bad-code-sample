package com.example.sample.domain.model;

import com.example.sample.domain.type.Direction;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * プレイヤーを表現するクラス
 */
@Getter
public class Player implements Collidable {
  private Location location;
  private Collision collision;
  private Direction direction;
  private final PlayerAnimation playerAnimation;

  public Player(final Location location, final PlayerAnimation playerAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.playerAnimation = playerAnimation;
    direction = Direction.DOWN;
  }

  public void move(final Vector vector) {
    location = location.shift(vector);
    collision = collision.shift(vector);
    direction = vector.getDirection();
  }

  public boolean canMove(final List<Collidable> collidableList, final Vector vector) {
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public void changeDirection(Vector vector) {
    direction = vector.getDirection();
  }

  public BufferedImage getAnimatedImage() {
    return playerAnimation.getAnimatedImage(direction);
  }

  public boolean isOverlap(final Tile tile) {
    return collision.isCollide(tile.getCollision());
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
