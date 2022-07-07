package com.example.sample.domain.model.battle.technique;

import com.example.sample.domain.model.battle.Level;

/**
 * 技
 */
public interface Technique {
  String displayName();
  String description();
  int attack(Level level);
}
