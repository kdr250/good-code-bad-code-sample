package com.example.sample.domain.model.event;

import com.example.sample.domain.model.battle.MagicPoint;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.item.Item;

/**
 * 魔法力回復イベント
 */
public class ConsumeItemAndMagicPointRecoveryPlayerEvent implements PlayerEvent {
  private final Item item;
  private final MagicPoint recoveryAmount;

  public ConsumeItemAndMagicPointRecoveryPlayerEvent(final Item item, final int recoveryAmount) {
    this.item = item;
    this.recoveryAmount = new MagicPoint(recoveryAmount);
  }

  @Override
  public boolean execute(Player player) {
    player.recoverMagicPoint(recoveryAmount);
    player.removeItem(item);
    return true;
  }
}
