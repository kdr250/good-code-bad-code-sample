package com.example.sample.domain.model.character.enemy;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Damage;
import com.example.sample.domain.model.battle.EnemyBattleStatus;
import com.example.sample.domain.model.battle.Experience;
import com.example.sample.domain.model.battle.HitPoint;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Vector;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 敵
 */
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
    Vector vector = enemyMovement.vector();
    location = location.shift(vector);
    collision = collision.shift(vector);
  }

  public boolean updateMovementThenCanMove(final List<Collidable> collidableList) {
    enemyMovement.update();
    Vector vector = enemyMovement.vector();
    return collidableList.stream().filter(collidable -> this != collidable).noneMatch(c -> collision.willCollide(c.collision(), vector));
  }

  public Vector movingVector() {
    return enemyMovement.vector();
  }

  public BufferedImage getAnimatedImage() {
    return enemyAnimation.getAnimatedImage();
  }

  public BufferedImage getImage() {
    return enemyAnimation.getImage();
  }

  public HitPoint hitPoint() {
    return enemyBattleStatus.hitPoint();
  }

  public void damageHitPoint(final Damage damage) {
    enemyBattleStatus.damageHitPoint(damage);
  }

  public AttackPower attackPower() {
    return enemyBattleStatus.attackPower();
  }

  public boolean isDead() {
    return enemyBattleStatus.isDead();
  }

  public ItemType dropItemType() {
    return enemyBattleStatus.dropItemType();
  }

  public String name() {
    return name;
  }

  public Experience experience() {
    return enemyBattleStatus.experience();
  }

  @Override
  public Location location() {
    return location;
  }

  @Override
  public Collision collision() {
    return collision;
  }
}
