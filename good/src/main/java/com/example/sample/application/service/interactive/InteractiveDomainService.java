package com.example.sample.application.service.interactive;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.interactive.Interactions;
import com.example.sample.domain.model.interactive.Interactive;
import org.springframework.stereotype.Service;

/**
 * 相互作用トリガーのドメインサービス
 */
@Service
public class InteractiveDomainService {

  /**
   * 相互作用する
   */
  public void interact(final Interactions interactions, final Player player, final GameMode gameMode) {
    for (Interactive interactive : interactions.interactions()) {
      if (!interactive.contains(player.location())) continue;

      boolean result = executeEvent(interactive.interact(), player, gameMode);
      if (result) interactions.remove(interactive);
      break;
    }
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
