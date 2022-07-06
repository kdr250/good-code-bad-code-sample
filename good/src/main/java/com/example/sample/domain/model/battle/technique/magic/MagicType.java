package com.example.sample.domain.model.battle.technique.magic;

import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.technique.Technique;

public enum MagicType implements Technique {
  FIRE(new Fire()),
  SHIDEN(new Shiden()),
  HELL_FIRE(new HellFire());

  private final Magic magic;

  MagicType(final Magic magic) {
    this.magic = magic;
  }

  public Magic getMagic() {
    return magic;
  }

  @Override
  public String displayName() {
    return magic.name();
  }

  @Override
  public String description() {
    return magic.description();
  }

  @Override
  public int attack(Level level) {
    return magic.attackPower(level).getValue();
  }
}