package com.example.sample.infrastructure.datasource;

import com.example.sample.application.repository.WorldMapRepository;
import com.example.sample.domain.model.TileType;
import com.example.sample.domain.model.WorldMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class WorldMapDataSource implements WorldMapRepository {
  private final WorldMapMapper worldMapMapper;

  @Override
  public WorldMap find(Integer worldId) {
    Map<TileType, BufferedImage> map = worldMapMapper.selectTileImageDto()
        .stream().collect(Collectors.toMap(TileImageDto::toTileType, TileImageDto::bufferedImage));

    WorldMapDto worldMapDto = worldMapMapper.selectWorldMapDto(worldId);
    return worldMapDto.toWorldMap(map);
  }
}
