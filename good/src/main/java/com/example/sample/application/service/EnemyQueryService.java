package com.example.sample.application.service;

import com.example.sample.application.repository.EnemyRepository;
import com.example.sample.domain.model.Enemy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnemyQueryService {

  private final EnemyRepository enemyRepository;

  public List<Enemy> find() {
    return enemyRepository.find();
  }
}
