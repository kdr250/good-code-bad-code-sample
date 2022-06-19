package com.example.sample.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@Getter
public class Tile {
  private final TileType tileType;
  private final Location location;
  // private final BufferedImage bufferedImage;
  // TODO: コリジョンも追加すること
}
