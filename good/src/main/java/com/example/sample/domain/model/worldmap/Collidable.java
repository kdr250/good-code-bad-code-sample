package com.example.sample.domain.model.worldmap;

/**
 * 衝突可能
 */
public interface Collidable {
  Location location();
  Collision collision();
}
