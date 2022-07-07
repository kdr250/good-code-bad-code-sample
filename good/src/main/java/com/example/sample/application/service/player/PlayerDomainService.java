package com.example.sample.application.service.player;

import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.worldmap.Vector;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * プレイヤーのドメインサービス
 */
@Service
public class PlayerDomainService {

  /**
   * プレイヤーを移動する
   */
  public void move(final Player player, final List<Collidable> collidableList, final Vector vector) {
    if (!player.canMove(collidableList, vector)) {
      player.changeDirection(vector);
      return;
    }

    player.move(vector);
  }
}
