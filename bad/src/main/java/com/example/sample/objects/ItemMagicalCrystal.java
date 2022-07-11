package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class ItemMagicalCrystal extends Entity {

  public ItemMagicalCrystal(GamePanel panel) {
    super(panel);

    name = "魔法のクリスタル";
    image = setup("objects/magical_crystal_full", panel.tileSize, panel.tileSize);
    image2 = setup("objects/magical_crystal_empty", panel.tileSize, panel.tileSize);
  }
}

