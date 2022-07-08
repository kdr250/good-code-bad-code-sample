package com.example.sample.infrastructure.datasource.interactive;

import com.example.sample.application.repository.interactive.InteractiveRepository;
import com.example.sample.domain.model.interactive.Interactions;
import com.example.sample.domain.model.interactive.Interactive;
import com.example.sample.domain.model.interactive.InteractiveType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InteractiveDataSource implements InteractiveRepository {

  private final InteractiveMapper interactiveMapper;

  private static final int FIRST_WORLD_ID = 1;

  @Override
  public Interactions find() {
    Map<InteractiveType, BufferedImage> imageMap = interactiveMapper.selectInteractiveImageDtoList(InteractiveType.names()).stream()
        .collect(Collectors.toMap(InteractiveImageDto::interactiveType, InteractiveImageDto::bufferedImage));

    List<Interactive> result = interactiveMapper.selectInteractiveDtoList(FIRST_WORLD_ID).stream()
        .map(interactiveDto -> interactiveDto.toInteractive(imageMap))
        .collect(Collectors.toList());

    return new Interactions(result);
  }
}
