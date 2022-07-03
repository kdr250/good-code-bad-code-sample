package com.example.sample.infrastructure.datasource.npc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NpcMapper {

  List<NpcImageDto> selectNpcImageDto(@Param("names") List<String> names);

  List<NpcDto> selectNpcDto(@Param("worldId") Integer worldId);
}
