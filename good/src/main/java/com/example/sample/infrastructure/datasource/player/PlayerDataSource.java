package com.example.sample.infrastructure.datasource.player;

import com.example.sample.application.repository.player.PlayerRepository;
import com.example.sample.domain.model.worldmap.Location;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.character.player.PlayerAnimation;
import com.example.sample.domain.model.character.player.PlayerAnimationType;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayerDataSource implements PlayerRepository {
  private final PlayerMapper playerMapper;

  private static final int FIRST_WORLD_ID = 1;

  public PlayerDataSource(PlayerMapper playerMapper) {
    this.playerMapper = playerMapper;
  }

  @Override
  public Player find() {
    Map<PlayerAnimationType, BufferedImage> animationMap = playerMapper.selectPlayerImageDto().stream()
        .collect(Collectors.toMap(PlayerImageDto::toPlayerAnimationType, PlayerImageDto::bufferedImage));
    PlayerAnimation playerAnimation = new PlayerAnimation(animationMap);

    PlayerDto playerDto = playerMapper.selectPlayerDto(FIRST_WORLD_ID);
    Location location = playerDto.getLocation();

    return new Player(location, playerAnimation);
  }

  @Override
  public PlayerAnimation findAnimation() {
    Map<PlayerAnimationType, BufferedImage> animationMap = playerMapper.selectPlayerImageDto().stream()
        .collect(Collectors.toMap(PlayerImageDto::toPlayerAnimationType, PlayerImageDto::bufferedImage));
    return new PlayerAnimation(animationMap);
  }
}
