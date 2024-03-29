package com.example.sample.domain.model.battle.technique.physics;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;

/**
 * 中段蹴り
 */
public class Kick implements Physics {

  @Override
  public String name() {
    return "中段蹴り";
  }

  @Override
  public AttackPower attackPower(Level level) {
    return new AttackPower(4);
  }

  @Override
  public String description() {
    return "蹴りをくりだす コストなし";
  }
}
