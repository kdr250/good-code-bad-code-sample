package com.example.sample.domain.model.battle.technique.magic;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

public interface Magic {
  String name();
  MagicPoint costMagicPoint(Level level);
  AttackPower attackPower(Level level);
  String description();
}
