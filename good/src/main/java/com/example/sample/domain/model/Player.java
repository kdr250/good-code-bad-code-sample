package com.example.sample.domain.model;

import com.example.sample.domain.model.battle.PlayerBattleStatus;
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
  private final PlayerAnimation playerAnimation;
  private PlayerItems playerItems;
  private final PlayerBattleStatus playerBattleStatus;

  public Player(final Location location, final PlayerAnimation playerAnimation) {
    this.location = location;
    collision = new Collision(location);
    this.playerAnimation = playerAnimation;
    direction = Direction.DOWN;
    playerItems = new PlayerItems();
    playerBattleStatus = PlayerBattleStatus.initialize();
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

  public void pickUp(Item item) {
    playerItems = playerItems.add(item);
  }

  public boolean hasKey() {
    return playerItems.hasKey();
  }

  public void deleteKey() {
    playerItems = playerItems.deleteKeyIfExists();
  }

  public boolean isOverlap(final Item item) {
    return collision.isCollide(item.getCollision());
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
