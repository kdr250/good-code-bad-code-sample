package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;

public class HitPointRecoveryEvent implements Event {
  private final int recoveryAmount;

  public HitPointRecoveryEvent(final int recoveryAmount) {
    this.recoveryAmount = recoveryAmount;
  }

  @Override
  public void execute(Player player) {
    // TODO: Not yet implemented
    System.out.println("=====");
    System.out.println("Player HitPoint Recovered! : " + recoveryAmount);
    System.out.println("=====");
  }
}
