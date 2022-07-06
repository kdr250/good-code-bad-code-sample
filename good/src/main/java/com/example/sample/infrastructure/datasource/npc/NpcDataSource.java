package com.example.sample.infrastructure.datasource.npc;

import com.example.sample.application.repository.npc.NpcRepository;
import com.example.sample.domain.model.character.npc.Npc;
import com.example.sample.domain.model.character.npc.NpcAnimation;
import com.example.sample.domain.model.character.npc.NpcAnimationType;
import com.example.sample.domain.model.character.npc.Npcs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NpcDataSource implements NpcRepository {

  private final NpcMapper npcMapper;

  private static final int FIRST_WORLD_ID = 1;

  public Npcs find() {
    Map<NpcAnimationType, BufferedImage> animationMap =
      npcMapper.selectNpcImageDto(NpcAnimationType.names()).stream()
        .collect(Collectors.toMap(NpcImageDto::toNpcAnimationType, NpcImageDto::bufferedImage));
    NpcAnimation npcAnimation = new NpcAnimation(animationMap);

    List<Npc> result = npcMapper.selectNpcDto(FIRST_WORLD_ID).stream()
      .map(dto -> dto.toNpc(npcAnimation)).collect(Collectors.toList());

    return new Npcs(result);
  }
}
