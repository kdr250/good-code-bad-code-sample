package com.example.sample.presentation.itemlist;

import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.worldmap.Tile;
import com.example.sample.domain.model.item.Item;
import com.example.sample.presentation.GamePanel;

import java.awt.*;
import java.util.List;

public class ItemListView {

  private final Player player;

  public ItemListView(final Player player) {
    this.player = player;
  }

  public void draw(Graphics2D g2, int itemListIndex) {
    drawPlayerBattleStatus(g2);
    drawPlayerItemList(g2, itemListIndex);
  }

  private void drawPlayerBattleStatus(Graphics2D g2) {
    // フレームを作成
    final int frameX = Tile.TILE_SIZE;
    final int frameY = Tile.TILE_SIZE;
    final int frameWidth = Tile.TILE_SIZE * 5;
    final int frameHeight = Tile.TILE_SIZE * 10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

    // テキスト
    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(32F));
    int textX = frameX + 20;
    int textY = frameY + Tile.TILE_SIZE;
    final int lineHeight = 35;

    // 項目名
    g2.drawString("Lv", textX, textY);
    textY += lineHeight;
    g2.drawString("HP", textX, textY);
    textY += lineHeight;
    g2.drawString("魔法力", textX, textY);
    textY += lineHeight;
    g2.drawString("攻撃力", textX, textY);
    textY += lineHeight;
    g2.drawString("防御力", textX, textY);
    textY += lineHeight;
    g2.drawString("経験値", textX, textY);
    textY += lineHeight;
    g2.drawString("次Lvまで", textX, textY);
    textY += lineHeight + 10;
    g2.drawString("武器", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("鎧", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("盾", textX, textY);

    // 値
    int tailX = (frameX + frameWidth) - 30;
    textY = frameY + Tile.TILE_SIZE;
    String value;
    value = String.valueOf(player.level().value());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.hitPoint().value());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = player.magicPoint().current() + "/" + player.magicPoint().max();
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.totalAttackPower());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.totalDefensePower());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.experience());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(player.experience().untilNextLevel());
    textX = getXForAlignToRightText(value, tailX, g2);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    if (!player.equipments().getWeapon().isEmpty()) {
      g2.drawImage(player.equipments().getWeapon().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
    textY += Tile.TILE_SIZE;
    if (!player.equipments().getArmor().isEmpty()) {
      g2.drawImage(player.equipments().getArmor().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
    textY += Tile.TILE_SIZE;
    if (!player.equipments().getArm().isEmpty()) {
      g2.drawImage(player.equipments().getArm().getImage(), tailX - Tile.TILE_SIZE, textY - 24, null);
    }
  }

  private void drawPlayerItemList(Graphics2D g2, int itemListIndex) {
    // フレーム
    int frameX = Tile.TILE_SIZE * 9;
    int frameY = Tile.TILE_SIZE;
    int frameWidth = Tile.TILE_SIZE * 6;
    int frameHeight = Tile.TILE_SIZE * 5;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

    // スロット
    final int slotXStart = frameX + 20;
    final int slotYStart = frameY + 20;
    int slotX = slotXStart;
    int slotY = slotYStart;
    int slotSize = Tile.TILE_SIZE + 3;

    // プレイヤーの所持アイテムを表示
    List<Item> items = player.playerItems().items();
    for (int i = 0; i < items.size(); i++) {

      g2.setColor(new Color(240, 190, 90));
      g2.fillRoundRect(slotX, slotY, Tile.TILE_SIZE, Tile.TILE_SIZE, 10, 10);

      g2.drawImage(items.get(i).getImage(), slotX, slotY, null);
      slotX += slotSize;

      if (i == itemListIndex) {
        int slotCol = itemListIndex % 5;
        int slotRow = itemListIndex / 5;
        // カーソル
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = Tile.TILE_SIZE;
        int cursorHeight = Tile.TILE_SIZE;

        // カーソル描画
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // アイテム説明
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = Tile.TILE_SIZE * 3;

        int textX = dFrameX + 20;
        int textY = dFrameY + Tile.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(28F));

        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight, g2);
        for (String line : items.get(itemListIndex).description().split("\n")) {
          g2.drawString(line, textX, textY);
          textY += 32;
        }
      }

      if (i != 0 && i % 5 == 4) {
        slotX = slotXStart;
        slotY += slotSize;
      }
    }

    // 戻る
    g2.setColor(Color.white);
    g2.drawString("もどる", GamePanel.screenWidth - Tile.TILE_SIZE * 5, GamePanel.screenHeight - Tile.TILE_SIZE);
    if (itemListIndex == -1) {
      g2.drawString(">", GamePanel.screenWidth - Tile.TILE_SIZE * 6, GamePanel.screenHeight - Tile.TILE_SIZE);
    }
  }

  private void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
    g2.setColor(new Color(0, 0, 0, 210));
    g2.fillRoundRect(x, y, width, height, 35, 35);
    g2.setColor(new Color(255, 255, 255));
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
  }

  private int getXForAlignToRightText(String text, int tailX, Graphics2D g2) {
    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    return tailX - length;
  }
}
