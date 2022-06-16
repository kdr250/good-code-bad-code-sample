package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class MagicalWeapon extends Entity {
  final AttackPower attackPower;

  public MagicalWeapon(AttackPower attackPower, GamePanel panel) {
    super(panel);

    type = typeMagicalSword;
    name = "魔法の杖";
    down1 = setup("objects/magical_weapon", panel.tileSize, panel.tileSize);
    this.attackPower = attackPower;
    maxMagicPointIncrements.add(2);
    description = "[" + name + "]\n魔法力最大値+2";
  }

  @Override
  public AttackPower attackPower() {
    return attackPower;
  }
}

