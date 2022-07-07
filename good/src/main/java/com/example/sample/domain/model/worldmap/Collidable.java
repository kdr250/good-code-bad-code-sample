package com.example.sample.domain.model.worldmap;

/**
 * 衝突可能
 */
public interface Collidable {
  public Location getLocation();
  public Collision getCollision();
}
