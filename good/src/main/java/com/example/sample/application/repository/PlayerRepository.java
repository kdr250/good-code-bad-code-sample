package com.example.sample.application.repository;

import com.example.sample.domain.model.PlayerAnimation;

public interface PlayerRepository {
  // TODO: 戻り値をPlayerにリファクタリングすること
  PlayerAnimation find();
}
