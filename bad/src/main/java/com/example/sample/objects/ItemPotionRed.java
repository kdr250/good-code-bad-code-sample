package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

public class ItemPotionRed extends Entity {

  public ItemPotionRed(GamePanel panel) {
    super(panel);

    type = typeConsumable;
    name = "赤ポーション";
    recoveryAmount = 5;
    down1 = setup("objects/potion_red", panel.tileSize, panel.tileSize);
    description = "[" + name + "]\n体力" + recoveryAmount + "回復";
  }

  @Override
  public void use(Entity player) {
    panel.gameState = panel.dialogState;
    panel.ui.currentDialog = name + "を飲んだ!\n体力" + recoveryAmount + "回復";
    /** ×リスト2.8 どこかに書かれるヒットポイント回復ロジック */
    player.hitPoint = player.hitPoint + recoveryAmount;
    if (player.hitPoint > player.maxHitPoint) {
      player.hitPoint = player.maxHitPoint;
    }
    panel.playSE(2);
  }
}
