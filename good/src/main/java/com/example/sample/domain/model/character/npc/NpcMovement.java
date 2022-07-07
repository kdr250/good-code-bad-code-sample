package com.example.sample.domain.model.character.npc;

import com.example.sample.domain.model.worldmap.Vector;

/**
 * NPCの移動
 */
public class NpcMovement {
  private Vector vector;
  private int count = 0;
  private static final int REPEAT_INTERVAL = 216;
  private static final int DECIDE_VECTOR_TIME = 120;

  public NpcMovement() {
    vector = Vector.NONE;
  }

  public void update() {
    count++;
    count %= REPEAT_INTERVAL;

    switch (Integer.compare(count, DECIDE_VECTOR_TIME)) {
      case -1:
        vector = Vector.NONE;
        return;
      case 0:
        vector = Vector.random(1);
    }
  }

  public Vector getVector() {
    return vector;
  }
}
