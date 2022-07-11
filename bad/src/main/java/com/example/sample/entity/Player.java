package com.example.sample.entity;

import com.example.sample.GamePanel;
import com.example.sample.KeyManager;
import com.example.sample.objects.AttackPower;
import com.example.sample.objects.Equipment;
import com.example.sample.objects.ItemChest;
import com.example.sample.objects.ItemDoor;
import com.example.sample.objects.ItemKey;
import com.example.sample.objects.ItemShieldNormal;
import com.example.sample.objects.Level;
import com.example.sample.objects.MagicType;
import com.example.sample.objects.PhysicalAttackType;
import com.example.sample.objects.BattleTechnique;
import com.example.sample.objects.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player extends Entity {

  KeyManager keyManager;

  public Entity currentWeapon;

  /**
   * ×リスト9.6 装備防具と防御力を表現するロジックの一部
   */
  public Equipment body;
  public Equipment arm;

  public final int screenX;
  public final int screenY;

  boolean moving;
  int pixelCounter = 0;

  public List<Entity> inventory = new ArrayList<>();
  public final int maxInventorySize = 20;

  public BattleTechnique[] techniques = {PhysicalAttackType.PUNCH, MagicType.FIRE, MagicType.SHIDEN, MagicType.HELL_FIRE};

  public Player(GamePanel panel, KeyManager keyManager) {
    super(panel);

    this.keyManager = keyManager;

    screenX = panel.screenWidth / 2 - (panel.tileSize / 2);
    screenY = panel.screenHeight / 2 - (panel.tileSize / 2);

    solidArea = new Rectangle(1, 1, 46, 46);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    setDefaultValues();
    getPlayerImage();
    setItems();
  }

  public void setDefaultValues() {
    location.worldX = panel.tileSize * 23;
    location.worldY = panel.tileSize * 21;
    speed = 4;
    direction = "down";

    // プレイヤーのステータス
    level = Level.initialize();
    maxHitPoint = 6;
    hitPoint = maxHitPoint;
    maxMagicPoint = 4;
    magicPoint = maxMagicPoint;
    exp = 1;
    nextLevelExp = 5;
    currentWeapon = new Weapon(new AttackPower(3), panel);
    arm = new ItemShieldNormal(panel);
    attack = new AttackPower(1);
    defense = 1;
  }

  public void setDefaultPositions() {
    location.worldX = panel.tileSize * 23;
    location.worldY = panel.tileSize * 21;
    direction = "down";
  }

  public void restoreLifeAndMana() {
    hitPoint = maxHitPoint;
    magicPoint = maxMagicPoint;
  }

  public void setItems() {
    inventory.clear();
    inventory.add(currentWeapon);
    inventory.add((Entity) arm);
  }

  public int getAttack() {
    return attack.value() * currentWeapon.attackPower().value();
  }

  /**
   * ×リスト9.7 装備していない状態をnullで表現している
   * すべての防具を外す
   */
  public void takeOffAllEquipments() {
    body = null;
    arm = null;
  }

  /**
   * ×リスト9.8 null前提だとnullチェックしなければならない
   */
  public int totalDefense() {
    int total = defense;

    if (body != null) {
      total += body.defence();
    }
    if (arm != null) {
      total += arm.defence();
    }

    return total;
  }

  public void deleteEquipmentFromItemList() {
    if (body != null) {
      inventory.removeIf(i -> (i == body));
    }
    if (arm != null) {
      inventory.removeIf(i -> (i == arm));
    }
  }

  public void getPlayerImage() {
    up1 = setup("player/boy_up_1", panel.tileSize, panel.tileSize);
    up2 = setup("player/boy_up_2", panel.tileSize, panel.tileSize);
    down1 = setup("player/boy_down_1", panel.tileSize, panel.tileSize);
    down2 = setup("player/boy_down_2", panel.tileSize, panel.tileSize);
    left1 = setup("player/boy_left_1", panel.tileSize, panel.tileSize);
    left2 = setup("player/boy_left_2", panel.tileSize, panel.tileSize);
    right1 = setup("player/boy_right_1", panel.tileSize, panel.tileSize);
    right2 = setup("player/boy_right_2", panel.tileSize, panel.tileSize);
  }

  @Override
  public void draw(Graphics2D g2) {
    BufferedImage image;
    switch (direction) {
      case "up":
        image = spriteNum == 1 ? up1 : up2;
        break;
      case "down":
        image = spriteNum == 1 ? down1 : down2;
        break;
      case "left":
        image = spriteNum == 1 ? left1 : left2;
        break;
      case "right":
        image = spriteNum == 1 ? right1 : right2;
        break;
      default:
        throw new IllegalStateException();
    };
    g2.drawImage(image, screenX, screenY, null);
  }

  @Override
  public void update() {
    if (!keyManager.upPressed && !keyManager.downPressed && !keyManager.leftPressed && !keyManager.rightPressed && !keyManager.enterPressed && !keyManager.shotKeyPressed) {
      return;
    }

    if (moving) {
      pixelCounter += speed;
      if (pixelCounter == 48) {
        moving = false;
        pixelCounter = 0;
      }

      spriteCounter++;
      if (spriteCounter > 12) {
        spriteNum = (spriteNum + 1) % 2;
        spriteCounter = 0;
      }
    }
  }

  public void pickUpObject(int index) {
    if (index == 999) return;

    if (panel.objects[index] instanceof ItemDoor) {
      Optional<Entity> key = inventory.stream().filter(ItemKey.class::isInstance).findFirst();
      if (key.isPresent()) {
        inventory.remove(key.get());
        panel.objects[index] = null;
      }
    } else if (panel.objects[index] instanceof ItemChest) {
      panel.ui.isGameFinished = true;
      panel.playSE(1);
    } else {
      String text;
      if (inventory.size() != maxInventorySize) {
        inventory.add(panel.objects[index]);
        panel.playSE(0);
        text = panel.objects[index].name + "を手に入れた!";
      } else {
        text = "これ以上入らない!";
      }
      panel.ui.addMessage(text);
      panel.objects[index] = null;
    }
  }

  public void interactNpc(int index) {
    if (panel.keyManager.enterPressed) {
      if (index != 999) {
        panel.npc[index].speak();
        panel.gameState = panel.dialogState;
      }
    }
  }

  public void contactMonster(int index) {
    if (index != 999) {
      panel.gameState = panel.battleState;
      panel.battleMonster = panel.monsters[index];
      panel.ui.commandNum = 0;
    }
  }

  public void checkLevelUp() {
    if (exp >= nextLevelExp) {
      level = level.increase();
      nextLevelExp = nextLevelExp * 2;
      maxHitPoint += 2;
      attack.reinForce(1);
      maxMagicPoint += 2;
      magicPoint++;

      panel.playSE(1);
      panel.gameState = panel.dialogState;
      panel.ui.currentDialog = "レベルが " + level.value() + " に上がった!";
    }
  }

  public void selectItem() {
    int itemIndex = panel.ui.getItemIndexOnSlot();
    if (itemIndex < inventory.size()) {
      Entity selectedItem = inventory.get(itemIndex);
      if (selectedItem.type == typeSword || selectedItem.type == typeMagicalSword) {
        currentWeapon = selectedItem;
      } else if (selectedItem instanceof Equipment && selectedItem.type == typeShield) {
        arm = (Equipment)selectedItem;
      } else if (selectedItem instanceof Equipment && selectedItem.type == typeBodyArmor) {
        body = (Equipment)selectedItem;
      } else if (selectedItem.type == typeConsumable) {
        selectedItem.use(this);
        inventory.remove(itemIndex);
      }
    }
  }

  public boolean canMove() {
    if (!keyManager.upPressed && !keyManager.downPressed && !keyManager.leftPressed && !keyManager.rightPressed && !keyManager.enterPressed && !keyManager.shotKeyPressed) {
      return false;
    }

    if (!moving) {
      if (keyManager.upPressed) {
        direction = "up";
      }
      else if (keyManager.downPressed) {
        direction = "down";
      }
      else if (keyManager.leftPressed) {
        direction = "left";
      }
      else if (keyManager.rightPressed) {
        direction = "right";
      }

      moving = true;

      // コリジョンチェック
      collisionOn = false;
      panel.checker.checkTile(this);

      // アイテムとのコリジョン
      int objIndex = panel.checker.checkObject(this, true);
      pickUpObject(objIndex);

      // NPCとのコリジョン
      int npcIndex = panel.checker.checkEntity(this, panel.npc);
      interactNpc(npcIndex);

      // モンスターとのコリジョン
      int monsterIndex = panel.checker.checkEntity(this, panel.monsters);
      contactMonster(monsterIndex);

      panel.keyManager.enterPressed = false;
    }

    return moving && !collisionOn;
  }

  @Override
  public List<Integer> maxMagicPointIncrements() {
    List<Integer> result = new ArrayList<>(currentWeapon.maxMagicPointIncrements());
    if (body != null) {
      result.addAll(body.maxMagicPointIncrements());
    }
    if (arm != null) {
      result.addAll(arm.maxMagicPointIncrements());
    }
    return result;
  }

  public int maxMagicPoint() {
    int tmp = maxMagicPoint;
    for (int each : maxMagicPointIncrements()) {
      tmp += each;
    }
    return tmp;
  }
}
