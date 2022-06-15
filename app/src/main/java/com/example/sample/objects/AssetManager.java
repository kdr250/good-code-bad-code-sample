package com.example.sample.objects;

import com.example.sample.GamePanel;
import com.example.sample.entity.Location;
import com.example.sample.entity.NpcOldMan;
import com.example.sample.monsters.Enemy;

public class AssetManager {

  GamePanel panel;

  public AssetManager(GamePanel panel) {
    this.panel = panel;
  }

  public void setObjects() {
    /**
     * ×リスト4.8 AttackPowerインスタンスを使い回している
     * ×リスト4.9 使い回している攻撃力を変更すると...?
     * ×リスト4.10 別の武器の攻撃力まで変化してしまう
     */
    AttackPower attackPower = new AttackPower(3);

    Weapon weaponA = new Weapon(attackPower, panel);
    Weapon weaponB = new Weapon(attackPower, panel);

    weaponA.attackPower.value = 4;

    System.out.println("Weapon A attack power : " + weaponA.attackPower.value); // 4
    System.out.println("Weapon B attack power : " + weaponA.attackPower.value); // こちらも4になってしまう...

    /**
     * ×リスト1.17 生焼けオブジェクト の例
     */
    Location locationA = new Location();
    locationA.worldX = panel.tileSize * 20;
    locationA.worldY = panel.tileSize * 7;
    weaponA.location = locationA;

    Location locationB = new Location();
    locationB.worldX = panel.tileSize * 21;
    locationB.worldY = panel.tileSize * 7;
    weaponB.location = locationB;

    int i = 0;
    panel.objects[i] = weaponA;
    i++;
    panel.objects[i] = weaponB;
    i++;
    ItemPotionRed potionRed = new ItemPotionRed(panel);
    potionRed.location.worldX = panel.tileSize * 22;
    potionRed.location.worldY = panel.tileSize * 7;
    panel.objects[i] = potionRed;
    i++;
    ItemShieldBlue shieldBlue = new ItemShieldBlue(panel);
    shieldBlue.location.worldX = panel.tileSize * 23;
    shieldBlue.location.worldY = panel.tileSize * 7;
    panel.objects[i] = shieldBlue;
    i++;
    MagicalWeapon magicalWeapon = new MagicalWeapon(new AttackPower(3), panel);
    magicalWeapon.location.worldX = panel.tileSize * 24;
    magicalWeapon.location.worldY = panel.tileSize * 7;
    panel.objects[i] = magicalWeapon;
    i++;
    ItemPotionBlue potionBlue = new ItemPotionBlue(panel);
    potionBlue.location.worldX = panel.tileSize * 25;
    potionBlue.location.worldY = panel.tileSize * 7;
    panel.objects[i] = potionBlue;
    i++;
    ItemBodyArmor bodyArmor = new ItemBodyArmor(panel);
    bodyArmor.location.worldX = panel.tileSize * 26;
    bodyArmor.location.worldY = panel.tileSize * 7;
    panel.objects[i] = bodyArmor;
    i++;
    ItemDoor door = new ItemDoor(panel);
    door.location.worldX = 10 * panel.tileSize;
    door.location.worldY = 11 * panel.tileSize;
    panel.objects[i] = door;
    i++;
    ItemChest chest = new ItemChest(panel);
    chest.location.worldX = 10 * panel.tileSize;
    chest.location.worldY = 7 * panel.tileSize;
    panel.objects[i] = chest;
  }

  public void setNpc() {
    panel.npc[0] = new NpcOldMan(panel);
    panel.npc[0].location.worldX = panel.tileSize * 21;
    panel.npc[0].location.worldY = panel.tileSize * 21;
  }

  public void setMonsters() {
    int i = 0;

    panel.monsters[i] = new Enemy(panel);
    panel.monsters[i].location.worldX = panel.tileSize * 14;
    panel.monsters[i].location.worldY = panel.tileSize * 21;
    i++;
    panel.monsters[i] = new Enemy(panel);
    panel.monsters[i].location.worldX = panel.tileSize * 9;
    panel.monsters[i].location.worldY = panel.tileSize * 30;
    i++;
    panel.monsters[i] = new Enemy(panel);
    panel.monsters[i].location.worldX = panel.tileSize * 12;
    panel.monsters[i].location.worldY = panel.tileSize * 33;
    i++;
    panel.monsters[i] = new Enemy(panel);
    panel.monsters[i].location.worldX = panel.tileSize * 8;
    panel.monsters[i].location.worldY = panel.tileSize * 20;
  }
}

