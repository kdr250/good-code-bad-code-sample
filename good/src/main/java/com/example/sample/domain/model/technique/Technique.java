package com.example.sample.domain.model.technique;

import com.example.sample.domain.model.battle.Level;

public interface Technique {
  String displayName();
  String description();
  int attack(Level level);
}
