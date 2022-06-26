package com.example.sample.presentation;

import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class KeyInputHandlerForItemList implements KeyListener {

  private KeyInputType keyInputType = KeyInputType.NOT_PAINT;
  private final Set<KeyInputType> pressedKey = new HashSet<>(List.of(KeyInputType.NOT_DISPLAY_ITEM_LIST));

  @Override
  public void keyTyped(KeyEvent e) {
  }

  // TODO: キー操作が安定しないので修正すること
  @Override
  public void keyPressed(KeyEvent e) {
    pressedKey.clear();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    KeyInputType key = KeyInputType.fromItemList(e.getKeyCode());
    pressedKey.add(key);
    keyInputType = key;
  }

  public KeyInputType getKeyInputType() {
    if (pressedKey.contains(keyInputType)) return KeyInputType.NOT_PAINT;
    return keyInputType;
  }
}
