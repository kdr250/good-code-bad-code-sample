package com.example.sample.domain.model;

import com.example.sample.domain.model.battle.EnemyBattleStatus;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
public class Enemy implements Collidable {
  private final String name;
  private Location location;
  private Collision collision;
  private final EnemyAnimation enemyAnimation;
  private final EnemyMovement enemyMovement;
  private final EnemyBattleStatus enemyBattleStatus;

  public Enemy(final String name, final Location location, final EnemyAnimation enemyAnimation) {
    this.name = name;
    this.location = location;
    collision = new Collision(location);
    this.enemyAnimation = enemyAnimation;
    enemyMovement = new EnemyMovement();
    this.enemyBattleStatus = EnemyBattleStatus.initialize();
  }

  public void move() {
    Vector vector = enemyMovement.getVector();
    location = location.shift(vector);
    collision = collision.shift(vector);
  }

  public boolean updateMovementThenCanMove(final List<Collidable> collidableList) {
    enemyMovement.update();
    Vector vector = enemyMovement.getVector();
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public BufferedImage getAnimatedImage() {
    return enemyAnimation.getAnimatedImage();
  }

  public BufferedImage getImage() {
    return enemyAnimation.getImage();
  }
}
