package com.example.sample.infrastructure.datasource.enemy;

import com.example.sample.application.repository.enemy.EnemyRepository;
import com.example.sample.domain.model.character.enemy.Enemies;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.character.enemy.EnemyAnimation;
import com.example.sample.domain.model.character.enemy.EnemyAnimationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EnemyDataSource implements EnemyRepository {

  private final EnemyMapper enemyMapper;

  private static final int FIRST_WORLD_ID = 1;

  @Override
  public Enemies find() {
    Map<EnemyAnimationType, BufferedImage> animationMap =
        enemyMapper.selectEnemyImageDto(EnemyAnimationType.names()).stream()
            .collect(Collectors.toMap(EnemyImageDto::toEnemyAnimationType, EnemyImageDto::bufferedImage));
    EnemyAnimation enemyAnimation = new EnemyAnimation(animationMap);

    List<Enemy> result = enemyMapper.selectEnemyDto(FIRST_WORLD_ID).stream()
        .map(enemyDto -> enemyDto.toEnemy(enemyAnimation))
        .collect(Collectors.toList());

    return new Enemies(result);
  }
}
