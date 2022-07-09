/*
 * ○リスト6.40 魔法「地獄の業火」の値オブジェクト導入版
 * を参考にしたクラス
 */
package com.example.sample.domain.model.battle.technique.magic;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

/**
 * 地獄の業火
 */
public class HellFire implements Magic {

  @Override
  public String name() {
    return "地獄の業火";
  }

  @Override
  public MagicPoint costMagicPoint(final Level level) {
    return new MagicPoint(6);
  }

  @Override
  public AttackPower attackPower(final Level level) {
    final int value = 5 + level.value() * 2;
    return new AttackPower(value);
  }

  @Override
  public String description() {
    return "強化炎攻撃 消費魔力6";
  }
}
