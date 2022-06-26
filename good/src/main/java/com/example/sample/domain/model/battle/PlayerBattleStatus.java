package com.example.sample.domain.model.battle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerBattleStatus {
  private HItPoint hItPoint;
  private AttackPower attackPower;
  private DefensePower defensePower;
  private MagicPoint magicPoint;
  private Level level;
  private Experience experience;
  private Equipments equipments;
}
