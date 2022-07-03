package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.technique.PlayerTechniques;
import lombok.Getter;

@Getter
public class PlayerBattleStatus {
  private HitPoint hitPoint;
  private AttackPower attackPower;
  private DefensePower defensePower;
  private MagicPoint magicPoint;
  private Level level;
  private Experience experience;
  private Equipments equipments;
  private PlayerTechniques playerTechniques;

  private PlayerBattleStatus(HitPoint hItPoint, AttackPower attackPower, DefensePower defensePower, MagicPoint magicPoint, Level level, Experience experience, Equipments equipments, PlayerTechniques playerTechniques) {
    this.hitPoint = hItPoint;
    this.attackPower = attackPower;
    this.defensePower = defensePower;
    this.magicPoint = magicPoint;
    this.level = level;
    this.experience = experience;
    this.equipments = equipments;
    this.playerTechniques = playerTechniques;
  }

  public static PlayerBattleStatus initialize() {
    HitPoint hItPoint = new HitPoint(6);
    AttackPower attackPower = new AttackPower(1);
    DefensePower defensePower = new DefensePower(1);
    MagicPoint magicPoint = new MagicPoint(4);
    Level level = Level.initialize();
    Experience experience = new Experience(1, 5);
    Equipments equipments = new Equipments();
    PlayerTechniques playerTechniques = PlayerTechniques.initialize();
    return new PlayerBattleStatus(hItPoint, attackPower, defensePower, magicPoint, level, experience, equipments, playerTechniques);
  }

  public void recoveryHitPoint(final int recoveryAmount) {
    hitPoint = hitPoint.recover(recoveryAmount);
  }

  public void recoveryMagicPoint(final int recoveryAmount) {
    magicPoint.recover(recoveryAmount);
  }

  public void equip(Equipment equipment) {
    magicPoint.addMaxIncrements(equipment.maxMagicPointIncrement());
    equipments.equip(equipment);
  }

  public boolean hasEquipment(EquipmentType equipmentType) {
    return equipments.has(equipmentType);
  }

  public Equipment getEquipment(EquipmentType equipmentType) {
    return equipments.getEquipment(equipmentType);
  }
}
