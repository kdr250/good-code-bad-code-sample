package com.example.sample.domain.model.character.player;

import com.example.sample.domain.model.battle.PlayerBattleStatus;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.battle.technique.Technique;
import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.worldmap.Collision;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Vector;
import com.example.sample.domain.model.worldmap.Direction;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Predicate;

/**
 * プレイヤー
 */
@Getter
public class Player implements Collidable {
  private Location location;
  private Collision collision;
  private Direction direction;
  private PlayerItems playerItems;
  private final PlayerAnimation playerAnimation;
  private final PlayerBattleStatus playerBattleStatus;

  public Player(final Location location, final PlayerAnimation playerAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.direction = Direction.DOWN;
    this.playerItems = new PlayerItems();
    this.playerAnimation = playerAnimation;
    this.playerBattleStatus = PlayerBattleStatus.initialize();
  }

  public void move(final Vector vector) {
    location = location.shift(vector);
    collision = collision.shift(vector);
    direction = vector.direction();
  }

  public void warp(final Location location) {
    this.location = location;
  }

  public boolean canMove(final List<Collidable> collidableList, final Vector vector) {
    return collidableList.stream()
        .filter(collidable -> this != collidable)
        .filter(Predicate.not(Item.class::isInstance))
        .filter(Predicate.not(Enemy.class::isInstance))
        .noneMatch(collidable -> collision.willCollide(collidable.getCollision(), vector));
  }

  public void changeDirection(Vector vector) {
    direction = vector.direction();
  }

  public BufferedImage getAnimatedImage() {
    return playerAnimation.getAnimatedImage(direction);
  }

  public BufferedImage getImage() {
    return playerAnimation.getImage();
  }

  public void changeEquipment(Equipment equipment) {
    EquipmentType equipmentType = equipment.getEquipmentType();
    if (playerBattleStatus.hasEquipment(equipmentType)) {
      Equipment deactivatedEquipment = playerBattleStatus.deactivateEquipment(equipmentType);
      pickUp(deactivatedEquipment);
    }
    playerBattleStatus.equip(equipment);
    removeItem(equipment);
  }

  public void recoverHitPoint(final int recoveryAmount) {
    playerBattleStatus.recoveryHitPoint(recoveryAmount);
  }

  public void recoverHitPointMax() {
    playerBattleStatus.recoveryHitPointMax();
  }

  public void damageHitPoint(final int damageAmount) {
    playerBattleStatus.damageHitPoint(damageAmount);
  }

  public void recoverMagicPoint(final int recoveryAmount) {
    playerBattleStatus.recoveryMagicPoint(recoveryAmount);
  }

  public void recoverMagicPointMax() {
    playerBattleStatus.recoverMagicPointMax();
  }

  public void pickUp(Item item) {
    playerItems = playerItems.add(item);
  }

  public void removeItem(Item item) {
    playerItems = playerItems.remove(item);
  }

  public boolean hasKey() {
    return playerItems.hasKey();
  }

  public void removeKey() {
    playerItems = playerItems.removeKeyIfExists();
  }

  public boolean isOverlap(final Collidable collidable) {
    return collision.isCollide(collidable.getCollision());
  }

  public List<Technique> techniques() {
    return playerBattleStatus.techniques();
  }

  public boolean canAttack(Technique technique) {
    return playerBattleStatus.canAttack(technique);
  }

  public void consumeCostForAttack(Technique technique) {
    playerBattleStatus.consumeCostForAttack(technique);
  }

  public int totalAttack(Technique technique) {
    return playerBattleStatus.totalAttack(technique);
  }

  public int totalAttack() {
    return playerBattleStatus.totalAttack();
  }

  public int totalDefense() {
    return playerBattleStatus.totalDefense();
  }

  public boolean isDead() {
    return playerBattleStatus.isDead();
  }

  public void deactivateAllEquipments() {
    playerBattleStatus.deactivateAllEquipments();
  }

  public boolean gainExperienceAndIsLevelUp(final int experienceIncrement) {
    return playerBattleStatus.gainExperienceAndIsLevelUp(experienceIncrement);
  }
}