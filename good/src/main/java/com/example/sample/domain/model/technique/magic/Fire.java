package com.example.sample.domain.model.technique.magic;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.MagicPoint;

public class Fire implements Magic {

  @Override
  public String name() {
    return "ファイア";
  }

  @Override
  public MagicPoint costMagicPoint(final Player player) {
    return new MagicPoint(2);
  }

  @Override
  public AttackPower attackPower(final Player player) {
    final int value = 2 + (int)(player.getLevel() * 0.5);
    return new AttackPower(value);
  }
}
