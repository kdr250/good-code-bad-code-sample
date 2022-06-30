package com.example.sample.domain.model.technique;

import com.example.sample.domain.model.technique.magic.MagicType;
import com.example.sample.domain.model.technique.physics.PhysicsType;

import java.util.Collections;
import java.util.List;

public class PlayerTechniques {
  private final List<Technique> list;
  private static final int SIZE = 4;

  private PlayerTechniques(final List<Technique> list) {
    if (list.size() != SIZE) {
      throw new IllegalArgumentException();
    }

    this.list = list;
  }

  public static PlayerTechniques initialize() {
    List<Technique> list = List.of(PhysicsType.PUNCH, MagicType.FIRE, MagicType.SHIDEN, MagicType.HELL_FIRE);
    return new PlayerTechniques(list);
  }

  public List<Technique> getList() {
    return Collections.unmodifiableList(list);
  }
}
