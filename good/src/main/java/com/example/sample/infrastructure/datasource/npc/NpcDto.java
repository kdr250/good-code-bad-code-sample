package com.example.sample.infrastructure.datasource.npc;

import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.character.npc.Npc;
import com.example.sample.domain.model.character.npc.NpcAnimation;
import com.example.sample.domain.model.worldmap.Tile;

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
