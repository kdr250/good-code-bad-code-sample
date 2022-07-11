package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

/**
 * リスト4.7 武器を表現するクラス
 */
public class Weapon extends Entity {
  final AttackPower attackPower;

  public Weapon(AttackPower attackPower, GamePanel panel) {
    super(panel);
    this.attackPower = attackPower;

    type = typeSword;
    name = "剣";
    down1 = setup("objects/sword_normal", panel.tileSize, panel.tileSize);
    description = "[" + name + "]\n普通の剣";
  }

  @Override
  public AttackPower attackPower() {
    return attackPower;
  }
}
