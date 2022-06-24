package com.example.sample.domain.model;

import com.example.sample.domain.type.Direction;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.List;

@Getter
public class Npc {
  private Location location;
  private Collision collision;
  private Direction direction;
  private final NpcAnimation npcAnimation;

  public Npc(final Location location, final NpcAnimation npcAnimation) {
    this.location = location;
    this.collision = new Collision(location);
    this.npcAnimation = npcAnimation;
    direction = Direction.DOWN;
  }

  public boolean canMove(final List<Tile> tiles, final Vector vector) {
    return tiles.stream().noneMatch(tile -> collision.willCollide(tile.getCollision(), vector));
  }

  public BufferedImage getAnimatedImage() {
    return npcAnimation.getAnimatedImage(direction);
  }
}
