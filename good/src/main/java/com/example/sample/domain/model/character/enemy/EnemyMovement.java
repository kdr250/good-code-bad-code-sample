package com.example.sample.domain.model.character.enemy;

import com.example.sample.domain.model.worldmap.Vector;

/**
 * 敵の移動
 */
public class EnemyMovement {
  private Vector vector;
  private int count = 0;
  private static final int REPEAT_INTERVAL = 90;
  private static final int DECIDE_VECTOR_TIME = 60;

  public EnemyMovement() {
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

  public Vector vector() {
    return vector;
  }
}
