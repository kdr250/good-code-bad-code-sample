package com.example.sample.infrastructure.datasource.npc;

import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Npc;
import com.example.sample.domain.model.NpcAnimation;
import com.example.sample.domain.model.Tile;

public class NpcDto {
  private int locationX;
  private int locationY;

  private Location getLocation() {
    return new Location(locationX * Tile.TILE_SIZE, locationY *  Tile.TILE_SIZE);
  }

  public Npc toNpc(NpcAnimation npcAnimation) {
    return new Npc(getLocation(), npcAnimation);
  }
}
