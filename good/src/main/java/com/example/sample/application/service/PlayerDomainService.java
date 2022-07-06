package com.example.sample.application.service;

import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Vector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerDomainService {

  public void move(final Player player, final List<Collidable> collidableList, final Vector vector) {
    if (!player.canMove(collidableList, vector)) {
      player.changeDirection(vector);
      return;
    }

    player.move(vector);
  }
}
