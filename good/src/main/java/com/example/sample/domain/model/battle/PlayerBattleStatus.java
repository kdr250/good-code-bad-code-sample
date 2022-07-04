package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.technique.PlayerTechniques;
import com.example.sample.domain.model.technique.Technique;
import com.example.sample.domain.model.technique.magic.Magic;
import com.example.sample.domain.model.technique.magic.MagicType;
import com.example.sample.domain.model.technique.physics.Physics;
import com.example.sample.domain.model.technique.physics.PhysicsType;
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

  public void damageHitPoint(final int damageAmount) {
    hitPoint = hitPoint.damage(damageAmount);
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

  public boolean canAttack(Technique technique) {
    if (technique instanceof MagicType) {
      Magic magicTechnique = ((MagicType)technique).getMagic();
      return magicPoint.canAttack(magicTechnique.costMagicPoint(level).current());
    }
    return true;
  }

  public void consumeCostForAttack(Technique technique) {
    if (technique instanceof MagicType) {
      Magic magicTechnique = ((MagicType)technique).getMagic();
      magicPoint.consume(magicTechnique.costMagicPoint(level).current());
    }
  }

  public int totalAttack(Technique technique) {
    int baseAttack = attackPower.getValue();
    int equipmentsAttack = equipments.totalAttack();

    if (technique instanceof PhysicsType) {
      Physics physicsTechnique = ((PhysicsType)technique).getPhysics();
      int techniqueAttack = physicsTechnique.attackPower(level).getValue();
      return baseAttack + equipmentsAttack + techniqueAttack;
    }
    if (technique instanceof MagicType) {
      Magic magicTechnique = ((MagicType)technique).getMagic();
      magicPoint.consume(magicTechnique.costMagicPoint(level).current());
      int techniqueAttack = magicTechnique.attackPower(level).getValue();
      return baseAttack + equipmentsAttack + techniqueAttack;
    }

    throw new IllegalArgumentException();
  }

  public int totalDefense() {
    return defensePower.getValue() + equipments.totalDefense();
  }

  public boolean isDead() {
    return hitPoint.isZero();
  }
}
