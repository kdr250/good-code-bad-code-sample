package com.example.sample.application.repository.player;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.character.player.PlayerAnimation;

/**
 * プレイヤーのレポジトリ
 */
public interface PlayerRepository {
  Player find();

  PlayerAnimation findAnimation();
}
