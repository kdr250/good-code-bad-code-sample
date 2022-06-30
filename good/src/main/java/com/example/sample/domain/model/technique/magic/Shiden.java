package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

public class Shiden implements Magic {

  @Override
  public String name() {
    return "紫電";
  }

  @Override
  public MagicPoint costMagicPoint(final Level level) {
    final int value = 3 + (int)(level.getValue() * 0.2);
    return new MagicPoint(value);
  }

  @Override
  public AttackPower attackPower(final Level level) {
    final int value = 4 + (int)(level.getValue() * 1.5);
    return new AttackPower(value);
  }

  @Override
  public String description() {
    return "雷攻撃 消費魔力3+レベル*0.2";
  }
}
