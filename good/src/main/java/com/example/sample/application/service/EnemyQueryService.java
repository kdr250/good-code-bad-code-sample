package com.example.sample.application.service;

import com.example.sample.application.repository.EnemyRepository;
import com.example.sample.domain.model.EnemyAnimation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnemyQueryService {

  private final EnemyRepository enemyRepository;

  public EnemyAnimation find() {
    return enemyRepository.find();
  }
}
