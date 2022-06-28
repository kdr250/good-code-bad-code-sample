package com.example.sample.presentation;

import com.example.sample.domain.model.Vector;
import com.example.sample.domain.type.Direction;

import java.awt.event.KeyEvent;

public enum KeyInputType {
  UP(Vector.up(4), Direction.UP),
  DOWN(Vector.down(4), Direction.DOWN),
  LEFT(Vector.left(4), Direction.LEFT),
  RIGHT(Vector.right(4), Direction.RIGHT),
  DECIDE(Vector.NONE, Direction.NONE),
  DISPLAY_ITEM_LIST(Vector.NONE, Direction.NONE),
  NONE(Vector.NONE, Direction.NONE);

  private final Vector vector;
  private final Direction direction;

  KeyInputType(Vector vector, Direction direction) {
    this.vector = vector;
    this.direction = direction;
  }

  public static KeyInputType from(int keyCode) {
    switch (keyCode) {
      case KeyEvent.VK_W:
        return KeyInputType.UP;
      case KeyEvent.VK_S:
        return KeyInputType.DOWN;
      case KeyEvent.VK_A:
        return KeyInputType.LEFT;
      case KeyEvent.VK_D:
        return KeyInputType.RIGHT;
      case KeyEvent.VK_I:
        return KeyInputType.DISPLAY_ITEM_LIST;
      case KeyEvent.VK_ENTER:
        return KeyInputType.DECIDE;
      default:
        return KeyInputType.NONE;
    }
  }

  public Vector getVector() {
    return vector;
  }

  public Direction getDirection() {
    return direction;
  }
}
