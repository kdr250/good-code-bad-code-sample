package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

import java.awt.image.BufferedImage;
import java.util.List;

public class ItemShieldBlue extends Entity implements Equipment {

  public ItemShieldBlue(GamePanel panel) {
    super(panel);

    type = typeShield;
    name = "青い盾";
    down1 = setup("objects/shield_blue", panel.tileSize, panel.tileSize);
    defenseValue = 2;
    description = "[" + name + "]\n少し強い盾";
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
