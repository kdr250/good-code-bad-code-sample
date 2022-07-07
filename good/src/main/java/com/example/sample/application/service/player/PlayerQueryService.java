package com.example.sample.application.service.player;

import com.example.sample.application.repository.player.PlayerRepository;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.character.player.PlayerAnimation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * プレイヤーの参照サービス
 */
@Service
@RequiredArgsConstructor
public class PlayerQueryService {

  private final PlayerRepository playerRepository;

  /**
   * プレイヤーをみつける
   */
  public Player find() {
    return playerRepository.find();
  }

  /**
   * プレイヤーのアニメーションをみつける
   */
  public PlayerAnimation findAnimation() {
    return playerRepository.findAnimation();
  }
}
