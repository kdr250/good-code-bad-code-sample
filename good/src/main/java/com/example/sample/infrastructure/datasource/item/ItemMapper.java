package com.example.sample.infrastructure.datasource.item;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

  List<ItemImageDto> selectItemImageDto(@Param("names") List<String> names);

  List<ItemDto> selectItemDto(@Param("worldId") Integer worldId);
}
