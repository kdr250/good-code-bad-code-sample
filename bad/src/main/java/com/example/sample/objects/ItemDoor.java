package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class ItemDoor extends Entity {

  public ItemDoor(GamePanel panel) {
    super(panel);
    name = "ドア";
    down1 = setup("objects/door", panel.tileSize, panel.tileSize);
    collision = true;

    solidArea.x = 0;
    solidArea.y = 16;
    solidArea.width = 48;
    solidArea.height = 32;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
  }
}
