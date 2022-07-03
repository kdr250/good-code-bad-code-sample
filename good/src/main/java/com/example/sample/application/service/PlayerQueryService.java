package com.example.sample.application.service;

import com.example.sample.application.repository.PlayerRepository;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.PlayerAnimation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerQueryService {

  private final PlayerRepository playerRepository;

  public Player find() {
    return playerRepository.find();
  }

  public PlayerAnimation findAnimation() {
    return playerRepository.findAnimation();
  }
}
