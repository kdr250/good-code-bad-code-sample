package com.example.sample.application.service.npc;

import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.character.npc.Npc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NPCのドメインサービス
 */
@Service
public class NpcDomainService {

  /**
   * NPCを移動する
   */
  public void move(final Npc npc, List<Collidable> collidableList) {
    if (!npc.updateMovementThenCanMove(collidableList)) {
      npc.changeDirection();
      return;
    }

    npc.move();
  }
}
