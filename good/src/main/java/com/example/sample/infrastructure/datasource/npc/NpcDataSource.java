package com.example.sample.infrastructure.datasource.npc;

import com.example.sample.application.repository.NpcRepository;
import com.example.sample.domain.model.NpcAnimation;
import com.example.sample.domain.model.NpcAnimationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NpcDataSource implements NpcRepository {

  private final NpcMapper npcMapper;

  // TODO: 戻り値をNpcにリファクタリングすること
  @Override
  public NpcAnimation find() {
    Map<NpcAnimationType, BufferedImage> animationMap =
      npcMapper.selectNpcImageDto(NpcAnimationType.names()).stream()
        .collect(Collectors.toMap(NpcImageDto::toNpcAnimationType, NpcImageDto::bufferedImage));

    return new NpcAnimation(animationMap);
  }
}
