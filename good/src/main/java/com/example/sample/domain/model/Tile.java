package com.example.sample.domain.model;

import com.example.sample.presentation.GamePanel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@Getter
public class Tile implements Collidable {
  private final TileType tileType;
  private final Location location;
  private final BufferedImage bufferedImage;
  private final Collision collision;

  public Tile(final TileType tileType, final Location location, final BufferedImage bufferedImage) {
    this.tileType = tileType;
    this.location = location;
    this.bufferedImage = bufferedImage;
    // TODO: 要リファクタリング
    if (tileType == TileType.TREE || tileType == TileType.WALL || tileType == TileType.WATER) {
      collision = new Collision(location);
    } else {
      collision = Collision.NO_COLLISION;
    }
  }

  public boolean contains(final Location location) {
    int diffX = location.getX() - this.location.getX();
    int diffY = location.getY() - this.location.getY();

    boolean containsX = diffX >= GamePanel.tileSize * -1 && diffX <= GamePanel.tileSize;
    boolean containsY = diffY >= GamePanel.tileSize * -1 && diffY <= GamePanel.tileSize;

    return containsX && containsY;
  }

  @Override
  public Collision getCollision() {
    return collision;
  }
}
