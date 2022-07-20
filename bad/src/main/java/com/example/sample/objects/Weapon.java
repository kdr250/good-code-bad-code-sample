package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * リスト4.7 武器を表現するクラス
 */
public class Weapon extends Entity {
  final AttackPower attackPower;

  public Weapon(AttackPower attackPower, GamePanel panel) {
    super(panel);
    this.attackPower = attackPower;

    type = typeSword;
    name = "剣";
    down1 = setup("objects/sword_normal", panel.tileSize, panel.tileSize);
    description = "[" + name + "]\n普通の剣";
  }

  @Override
  public AttackPower attackPower() {
    return attackPower;
  }

  @Override
  public void draw(Graphics2D g2) {
    int screenX = location.worldX - panel.player.location.worldX + panel.player.screenX;
    int screenY = location.worldY - panel.player.location.worldY + panel.player.screenY;
    BufferedImage image = overwriteNumber(down1, attackPower.value);
    g2.drawImage(image, screenX, screenY, null);
  }

  private static final Font arial20 = new Font("Arial", Font.PLAIN, 20);

  private BufferedImage overwriteNumber(BufferedImage original, int number) {
    BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
    image.setData(original.getData());
    Graphics2D g2 = image.createGraphics();
    g2.setColor(Color.white);
    g2.setFont(arial20);
    g2.drawString(String.valueOf(number), panel.tileSize - 15, panel.tileSize - 10);
    return image;
  }
}
