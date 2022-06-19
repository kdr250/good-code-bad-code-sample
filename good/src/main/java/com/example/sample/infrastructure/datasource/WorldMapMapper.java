package com.example.sample.infrastructure.datasource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorldMapMapper {
  WorldMapDto selectWorldMapDto(@Param("worldId") Integer worldId);
}
