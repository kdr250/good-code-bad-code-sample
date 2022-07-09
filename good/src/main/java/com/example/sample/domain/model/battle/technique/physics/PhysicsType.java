package com.example.sample.domain.model.battle.technique.physics;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;
import com.example.sample.domain.model.battle.technique.Technique;

/**
 * 物理攻撃の種類
 */
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
  public AttackPower attackPower(Level level) {
    return physics.attackPower(level);
  }

  @Override
  public MagicPoint costMagicPoint(Level level) {
    return MagicPoint.NONE;
  }
}
