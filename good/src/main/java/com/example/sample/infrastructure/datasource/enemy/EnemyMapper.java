package com.example.sample.infrastructure.datasource.enemy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnemyMapper {

  List<EnemyImageDto> selectEnemyImageDto(@Param("names") List<String> names);

  List<EnemyDto> selectEnemyDto(@Param("worldId") Integer worldId);
}
