package com.example.sample.domain.model;

import com.example.sample.domain.type.Direction;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
public class Npc implements Collidable {
  private Location location;
  private Collision collision;
  private Direction direction;
  private final NpcAnimation npcAnimation;

  // TODO: Movementのドメインモデルを作成すること
  private Vector vector = Vector.NONE;
  private int count = 0;

  public Npc(final Location location, final NpcAnimation npcAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.npcAnimation = npcAnimation;
    direction = Direction.DOWN;
  }

  public void move() {
    location = location.shift(vector);
    collision = collision.shift(vector);
    direction = vector.getDirection();
  }

  public boolean canMove(final List<Collidable> collidableList) {
    count++;
    count %= 180;
    if (count / 120 < 1) return false;
    if (count == 120) vector = Vector.random(1);
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public void changeDirection() {
    direction = vector.getDirection();
  }

  public BufferedImage getAnimatedImage() {
    return npcAnimation.getAnimatedImage(direction);
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
