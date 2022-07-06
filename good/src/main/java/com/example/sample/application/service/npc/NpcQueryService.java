package com.example.sample.application.service.npc;

import com.example.sample.application.repository.npc.NpcRepository;
import com.example.sample.domain.model.character.npc.Npcs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NpcQueryService {

  private final NpcRepository npcRepository;

  public Npcs find() {
    return npcRepository.find();
  }
}
