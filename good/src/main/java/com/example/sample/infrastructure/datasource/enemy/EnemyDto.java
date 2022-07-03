package com.example.sample.infrastructure.datasource.enemy;

import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.EnemyAnimation;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Tile;

public class EnemyDto {
  private String name;
  private int locationX;
  private int locationY;

  private Location getLocation() {
    return new Location(locationX * Tile.TILE_SIZE, locationY * Tile.TILE_SIZE);
  }

  public Enemy toEnemy(EnemyAnimation enemyAnimation) {
    return new Enemy(name, getLocation(), enemyAnimation);
  }
}
