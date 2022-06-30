package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.MagicPoint;

public interface Magic {
  String name();
  MagicPoint costMagicPoint(Player player);
  AttackPower attackPower(Player player);
}
