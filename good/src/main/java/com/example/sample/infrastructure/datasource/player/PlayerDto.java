package com.example.sample.infrastructure.datasource.player;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Tile;

public class PlayerDto {
  private int locationX;
  private int locationY;

  public Location getLocation() {
    return new Location(locationX * Tile.TILE_SIZE, locationY * Tile.TILE_SIZE);
  }
}
