package com.example.sample.domain.model.battle;

import lombok.Getter;

@Getter
public class PlayerBattleStatus {
  private HItPoint hItPoint;
  private AttackPower attackPower;
  private DefensePower defensePower;
  private MagicPoint magicPoint;
  private Level level;
  private Experience experience;
  private Equipments equipments;

  private PlayerBattleStatus(HItPoint hItPoint, AttackPower attackPower, DefensePower defensePower, MagicPoint magicPoint, Level level, Experience experience, Equipments equipments) {
    this.hItPoint = hItPoint;
    this.attackPower = attackPower;
    this.defensePower = defensePower;
    this.magicPoint = magicPoint;
    this.level = level;
    this.experience = experience;
    this.equipments = equipments;
  }

  public static PlayerBattleStatus initialize() {
    HItPoint hItPoint = new HItPoint(6);
    AttackPower attackPower = new AttackPower(1);
    DefensePower defensePower = new DefensePower(1);
    MagicPoint magicPoint = new MagicPoint(4, 4);
    Level level = Level.initialize();
    Experience experience = new Experience(1, 5);
    Equipments equipments = new Equipments();
    return new PlayerBattleStatus(hItPoint, attackPower, defensePower, magicPoint, level, experience, equipments);
  }
}
