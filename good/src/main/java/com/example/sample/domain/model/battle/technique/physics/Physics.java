package com.example.sample.domain.model.battle.technique.physics;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;

/**
 * 物理攻撃
 */
public interface Physics {
  String name();
  AttackPower attackPower(Level level);
  String description();
}
