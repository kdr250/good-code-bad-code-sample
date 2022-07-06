package com.example.sample.domain.model.character.enemy;

import com.example.sample.domain.model.battle.EnemyBattleStatus;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Vector;
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
    this.collision = new Collision(location);
    this.enemyAnimation = enemyAnimation;
    this.enemyMovement = new EnemyMovement();
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
    return collidableList.stream().filter(collidable -> this != collidable).noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public BufferedImage getAnimatedImage() {
    return enemyAnimation.getAnimatedImage();
  }

  public BufferedImage getImage() {
    return enemyAnimation.getImage();
  }

  public void damageHitPoint(final int damageAmount) {
    enemyBattleStatus.damageHitPoint(damageAmount);
  }

  public int attack() {
    return enemyBattleStatus.attack();
  }

  public boolean isDead() {
    return enemyBattleStatus.isDead();
  }

  public ItemType dropItemType() {
    return enemyBattleStatus.getDropItemType();
  }
}