package com.example.sample.infrastructure.datasource.player;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {
  List<PlayerImageDto> selectPlayerImageDto();
}
