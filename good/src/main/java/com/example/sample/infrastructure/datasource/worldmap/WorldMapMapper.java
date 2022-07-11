package com.example.sample.infrastructure.datasource.worldmap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorldMapMapper {
  WorldMapDto selectWorldMapDto(@Param("worldId") Integer worldId);

  List<TileImageDto> selectTileImageDto(@Param("names") List<String> names);
}
