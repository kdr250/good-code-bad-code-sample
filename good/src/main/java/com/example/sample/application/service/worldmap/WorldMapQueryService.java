package com.example.sample.application.service.worldmap;

import com.example.sample.application.repository.worldmap.WorldMapRepository;
import com.example.sample.domain.model.worldmap.WorldMap;
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
