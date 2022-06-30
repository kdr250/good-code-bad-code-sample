package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.MagicPoint;

public class HellFire implements Magic {

  @Override
  public String name() {
    return "地獄の業火";
  }

  @Override
  public MagicPoint costMagicPoint(Player player) {
    return new MagicPoint(6);
  }

  @Override
  public AttackPower attackPower(Player player) {
    final int value = 5 + player.getLevel() * 2;
    return new AttackPower(value);
  }
}
