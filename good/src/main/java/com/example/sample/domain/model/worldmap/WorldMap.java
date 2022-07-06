package com.example.sample.domain.model.worldmap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class WorldMap {
  private final Tile[][] tiles;
  private final Location playerStartLocation;

  public List<Tile> getTilesFromLocation(final Location location) {
    int tileX = location.getX() / Tile.TILE_SIZE;
    int tileY = location.getY() / Tile.TILE_SIZE;

    return IntStream.rangeClosed(0, 3)
      .mapToObj(number -> getTile(number, tileX, tileY))
      .filter(tile -> tile.contains(location))
      .collect(Collectors.toList());
  }

  private Tile getTile(int number, int tileX, int tileY) {
    char[] binaryChars = String.format("%2s", Integer.toBinaryString(number)).replace(" ", "0").toCharArray();
    return tiles[tileY + (binaryChars[0] - '0')][tileX + (binaryChars[1] - '0')];
  }
}
