package com.example.sample.application.service;

import com.example.sample.application.repository.NpcRepository;
import com.example.sample.domain.model.Npc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NpcQueryService {

  private final NpcRepository npcRepository;

  public List<Npc> find() {
    return npcRepository.find();
  }
}
