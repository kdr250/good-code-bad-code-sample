package com.example.sample.infrastructure.datasource;

import com.example.sample.application.repository.WorldMapRepository;
import com.example.sample.domain.model.TileType;
import com.example.sample.domain.model.WorldMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class WorldMapDataSource implements WorldMapRepository {
  private final WorldMapMapper worldMapMapper;

  private static final int FIRST_WORLD_ID = 1;

  @Override
  public WorldMap find() {
    Map<TileType, BufferedImage> imageMap = worldMapMapper.selectTileImageDto(TileType.names())
        .stream().collect(Collectors.toMap(TileImageDto::toTileType, TileImageDto::bufferedImage));

    WorldMapDto worldMapDto = worldMapMapper.selectWorldMapDto(FIRST_WORLD_ID);
    return worldMapDto.toWorldMap(imageMap);
  }
}
