package com.example.sample.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TileType {
  GRASS("\uD83D\uDFE9"),
  WALL("\uD83E\uDDF1"),
  WATER("\uD83C\uDF0A"),
  FLOOR("\uD83D\uDFEB"),
  TREE("\uD83C\uDF33"),
  SAND("\uD83D\uDFE7");

  private static List<TileType> collisionTileList = Arrays.asList(WALL, WATER, TREE);
  private String emoji;

  TileType(String emoji) {
    this.emoji = emoji;
  }

  public static TileType from(String emoji) {
    return Arrays.stream(values()).filter(t -> t.emoji.equals(emoji))
        .findFirst().orElseThrow(IllegalArgumentException::new);
  }

  public static List<String> names() {
    return Arrays.stream(values()).map(TileType::name).collect(Collectors.toList());
  }

  public boolean requireCollision() {
    return collisionTileList.contains(this);
  }
}
