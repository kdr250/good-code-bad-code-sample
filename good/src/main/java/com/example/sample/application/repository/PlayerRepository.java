package com.example.sample.application.repository;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.PlayerAnimation;

public interface PlayerRepository {
  Player find();

  PlayerAnimation findAnimation();
}
