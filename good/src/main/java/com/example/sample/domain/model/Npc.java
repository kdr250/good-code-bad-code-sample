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
  private final NpcMovement npcMovement;

  public Npc(final Location location, final NpcAnimation npcAnimation) {
    this.location = location;
    collision = new Collision(location);
    direction = Direction.DOWN;
    this.npcAnimation = npcAnimation;
    npcMovement = new NpcMovement();
  }

  public void move() {
    Vector vector = npcMovement.getVector();
    location = location.shift(vector);
    collision = collision.shift(vector);
    direction = vector.getDirection();
  }

  public boolean updateMovementThenCanMove(final List<Collidable> collidableList) {
    npcMovement.update();
    Vector vector = npcMovement.getVector();
    return collidableList.stream().noneMatch(c -> collision.willCollide(c.getCollision(), vector));
  }

  public void changeDirection() {
    direction = npcMovement.getVector().getDirection();
  }

  public BufferedImage getAnimatedImage() {
    return npcAnimation.getAnimatedImage(direction);
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
