package com.example.sample.domain.model;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
public class Enemy implements Collidable {
  private Location location;
  private Collision collision;
  private final EnemyAnimation enemyAnimation;

  // TODO: Movementのドメインモデルを作成すること
  private Vector vector = Vector.NONE;
  private int count = 0;

  public Enemy(final Location location, final EnemyAnimation enemyAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.enemyAnimation = enemyAnimation;
  }

  public void move() {
    location = location.shift(vector);
    collision = collision.shift(vector);
  }

  public boolean canMove(final List<Collidable> collidableList) {
    count++;
    count %= 90;
    if (count / 60 < 1) return false;
    if (count == 60) vector = Vector.random(2);
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public BufferedImage getAnimatedImage() {
    return enemyAnimation.getAnimatedImage();
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
