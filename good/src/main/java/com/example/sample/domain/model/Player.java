package com.example.sample.domain.model;

import com.example.sample.domain.model.battle.PlayerBattleStatus;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.item.Item;
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

  public boolean canMove(final List<Collidable> collidableList, final Vector vector) {
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
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
    if (playerBattleStatus.hasEquipment(equipmentType)) pickUp(playerBattleStatus.getEquipment(equipmentType));
    playerBattleStatus.equip(equipment);
    removeItem(equipment);
  }

  public void recoverHitPoint(final int recoveryAmount) {
    playerBattleStatus.recoveryHitPoint(recoveryAmount);
  }

  public void recoverMagicPoint(final int recoveryAmount) {
    playerBattleStatus.recoveryMagicPoint(recoveryAmount);
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
}
