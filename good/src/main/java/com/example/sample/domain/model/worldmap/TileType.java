package com.example.sample.domain.model.worldmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * タイルの種類
 */
public enum TileType {
  GRASS,
  WALL,
  WATER,
  FLOOR,
  TREE,
  SAND;

  private static final List<TileType> collisionTileList = Arrays.asList(WALL, WATER, TREE);

  public static TileType from(String emoji) {
    switch (emoji) {
      case "\uD83D\uDFE9": // 🟩
        return GRASS;
      case "\uD83E\uDDF1": // 🧱
        return WALL;
      case "\uD83C\uDF0A": // 🌊
        return WATER;
      case "\uD83D\uDFEB": // 🟫
        return FLOOR;
      case "\uD83C\uDF33": // 🌳
        return TREE;
      case "\uD83D\uDFE7": // 🟧
        return SAND;
      default:
        throw new IllegalArgumentException();
    }
  }

  public static List<String> names() {
    return Arrays.stream(values()).map(TileType::name).collect(Collectors.toList());
  }

  public boolean requireCollision() {
    return collisionTileList.contains(this);
  }
}
