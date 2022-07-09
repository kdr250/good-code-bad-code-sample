package com.example.sample.domain.model.character.player;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Damage;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.battle.Equipments;
import com.example.sample.domain.model.battle.Experience;
import com.example.sample.domain.model.battle.HitPoint;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;
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

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Predicate;

/**
 * プレイヤー
 */
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
    this.collision = new Collision(location);
    this.direction = Direction.DOWN;
  }

  public boolean canMove(final List<Collidable> collidableList, final Vector vector) {
    return collidableList.stream()
        .filter(collidable -> this != collidable)
        .filter(Predicate.not(Item.class::isInstance))
        .filter(Predicate.not(Enemy.class::isInstance))
        .noneMatch(collidable -> collision.willCollide(collidable.collision(), vector));
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

  public Equipments equipments() {
    return playerBattleStatus.equipments();
  }

  public void changeEquipment(Equipment equipment) {
    EquipmentType equipmentType = equipment.equipmentType();
    if (playerBattleStatus.hasEquipment(equipmentType)) {
      Equipment deactivatedEquipment = playerBattleStatus.deactivateEquipment(equipmentType);
      pickUp(deactivatedEquipment);
    }
    playerBattleStatus.equip(equipment);
    removeItem(equipment);
  }

  public HitPoint hitPoint() {
    return playerBattleStatus.hitPoint();
  }

  public void recoverHitPoint(final HitPoint recoveryAmount) {
    playerBattleStatus.recoveryHitPoint(recoveryAmount);
  }

  public void recoverHitPointMax() {
    playerBattleStatus.recoveryHitPointMax();
  }

  public void damageHitPoint(final Damage damage) {
    playerBattleStatus.damageHitPoint(damage);
  }

  public MagicPoint magicPoint() {
    return playerBattleStatus.magicPoint();
  }

  public void recoverMagicPoint(final MagicPoint recoveryAmount) {
    playerBattleStatus.recoveryMagicPoint(recoveryAmount);
  }

  public void recoverMagicPointMax() {
    playerBattleStatus.recoverMagicPointMax();
  }

  public PlayerItems playerItems() {
    return playerItems;
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
    return collision.isCollide(collidable.collision());
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

  public AttackPower totalAttackPower(Technique technique) {
    return playerBattleStatus.totalAttackPower(technique);
  }

  public AttackPower totalAttackPower() {
    return playerBattleStatus.totalAttackPower();
  }

  public DefensePower totalDefensePower() {
    return playerBattleStatus.totalDefensePower();
  }

  public boolean isDead() {
    return playerBattleStatus.isDead();
  }

  public void deactivateAllEquipments() {
    playerBattleStatus.deactivateAllEquipments();
  }

  public Level level() {
    return playerBattleStatus.level();
  }

  public Experience experience() {
    return playerBattleStatus.experience();
  }

  public boolean gainExperienceAndIsLevelUp(final Experience increment) {
    return playerBattleStatus.gainExperienceAndIsLevelUp(increment);
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
