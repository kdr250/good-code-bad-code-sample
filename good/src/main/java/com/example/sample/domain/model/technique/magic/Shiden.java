package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.MagicPoint;

public class Shiden implements Magic {

  @Override
  public String name() {
    return "紫電";
  }

  @Override
  public MagicPoint costMagicPoint(final Player player) {
    final int value = 3 + (int)(player.getLevel() * 0.2);
    return new MagicPoint(value);
  }

  @Override
  public AttackPower attackPower(final Player player) {
    final int value = 4 + (int)(player.getLevel() * 1.5);
    return new AttackPower(value);
  }
}
