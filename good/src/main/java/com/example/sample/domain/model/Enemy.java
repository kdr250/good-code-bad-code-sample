package com.example.sample.domain.model;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
public class Enemy implements Collidable {
  private Location location;
  private Collision collision;
  private final EnemyAnimation enemyAnimation;
  private final EnemyMovement enemyMovement;

  public Enemy(final Location location, final EnemyAnimation enemyAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.enemyAnimation = enemyAnimation;
    enemyMovement = new EnemyMovement();
  }

  public void move() {
    Vector vector = enemyMovement.getVector();
    location = location.shift(vector);
    collision = collision.shift(vector);
  }

  public boolean updateMovementCountThenCanMove(final List<Collidable> collidableList) {
    enemyMovement.update();
    Vector vector = enemyMovement.getVector();
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
