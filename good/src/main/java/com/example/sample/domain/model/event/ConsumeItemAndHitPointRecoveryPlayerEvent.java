package com.example.sample.domain.model.event;

import com.example.sample.domain.model.battle.HitPoint;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.item.Item;

/**
 * ヒットポイント回復イベント
 */
public class ConsumeItemAndHitPointRecoveryPlayerEvent implements PlayerEvent {
  private final Item item;
  private final HitPoint recoveryAmount;

  public ConsumeItemAndHitPointRecoveryPlayerEvent(final Item item, final int recoveryAmount) {
    this.item = item;
    this.recoveryAmount = new HitPoint(recoveryAmount);
  }

  @Override
  public boolean execute(Player player) {
    player.recoverHitPoint(recoveryAmount);
    player.removeItem(item);
    return true;
  }
}
