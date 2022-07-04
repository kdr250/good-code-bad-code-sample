package com.example.sample.domain.model.technique.physics;

import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.technique.Technique;

public enum PhysicsType implements Technique {
  PUNCH(new Punch()),
  KICK(new Kick());

  private final Physics physics;

  PhysicsType(final Physics physics) {
    this.physics = physics;
  }

  public Physics getPhysics() {
    return physics;
  }

  @Override
  public String displayName() {
    return physics.name();
  }

  @Override
  public String description() {
    return physics.description();
  }

  @Override
  public int attack(Level level) {
    return physics.attackPower(level).getValue();
  }
}
