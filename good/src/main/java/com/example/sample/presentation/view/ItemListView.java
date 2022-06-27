package com.example.sample.presentation.view;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.item.ItemWeapon;
import com.example.sample.presentation.GamePanel;

import java.awt.*;

public class ItemListView {

  private final Player player;

  public ItemListView(final Player player) {
    this.player = player;
  }

  public void draw(Graphics2D g2) {
    // フレームを作成
    final int frameX = Tile.TILE_SIZE;
    final int frameY = Tile.TILE_SIZE;
    final int frameWidth = Tile.TILE_SIZE * 5;
    final int frameHeight =  Tile.TILE_SIZE * 10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

    // テキスト
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(32F));
    int textX = frameX + 20;
    int textY = frameY + Tile.TILE_SIZE;
    final int lineHeight = 35;

    // 項目名
    g2.drawString("Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Life", textX, textY);
    textY += lineHeight;
    g2.drawString("Mana", textX, textY);
    textY += lineHeight;
    g2.drawString("Attack", textX, textY);
    textY += lineHeight;
    g2.drawString("Defense", textX, textY);
    textY += lineHeight;
    g2.drawString("Exp", textX, textY);
    textY += lineHeight;
    g2.drawString("Next Level", textX, textY);
    textY += lineHeight + 10;
    g2.drawString("Weapon", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Body", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Arm", textX, textY);

    // 値
    int tailX = (frameX + frameWidth) - 30;
    textY = frameY + Tile.TILE_SIZE;
    String value;
    value = String.valueOf(player.getPlayerBattleStatus().getLevel().getValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.getPlayerBattleStatus().getHItPoint().getValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = player.getPlayerBattleStatus().getMagicPoint().current() + "/" + player.getPlayerBattleStatus().getMagicPoint().max();
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.getPlayerBattleStatus().getAttackPower().getValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    // TODO: トータルの防御力に修正すること
    value = String.valueOf(player.getPlayerBattleStatus().getDefensePower().getValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.getPlayerBattleStatus().getExperience().getValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.getPlayerBattleStatus().getExperience().getNextLevelValue());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    // TODO: EMPTYの修正すること
    if (player.getPlayerBattleStatus().getEquipments().getHead() != ItemWeapon.EMPTY) {
      g2.drawImage(player.getPlayerBattleStatus().getEquipments().getHead().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
    textY += Tile.TILE_SIZE;
    if (player.getPlayerBattleStatus().getEquipments().getArmor() != ItemWeapon.EMPTY) {
      g2.drawImage(player.getPlayerBattleStatus().getEquipments().getArmor().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
    textY += Tile.TILE_SIZE;
    if (player.getPlayerBattleStatus().getEquipments().getArm() != ItemWeapon.EMPTY) {
      g2.drawImage(player.getPlayerBattleStatus().getEquipments().getArm().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
  }

  private void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
    g2.setColor(new Color(0, 0, 0, 210));
    g2.fillRoundRect(x, y, width, height, 35, 35);
    g2.setColor(new Color(255, 255, 255));
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
  }

  private int getXForCenteredText(String text, Graphics2D g2) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return GamePanel.screenWidth / 2 - length / 2;
  }

  private int getXForAlignToRightText(String text, int tailX, Graphics2D g2) {
    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return tailX - length;
  }
}
