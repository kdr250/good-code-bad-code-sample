package com.example.sample.application.service;

import com.example.sample.application.repository.NpcRepository;
import com.example.sample.domain.model.NpcAnimation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NpcQueryService {

  private final NpcRepository npcRepository;

  public NpcAnimation find() {
    return npcRepository.find();
  }
}
