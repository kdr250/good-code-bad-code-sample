package com.example.sample.presentation;

import com.example.sample.domain.model.Vector;

public enum KeyInputType {
  UP(Vector.up(4)),
  DOWN(Vector.down(4)),
  LEFT(Vector.left(4)),
  RIGHT(Vector.right(4)),
  DECIDE(Vector.NONE),
  NONE(Vector.NONE);

  private final Vector vector;

  KeyInputType(Vector vector) {
    this.vector = vector;
  }

  public Vector getVector() {
    return vector;
  }
}
