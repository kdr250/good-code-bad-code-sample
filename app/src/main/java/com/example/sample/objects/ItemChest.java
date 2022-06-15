package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class ItemChest extends Entity {

  public ItemChest(GamePanel panel) {
    super(panel);
    name = "宝箱";
    down1 = setup("objects/chest", panel.tileSize, panel.tileSize);
  }
}
