package com.example.sample.presentation.title;

import com.example.sample.application.service.player.PlayerQueryService;
import com.example.sample.domain.model.character.player.PlayerAnimation;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class TitleController {

  private final ApplicationContext applicationContext;
  private final PlayerQueryService playerQueryService;

  private PlayerAnimation playerAnimation;
  private TitleView titleView;

  public TitleController(ApplicationContext applicationContext, PlayerQueryService playerQueryService) {
    this.applicationContext = applicationContext;
    this.playerQueryService = playerQueryService;
  }

  public void setUp() {
    playerAnimation = playerQueryService.findAnimation();
    titleView = new TitleView(playerAnimation);
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (keyInputType == KeyInputType.DECIDE) {
      WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
      worldMapController.setUp(gameMode);
      gameMode.changeToWorldMap();
    }
  }

  public void draw(Graphics2D g2) {
    if (titleView == null) return;

    titleView.draw(g2);
  }
}
