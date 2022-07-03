package com.example.sample.infrastructure.datasource.player;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper {
  List<PlayerImageDto> selectPlayerImageDto();

  PlayerDto selectPlayerDto(@Param("worldId") Integer worldId);
}
