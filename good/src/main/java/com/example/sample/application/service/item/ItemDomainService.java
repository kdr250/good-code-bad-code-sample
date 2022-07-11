package com.example.sample.application.service.item;

import com.example.sample.domain.model.item.Consumable;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.Items;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.interactive.Interactive;
import com.example.sample.domain.model.item.Item;
import org.springframework.stereotype.Service;

/**
 * アイテムのドメインサービス
 */
@Service
public class ItemDomainService {

  /**
   * アイテムが取得されるかプレイヤーに働きかける
   */
  public void pickedUpOrInteract(final Items items, final Player player, final GameMode gameMode) {
    for (Item item : items.items()) {
      if (!player.isOverlap(item)) continue;

      if (item instanceof Interactive) {
        boolean result = executeEvent(((Interactive) item).interact(), player, gameMode);
        if (result) items.remove(item);
        break;
      }

      player.pickUp(item);
      items.remove(item);
      break;
    }
  }

  /**
   * アイテムが消費されるか装備される
   */
  public void consumedOrEquipped(final Item item, final Player player, final GameMode gameMode) {
    if (item instanceof Consumable) {
      executeEvent(((Consumable) item).consume(), player, gameMode);
      return;
    }
    if (item instanceof Equipment) {
      executeEvent(((Equipment) item).equip(), player, gameMode);
      return;
    }

    throw new IllegalArgumentException();
  }

  private boolean executeEvent(final Event event, final Player player, final GameMode gameMode) {
    if (event instanceof GameModeEvent) {
      return ((GameModeEvent) event).execute(gameMode);
    }
    if (event instanceof PlayerEvent) {
      return ((PlayerEvent) event).execute(player);
    }

    throw new IllegalArgumentException();
  }
}
