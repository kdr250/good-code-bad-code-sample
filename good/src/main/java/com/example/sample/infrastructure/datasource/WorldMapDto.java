package com.example.sample.infrastructure.datasource;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.TileType;
import com.example.sample.domain.model.WorldMap;

public class WorldMapDto {
  private String tiles;
  private int horizontalSize;
  private int verticalSize;

  public WorldMap toWorldMap() {
    Tile[][] result = new Tile[verticalSize][horizontalSize];

    String[] strTiles = tiles.split(" ");

    for (int i = 0; i < verticalSize; i++) {
      for (int j = 0; j < horizontalSize; j++) {
        String emoji = strTiles[i * horizontalSize + j];
        TileType tileType = TileType.from(emoji);
        Location location = new Location(0, 0);
        result[i][j] = new Tile(tileType, location);
      }
    }

    return new WorldMap(result);
  }
}
