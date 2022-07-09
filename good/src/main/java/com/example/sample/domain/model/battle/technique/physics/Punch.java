package com.example.sample.domain.model.battle.technique.physics;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;

/**
 * 正拳突き
 */
public class Punch implements Physics {

  @Override
  public String name() {
    return "正拳突き";
  }

  @Override
  public AttackPower attackPower(Level level) {
    return new AttackPower(2);
  }

  @Override
  public String description() {
    return "突きをくりだす コストなし";
  }
}
