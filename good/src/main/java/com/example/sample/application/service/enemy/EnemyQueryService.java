package com.example.sample.application.service.enemy;

import com.example.sample.application.repository.enemy.EnemyRepository;
import com.example.sample.domain.model.character.enemy.Enemies;
import org.springframework.stereotype.Service;

/**
 * 敵の参照サービス
 */
@Service
public class EnemyQueryService {

  private final EnemyRepository enemyRepository;

  public EnemyQueryService(EnemyRepository enemyRepository) {
    this.enemyRepository = enemyRepository;
  }

  /**
   * 敵のコレクションをみつける
   */
  public Enemies find() {
    return enemyRepository.find();
  }
}
