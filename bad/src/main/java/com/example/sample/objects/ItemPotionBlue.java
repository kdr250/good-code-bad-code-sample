package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Entity;

import java.util.List;

public class ItemPotionBlue extends Entity {

  public ItemPotionBlue(GamePanel panel) {
    super(panel);

    type = typeConsumable;
    name = "青ポーション";
    recoveryAmount = 5;
    down1 = setup("objects/potion_blue", panel.tileSize, panel.tileSize);
    description = "[" + name + "]\n魔法力" + recoveryAmount + "回復";
  }

  @Override
  public void use(Entity player) {
    panel.gameState = panel.dialogState;
    panel.ui.currentDialog = name + "を飲んだ!\n魔法力" + recoveryAmount + "回復";

    player.magicPoint = recoverMagicPoint(player.magicPoint, player.maxMagicPoint, player.maxMagicPointIncrements(), recoveryAmount);
    panel.playSE(2);
  }

  /**
   * ×リスト5.23 引数の多いメソッド
   * 魔法力を回復する
   * @param currentMagicPoint 現在の魔法力残量
   * @param originalMaxMagicPoint　オリジナルの魔法力最大値
   * @param maxMagicPointIncrements 魔法力最大値の増分
   * @param recoveryAmount 回復後の魔法力残量
   * @return 回復後の魔法力残量
   */
  public int recoverMagicPoint(int currentMagicPoint, int originalMaxMagicPoint, List<Integer> maxMagicPointIncrements, int recoveryAmount) {
    int currentMaxMagicPoint = originalMaxMagicPoint;
    for (int each : maxMagicPointIncrements) {
      currentMaxMagicPoint += each;
    }

    return Math.min(currentMagicPoint + recoveryAmount, currentMaxMagicPoint);
  }
}
