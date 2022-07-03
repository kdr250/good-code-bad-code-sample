package com.example.sample.presentation.clear;

import com.example.sample.domain.model.Tile;
import com.example.sample.presentation.GamePanel;
import com.example.sample.presentation.KeyInputType;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class GameClearController {

  private final Font arial30 = new Font("Arial", Font.PLAIN, 30);
  private final Font arialBold80 = new Font("Arial", Font.BOLD, 80);

  public void update(KeyInputType keyInputType) {
    if (keyInputType == KeyInputType.DECIDE) {
      System.exit(0);
    }
  }

  public void draw(Graphics2D g2) {
    g2.setFont(arial30);
    g2.setColor(Color.white);

    String text = "宝物を見つけた!";
    int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = GamePanel.screenWidth / 2 - textLength / 2;
    int y = GamePanel.screenHeight / 2 - Tile.TILE_SIZE * 3;
    g2.drawString(text, x, y);

    g2.setFont(arialBold80);
    g2.setColor(Color.YELLOW);
    String text3 = "ゲームクリア!";
    int textLength3 = (int)g2.getFontMetrics().getStringBounds(text3, g2).getWidth();
    int x3 = GamePanel.screenWidth / 2 - textLength3 / 2;
    int y3 = GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 2;
    g2.drawString(text3, x3, y3);

    g2.setFont(arial30);
    g2.setColor(Color.white);
    String text4 = "> Press Enter to Quit Game";
    int textLength4 = (int)g2.getFontMetrics().getStringBounds(text4, g2).getWidth();
    int x4 = GamePanel.screenWidth / 2 - textLength4 / 2;
    int y4 = GamePanel.screenHeight / 2 + Tile.TILE_SIZE * 4;
    g2.drawString(text4, x4, y4);
  }
}
