package com.example.sample.domain.model;

import java.util.Collections;
import java.util.List;

public class Npcs {
  private final List<Npc> npcs;

  public Npcs(final List<Npc> npcs) {
    this.npcs = npcs;
  }

  public List<Npc> npcs() {
    return Collections.unmodifiableList(npcs);
  }
}
