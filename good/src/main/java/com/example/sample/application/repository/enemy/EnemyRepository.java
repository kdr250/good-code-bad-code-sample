package com.example.sample.application.repository.enemy;

import com.example.sample.domain.model.character.enemy.Enemies;

/**
 * 敵のレポジトリ
 */
public interface EnemyRepository {
  Enemies find();
}
