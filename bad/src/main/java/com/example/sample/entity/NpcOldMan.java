package com.example.sample.entity;

import com.example.sample.GamePanel;

public class NpcOldMan extends Entity {

  public NpcOldMan(GamePanel panel) {
    super(panel);

    direction = "down";
    speed = 1;

    getImage();
    setDialog();
  }

  public void getImage() {
    up1 = setup("npc/oldman_up_1", panel.tileSize, panel.tileSize);
    up2 = setup("npc/oldman_up_2", panel.tileSize, panel.tileSize);
    down1 = setup("npc/oldman_down_1", panel.tileSize, panel.tileSize);
    down2 = setup("npc/oldman_down_2", panel.tileSize, panel.tileSize);
    left1 = setup("npc/oldman_left_1", panel.tileSize, panel.tileSize);
    left2 = setup("npc/oldman_left_2", panel.tileSize, panel.tileSize);
    right1 = setup("npc/oldman_right_1", panel.tileSize, panel.tileSize);
    right2 = setup("npc/oldman_right_2", panel.tileSize, panel.tileSize);
  }

  public void setDialog() {
    dialogs[0] = "北の湖には武器や防具がある。\n装備を忘れずに。";
    dialogs[1] = "西の館の中には宝箱がある。\n館の鍵を手に入れ、\n宝箱にたどり着いたらゲームクリア";
    dialogs[2] = "幸運を祈る!";
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
}
