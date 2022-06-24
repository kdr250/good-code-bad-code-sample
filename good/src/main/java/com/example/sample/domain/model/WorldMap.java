package com.example.sample.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class WorldMap {
  private final Tile[][] tiles;

  public List<Collidable> getTilesFromLocation(Location location) {
    // TODO: Locationからx座標、y座標のタイルのインデックス絞り込みを行い効率化すること
    List<Collidable> tileList = new ArrayList<>();
    for (Tile[] tls : tiles) {
      for (Tile tile : tls) {
        if (tile.contains(location)) {
          tileList.add(tile);
        }
      }
    }
    return tileList;
  }
}
