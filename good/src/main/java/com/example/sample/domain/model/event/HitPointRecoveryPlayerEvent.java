package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public class HitPointRecoveryPlayerEvent implements PlayerEvent {
  private final int recoveryAmount;

  public HitPointRecoveryPlayerEvent(final int recoveryAmount) {
    this.recoveryAmount = recoveryAmount;
  }

  @Override
  public boolean execute(Player player) {
    // TODO: Not yet implemented
    System.out.println("=====");
    System.out.println("Player HitPoint Recovered! : " + recoveryAmount);
    System.out.println("=====");
    return true;
  }
}
