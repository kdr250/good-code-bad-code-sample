package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

import java.awt.image.BufferedImage;
import java.util.List;

public class ItemBodyArmor extends Entity implements Equipment {

  public ItemBodyArmor(GamePanel panel) {
    super(panel);

    type = typeBodyArmor;
    name = "普通の鎧";
    down1 = setup("objects/armor_gray", panel.tileSize, panel.tileSize);
    defenseValue = 2;
    description = "[" + name + "]\n胴体に装備する";
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
