package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class ItemKey extends Entity {

  public ItemKey(GamePanel panel) {
    super(panel);
    name = "カギ";
    down1 = setup("objects/key", panel.tileSize, panel.tileSize);
    description = "[" + name + "]\nドアを開ける";
  }
}
