package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

public class Fire implements Magic {

  @Override
  public String name() {
    return "ファイア";
  }

  @Override
  public MagicPoint costMagicPoint(final Level level) {
    return new MagicPoint(2);
  }

  @Override
  public AttackPower attackPower(final Level level) {
    final int value = 2 + (int)(level.getValue() * 0.5);
    return new AttackPower(value);
  }

  @Override
  public String description() {
    return "炎攻撃 消費魔力2";
  }
}
