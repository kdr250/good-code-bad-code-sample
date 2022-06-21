package com.example.sample.presentation;

import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Component
public class KeyInputHandler implements KeyListener {

  private KeyInputType keyInputType = KeyInputType.NONE;

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
        keyInputType = KeyInputType.UP;
        break;
      case KeyEvent.VK_S:
        keyInputType = KeyInputType.DOWN;
        break;
      case KeyEvent.VK_A:
        keyInputType = KeyInputType.LEFT;
        break;
      case KeyEvent.VK_D:
        keyInputType = KeyInputType.RIGHT;
        break;
      default:
        keyInputType = KeyInputType.NONE;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyInputType = KeyInputType.NONE;
  }

  public KeyInputType getKeyInputType() {
    return keyInputType;
  }
}
