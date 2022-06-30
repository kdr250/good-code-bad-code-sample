package com.example.sample.domain.model.technique.physics;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

public interface Physics {
  String name();
  AttackPower attackPower(Level level);
  String description();
}
