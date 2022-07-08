package com.example.sample.infrastructure.datasource.interactive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InteractiveMapper {

  List<InteractiveImageDto> selectInteractiveImageDtoList(@Param("names") List<String> names);

  List<InteractiveDto> selectInteractiveDtoList(@Param("worldId") Integer worldId);
}
