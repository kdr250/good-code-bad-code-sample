package com.example.sample.domain.model;

import java.util.Arrays;

public enum TileType {
  GRASS("\uD83D\uDFE9"),
  WALL("\uD83E\uDDF1"),
  WATER("\uD83C\uDF0A"),
  FLOOR("\uD83D\uDFEB"),
  TREE("\uD83C\uDF33"),
  SAND("\uD83D\uDFE7");

  private String emoji;

  TileType(String emoji) {
    this.emoji = emoji;
  }

  public TileType from(String emoji) {
    return Arrays.stream(values()).filter(t -> t.emoji.equals(emoji))
        .findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
