package com.example.sample.entity;

import com.example.sample.GamePanel;
import com.example.sample.UtilityTool;
import com.example.sample.objects.AttackPower;
import com.example.sample.objects.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

  protected GamePanel panel;
  public Location location = new Location();
  public int speed;

  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
  public String direction = "down";

  public int spriteCounter = 0;
  public int spriteNum = 1;

  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX, solidAreaDefaultY;
  public boolean collisionOn;
  public int actionLockCounter = 0;
  String[] dialogs = new String[20];
  int dialogIndex = 0;

  public BufferedImage image, image2, image3;
  public String name;
  public boolean collision;

  // キャラクターのステータス
  public int maxHitPoint;
  /** ×リスト2.6 単なる変数として用意されたヒットポイント */
  public int hitPoint;
  public int maxMagicPoint;
  public int magicPoint;

  public boolean alive = true;
  public boolean dying = false;

  public Level level;
  public AttackPower attack;
  public int defense;
  public int exp;
  public int nextLevelExp;

  // アイテムの属性
  public int defenseValue;
  public String description = "";
  public int recoveryAmount;
  public List<Integer> maxMagicPointIncrements = new ArrayList<>();

  // タイプ
  public int type;  // 0 = プレイヤー, 1 = NPC, 2 = 敵
  public final int typePlayer = 0;
  public final int typeNpc = 1;
  public final int typeMonster = 2;
  public final int typeSword = 3;
  public final int typeMagicalSword = 4;
  public final int typeShield = 5;
  public final int typeConsumable = 6;
  public final int typeBodyArmor = 7;

  public Entity(GamePanel panel) {
    this.panel = panel;
  }

  public void setAction() {}

  public void update() {
    setAction();

    collisionOn = false;
    panel.checker.checkTile(this);
    panel.checker.checkObject(this, false);
    panel.checker.checkEntity(this, panel.npc);
    panel.checker.checkEntity(this, panel.monsters);

    // コリジョンがオンでなければ動くことができる
    if (!collisionOn) {
      switch (direction) {
        case "up":
          location.worldY -= speed;
          break;
        case "down":
          location.worldY += speed;
          break;
        case "left":
          location.worldX -= speed;
          break;
        case "right":
          location.worldX += speed;
          break;
      }
    }
  }

  public BufferedImage setup(String imageName, int width, int height) {
    try {
      BufferedImage originalImage = ImageIO.read(Paths.get("src/main/resources/" + imageName + ".png").toFile());
      return UtilityTool.scaleImage(originalImage, width, height);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void draw(Graphics2D g2) {
    int screenX = location.worldX - panel.player.location.worldX + panel.player.screenX;
    int screenY = location.worldY - panel.player.location.worldY + panel.player.screenY;
    BufferedImage image;
    switch (direction) {
      case "up":
        image = up1;
        break;
      case "down":
        image = down1;
        break;
      case "left":
        image = left1;
        break;
      case "right":
        image = right1;
        break;
      default:
        throw new IllegalStateException();
    };
    g2.drawImage(image, screenX, screenY, null);
  }

  public void speak() {
    if (dialogs[dialogIndex] == null) {
      dialogIndex = 0;
    }
    panel.ui.currentDialog = dialogs[dialogIndex];
    dialogIndex++;

    switch (panel.player.direction) {
      case "up":
        direction = "down";
        break;
      case "down":
        direction = "up";
        break;
      case "left":
        direction = "right";
        break;
      case "right":
        direction = "left";
        break;
    }
  }

  public void use(Entity entity) {}

  public boolean addItemToItemList(List<Entity> items) {
    return false;
  }

  public AttackPower attackPower() {
    return null;
  }

  public List<Integer> maxMagicPointIncrements() {
    return maxMagicPointIncrements;
  }
}
