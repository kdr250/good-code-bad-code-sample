package com.example.sample.monsters;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;
import com.example.sample.objects.AttackPower;
import com.example.sample.objects.ItemKey;
import com.example.sample.objects.ItemPotionBlue;
import com.example.sample.objects.ItemPotionRed;

import java.util.List;

public class Enemy extends Entity {

  private Entity dropItem;

  /**
   * ×リスト10.19 敵を表現するクラス
   * 主人公のアイテムリストにアイテムを追加する
   * 追加できた場合はtrueを返す
   */
  @Override
  public boolean addItemToItemList(List<Entity> items) {
    if (items.size() < 20) {
      items.add(dropItem);
      return true;
    }
    return false;
  }

  public Enemy(GamePanel panel) {
    super(panel);

    type = typeMonster;
    name = "Green Slime";
    direction = "down";
    speed = 1;
    maxHitPoint = 10;
    hitPoint = maxHitPoint;
    attack = new AttackPower(5);
    defense = 0;
    exp = 2;

    solidArea.x = 3;
    solidArea.y = 18;
    solidArea.width = 42;
    solidArea.height = 30;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    setupImage();
    decideDropItem();
  }

  public void setupImage() {
    up1 = setup("monsters/greenslime_down_1", panel.tileSize, panel.tileSize);
    up2 = setup("monsters/greenslime_down_2", panel.tileSize, panel.tileSize);
    down1 = setup("monsters/greenslime_down_1", panel.tileSize, panel.tileSize);
    down2 = setup("monsters/greenslime_down_2", panel.tileSize, panel.tileSize);
    left1 = setup("monsters/greenslime_down_1", panel.tileSize, panel.tileSize);
    left2 = setup("monsters/greenslime_down_2", panel.tileSize, panel.tileSize);
    right1 = setup("monsters/greenslime_down_1", panel.tileSize, panel.tileSize);
    right2 = setup("monsters/greenslime_down_2", panel.tileSize, panel.tileSize);
  }

  @Override
  public void setAction() {
    actionLockCounter++;
    if (actionLockCounter == 120) {
      int i = (int)(Math.random() * 100F);
      if (i <= 25) {
        direction = "up";
      } else if (i <= 50) {
        direction = "down";
      } else if (i <= 75) {
        direction = "left";
      } else {
        direction = "right";
      }
      actionLockCounter = 0;
    }
  }

  private void decideDropItem() {
    int i = (int)(Math.random() * 100);
    if (i < 25) {
      dropItem = new ItemPotionRed(panel);
    } else if (i < 50) {
      dropItem = new ItemPotionBlue(panel);
    } else {
      dropItem = new ItemKey(panel);
    }
  }
}

