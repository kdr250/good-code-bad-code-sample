package com.example.sample.application.service;

import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Npc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NpcDomainService {

  public void move(final Npc npc, List<Collidable> collidableList) {
    if (!npc.updateMovementThenCanMove(collidableList)) {
      npc.changeDirection();
      return;
    }

    npc.move();
  }
}
