package com.example.sample.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WorldMap {
  private final Tile[][] tiles;
}
