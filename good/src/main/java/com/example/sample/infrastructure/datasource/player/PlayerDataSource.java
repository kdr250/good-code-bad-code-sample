package com.example.sample.infrastructure.datasource.player;

import com.example.sample.application.repository.PlayerRepository;
import com.example.sample.domain.model.PlayerAnimation;
import com.example.sample.domain.model.PlayerAnimationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlayerDataSource implements PlayerRepository {
  private final PlayerMapper playerMapper;

  // TODO: 戻り値をPlayerにリファクタリングすること
  @Override
  public PlayerAnimation find() {
    Map<PlayerAnimationType, BufferedImage> animationMap = playerMapper.selectPlayerImageDto().stream()
        .collect(Collectors.toMap(PlayerImageDto::toPlayerAnimationType, PlayerImageDto::bufferedImage));

    return new PlayerAnimation(animationMap);
  }
}
