package com.example.sample.infrastructure.datasource.item;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

  List<ItemImageDto> selectItemImageDtoList(@Param("names") List<String> names);

  ItemImageDto selectItemImageDto(@Param("name") String names);

  List<ItemDto> selectItemDtoList(@Param("worldId") Integer worldId);
}
