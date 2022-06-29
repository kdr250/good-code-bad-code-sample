package com.example.sample.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class WorldMap {
  private final Tile[][] tiles;

  public List<Collidable> getTilesFromLocation(final Location location) {
    int tileX = location.getX() / Tile.TILE_SIZE;
    int tileY = location.getY() / Tile.TILE_SIZE;

    Tile tile1 = tiles[tileY][tileX];
    Tile tile2 = tiles[tileY + 1][tileX];
    Tile tile3 = tiles[tileY][tileX + 1];
    Tile tile4 = tiles[tileY + 1][tileX + 1];

    List<Collidable> tileList = new ArrayList<>();
    if (tile1.contains(location)) tileList.add(tile1);
    if (tile2.contains(location)) tileList.add(tile2);
    if (tile3.contains(location)) tileList.add(tile3);
    if (tile4.contains(location)) tileList.add(tile4);
    return tileList;
  }
}
