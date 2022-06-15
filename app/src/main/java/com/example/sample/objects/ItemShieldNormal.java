package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

import java.awt.image.BufferedImage;
import java.util.List;

public class ItemShieldNormal extends Entity implements Equipment {

  public ItemShieldNormal(GamePanel panel) {
    super(panel);

    type = typeShield;
    name = "木の盾";
    down1 = setup("objects/shield_wood", panel.tileSize, panel.tileSize);
    defenseValue = 1;
    description = "[" + name + "]\n普通の盾";
  }

  @Override
  public int defence() {
    return defenseValue;
  }

  @Override
  public BufferedImage image() {
    return down1;
  }

  @Override
  public List<Integer> maxMagicPointIncrements() {
    return maxMagicPointIncrements;
  }
}
