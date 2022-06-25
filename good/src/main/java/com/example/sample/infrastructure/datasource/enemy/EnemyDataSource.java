package com.example.sample.infrastructure.datasource.enemy;

import com.example.sample.application.repository.EnemyRepository;
import com.example.sample.domain.model.EnemyAnimation;
import com.example.sample.domain.model.EnemyAnimationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EnemyDataSource implements EnemyRepository {

  private final EnemyMapper enemyMapper;

  @Override
  public EnemyAnimation find() {
    Map<EnemyAnimationType, BufferedImage> animationMap =
      enemyMapper.selectEnemyImageDto(EnemyAnimationType.names()).stream()
        .collect(Collectors.toMap(EnemyImageDto::toEnemyAnimationType, EnemyImageDto::bufferedImage));

    return new EnemyAnimation(animationMap);
  }
}
