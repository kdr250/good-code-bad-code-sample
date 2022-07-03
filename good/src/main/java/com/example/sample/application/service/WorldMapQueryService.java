package com.example.sample.application.service;

import com.example.sample.application.repository.WorldMapRepository;
import com.example.sample.domain.model.WorldMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorldMapQueryService {

  private final WorldMapRepository worldMapRepository;

  public WorldMap find() {
    return worldMapRepository.find();
  }
}
