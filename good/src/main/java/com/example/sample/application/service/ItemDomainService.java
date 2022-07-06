package com.example.sample.application.service;

import com.example.sample.domain.model.Items;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.Interactive;
import com.example.sample.domain.model.item.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemDomainService {

  public void pickedUpOrInteract(final Items items, final Player player, final GameMode gameMode) {
    for (Item item : items.items()) {
      if (!player.isOverlap(item)) continue;

      if (item instanceof Interactive) {
        executeEvent(items, (Interactive) item, player, gameMode);
        continue;
      }

      player.pickUp(item);
      items.remove(item);
      break;
    }
  }

  private void executeEvent(final Items items, final Interactive interactiveItem, final Player player, final GameMode gameMode) {
    Event event = interactiveItem.interact();
    if (event instanceof GameModeEvent) {
      ((GameModeEvent) event).execute(gameMode);
      return;
    }
    if (event instanceof PlayerEvent && ((PlayerEvent) event).execute(player)) {
      items.remove(interactiveItem);
      return;
    }
  }
}
