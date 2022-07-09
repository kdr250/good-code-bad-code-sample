package com.example.sample.domain.model.battle.technique;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

/**
 * 技
 */
public interface Technique {
  String displayName();
  String description();
  AttackPower attackPower(Level level);
  MagicPoint costMagicPoint(Level level);
}
