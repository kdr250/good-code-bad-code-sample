package com.example.sample.presentation.title;

import com.example.sample.application.service.PlayerQueryService;
import com.example.sample.domain.model.PlayerAnimation;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.presentation.GamePanel;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;

@RequiredArgsConstructor
@Component
public class TitleController {

  private final ApplicationContext applicationContext;

  private final PlayerQueryService playerQueryService;

  private PlayerAnimation playerAnimation;

  public void start() {
    playerAnimation = playerQueryService.find();
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (keyInputType == KeyInputType.DECIDE) {
      WorldMapController worldMapController = (WorldMapController) applicationContext.getBean("worldMapController");
      worldMapController.start();
      gameMode.worldMap();
    }
  }

  public void draw(Graphics2D g2) {
    g2.setColor(new Color(0, 0, 0));
    g2.fillRect(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);

    // タイトル名
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
    String text = "Java Sample Adventure";
    int x = getXForCenteredText(g2, text);
    int y = Tile.TILE_SIZE * 3;

    // 文字に影を入れる
    g2.setColor(Color.gray);
    g2.drawString(text, x + 5, y + 5);

    // メインの色
    g2.setColor(Color.white);
    g2.drawString(text, x, y);

    // プレイヤーの画像
    int playerX = GamePanel.screenWidth / 2 - (Tile.TILE_SIZE * 2) / 2;
    int playerY = y + Tile.TILE_SIZE * 2;
    g2.drawImage(playerAnimation.getImage(), playerX, playerY, Tile.TILE_SIZE * 2, Tile.TILE_SIZE * 2, null);

    // メニュー
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

    String text2 = "> Press Enter !";
    int newGameX = getXForCenteredText(g2, text2);
    int newGameY = playerY + Tile.TILE_SIZE * 4;
    g2.drawString(text2, newGameX, newGameY);
  }

  public int getXForCenteredText(Graphics2D g2, String text) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return GamePanel.screenWidth / 2 - length / 2;
  }
}
