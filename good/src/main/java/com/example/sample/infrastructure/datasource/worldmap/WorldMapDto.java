package com.example.sample.infrastructure.datasource.worldmap;

import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.worldmap.TileType;
import com.example.sample.domain.model.worldmap.WorldMap;

import java.awt.image.BufferedImage;
import java.util.Map;

public class WorldMapDto {
  private String tiles;
  private int playerStartX;
  private int playerStartY;
  private int horizontalSize;
  private int verticalSize;

  public WorldMap toWorldMap(Map<TileType, BufferedImage> map) {
    Tile[][] result = new Tile[verticalSize][horizontalSize];

    String[] strTiles = tiles.split(" ");

    for (int i = 0; i < verticalSize; i++) {
      for (int j = 0; j < horizontalSize; j++) {
        String emoji = strTiles[i * horizontalSize + j];
        TileType tileType = TileType.from(emoji);
        Location location = new Location(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);
        BufferedImage image = map.get(tileType);
        result[i][j] = new Tile(tileType, location, image);
      }
    }

    Location playerStart = new Location(playerStartX * Tile.TILE_SIZE, playerStartY * Tile.TILE_SIZE);

    return new WorldMap(result, playerStart);
  }
}
