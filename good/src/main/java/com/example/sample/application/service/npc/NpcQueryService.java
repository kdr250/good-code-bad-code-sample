package com.example.sample.application.service.npc;

import com.example.sample.application.repository.npc.NpcRepository;
import com.example.sample.domain.model.character.npc.Npcs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * NPCの参照サービス
 */
@Service
@RequiredArgsConstructor
public class NpcQueryService {

  private final NpcRepository npcRepository;

  /**
   * NPCのコレクションをみつける
   */
  public Npcs find() {
    return npcRepository.find();
  }
}
