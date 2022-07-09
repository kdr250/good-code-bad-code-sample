package com.example.sample.domain.model.battle;

import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.EquipmentType;
import com.example.sample.domain.model.battle.technique.PlayerTechniques;
import com.example.sample.domain.model.battle.technique.Technique;
import com.example.sample.domain.model.battle.technique.magic.Magic;
import com.example.sample.domain.model.battle.technique.magic.MagicType;

import java.util.List;

/**
 * プレイヤーの戦闘ステータス
 */
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
    DefensePower defensePower = DefensePower.NONE;
    MagicPoint magicPoint = new MagicPoint(4);
    Level level = Level.initialize();
    Experience experience = new Experience(1, 5);
    Equipments equipments = new Equipments();
    PlayerTechniques playerTechniques = PlayerTechniques.initialize();
    return new PlayerBattleStatus(hItPoint, attackPower, defensePower, magicPoint, level, experience, equipments, playerTechniques);
  }

  public HitPoint hitPoint() {
    return hitPoint;
  }

  public void recoveryHitPoint(final HitPoint recoveryAmount) {
    hitPoint = hitPoint.recover(recoveryAmount);
  }

  public void recoveryHitPointMax() {
    hitPoint = hitPoint.recoverMax();
  }

  public void damageHitPoint(final Damage damage) {
    Damage correctedDamage = damage.reduce(totalDefensePower());
    hitPoint = hitPoint.damage(correctedDamage);
  }

  public MagicPoint magicPoint() {
    return magicPoint;
  }

  public void recoveryMagicPoint(final MagicPoint recoveryAmount) {
    magicPoint.recover(recoveryAmount);
  }

  public void recoverMagicPointMax() {
    magicPoint.recoverOriginalMax();
  }

  public Equipments equipments() {
    return equipments;
  }

  public void equip(Equipment equipment) {
    magicPoint.addMaxIncrements(equipment.maxMagicPointIncrement());
    equipments.equip(equipment);
  }

  public boolean hasEquipment(EquipmentType equipmentType) {
    return equipments.has(equipmentType);
  }

  public List<Technique> techniques() {
    return playerTechniques.getList();
  }

  public boolean canAttack(Technique technique) {
    if (technique instanceof MagicType) {
      Magic magicTechnique = ((MagicType)technique).getMagic();
      return magicPoint.canAttack(magicTechnique.costMagicPoint(level));
    }
    return true;
  }

  public void consumeCostForAttack(Technique technique) {
    magicPoint.consume(technique.costMagicPoint(level));
  }

  public AttackPower totalAttackPower(Technique technique) {
    AttackPower equipmentsAttackPower = equipments.totalAttackPower();
    AttackPower techniqueAttackPower = technique.attackPower(level);
    return attackPower.reinForce(equipmentsAttackPower).reinForce(techniqueAttackPower);
  }

  public AttackPower totalAttackPower() {
    AttackPower equipmentsAttackPower = equipments.totalAttackPower();
    return attackPower.reinForce(equipmentsAttackPower);
  }

  public DefensePower totalDefensePower() {
    return defensePower.reinforce(equipments.totalDefensePower());
  }

  public boolean isDead() {
    return hitPoint.isZero();
  }

  public Equipment deactivateEquipment(EquipmentType equipmentType) {
    Equipment equipment = equipments.getEquipment(equipmentType);
    magicPoint.removeMaxIncrements(equipment.maxMagicPointIncrement());
    equipments.deactivate(equipmentType);
    return equipment;
  }

  public void deactivateAllEquipments() {
    equipments.deactivateAll();
  }

  public Level level() {
    return level;
  }

  public Experience experience() {
    return experience;
  }

  public boolean gainExperienceAndIsLevelUp(final Experience increment) {
    boolean needsLevelUp = experience.needsLevelUpIfGained(increment);
    experience = experience.gain(increment);
    if (needsLevelUp) level = level.increase();
    return needsLevelUp;
  }
}
