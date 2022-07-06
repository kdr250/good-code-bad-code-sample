package com.example.sample.presentation.worldmap;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.item.ItemImage;

import java.awt.*;

public class PlayerStatusView {
  private final Player player;
  private final ItemImage crystalBlank;
  private final ItemImage crystalFull;

  public PlayerStatusView(final Player player, final ItemImage crystalBlank, final ItemImage crystalFull) {
    this.player = player;
    this.crystalBlank = crystalBlank;
    this.crystalFull = crystalFull;
  }

  public void draw(Graphics2D g2) {
    int x = Tile.TILE_SIZE / 2;
    int y = Tile.TILE_SIZE / 2;
    // 現在のライフを表示する
    int playerLifeBarX = x + 15;
    int playerLifeBarMaxWidth = 120;
    g2.setColor(Color.black);
    g2.fillRect(playerLifeBarX, y, playerLifeBarMaxWidth, 20);
    double playerLifeBarWidth = playerLifeBarMaxWidth * ((double) player.getPlayerBattleStatus().getHitPoint().getValue() / (double) player.getPlayerBattleStatus().getHitPoint().getMaxValue());
    g2.setColor(Color.red);
    g2.fillRect(playerLifeBarX, y, (int)playerLifeBarWidth, 20);
    // 魔法力最大値を表示する
    x = Tile.TILE_SIZE / 2 - 5;
    y = (int)(Tile.TILE_SIZE * 1.5);
    for (int i = 0; i < player.getPlayerBattleStatus().getMagicPoint().max(); i++) {
      g2.drawImage(crystalBlank.getBufferedImage(), x, y, null);
      x += 35;
    }

    // 現在の魔法力を表示する
    x = Tile.TILE_SIZE / 2 - 5;
    y = (int)(Tile.TILE_SIZE * 1.5);
    for (int i = 0; i < player.getPlayerBattleStatus().getMagicPoint().current(); i++) {
      g2.drawImage(crystalFull.getBufferedImage(), x, y, null);
      x += 35;
    }
  }

  public ItemImage getCrystalBlank() {
    return crystalBlank;
  }

  public ItemImage getCrystalFull() {
    return crystalFull;
  }
}
