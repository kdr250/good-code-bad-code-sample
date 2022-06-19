package com.example.sample.infrastructure.datasource;

import com.example.sample.application.repository.WorldMapRepository;
import com.example.sample.domain.model.WorldMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorldMapDataSource implements WorldMapRepository {
  private final WorldMapMapper worldMapMapper;

  @Override
  public WorldMap find(Integer worldId) {
    return worldMapMapper.selectWorldMapDto(worldId).toWorldMap();
  }
}
