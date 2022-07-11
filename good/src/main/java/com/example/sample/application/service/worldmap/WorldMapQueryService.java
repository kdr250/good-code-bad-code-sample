package com.example.sample.application.service.worldmap;

import com.example.sample.application.repository.worldmap.WorldMapRepository;
import com.example.sample.domain.model.worldmap.WorldMap;
import org.springframework.stereotype.Service;

/**
 * ワールドマップの参照サービス
 */
@Service
public class WorldMapQueryService {

  private final WorldMapRepository worldMapRepository;

  public WorldMapQueryService(WorldMapRepository worldMapRepository) {
    this.worldMapRepository = worldMapRepository;
  }

  /**
   * ワールドマップをみつける
   */
  public WorldMap find() {
    return worldMapRepository.find();
  }
}
