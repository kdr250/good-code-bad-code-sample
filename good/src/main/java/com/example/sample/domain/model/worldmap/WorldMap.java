package com.example.sample.domain.model.worldmap;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ワールドマップ
 */
public class WorldMap {
  private final Tile[][] tiles;

  public WorldMap(final Tile[][] tiles) {
    this.tiles = tiles;
  }

  public List<Tile> getTilesFromLocation(final Location location) {
    int tileX = location.getX() / Tile.TILE_SIZE;
    int tileY = location.getY() / Tile.TILE_SIZE;

    return IntStream.rangeClosed(0, 3)
      .mapToObj(number -> getSpecifiedTile(tileX, tileY, number))
      .filter(tile -> tile.contains(location))
      .collect(Collectors.toList());
  }

  private Tile getSpecifiedTile(int tileX, int tileY, int number) {
    char[] binaryChars = String.format("%2s", Integer.toBinaryString(number)).replace(" ", "0").toCharArray();
    return tiles[tileY + (binaryChars[0] - '0')][tileX + (binaryChars[1] - '0')];
  }

  public Tile[][] tiles() {
    return tiles;
  }
}
