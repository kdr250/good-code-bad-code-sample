package com.example.sample.domain.model.event;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.item.Item;

public class ConsumeItemAndHitPointRecoveryPlayerEvent implements PlayerEvent {
  private final Item item;
  private final int recoveryAmount;

  public ConsumeItemAndHitPointRecoveryPlayerEvent(final Item item, final int recoveryAmount) {
    this.item = item;
    this.recoveryAmount = recoveryAmount;
  }

  @Override
  public boolean execute(Player player) {
    player.recoverHitPoint(recoveryAmount);
    player.removeItem(item);
    return true;
  }
}
