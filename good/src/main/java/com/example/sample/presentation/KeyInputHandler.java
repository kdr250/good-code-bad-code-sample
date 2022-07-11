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
    keyInputType = KeyInputType.from(e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyInputType = KeyInputType.NONE;
  }

  public KeyInputType getKeyInputType() {
    return keyInputType;
  }
}
