/*
 * ○リスト6.39 魔法「紫電」の値オブジェクト導入版
 * を参考にしたクラス
 */
package com.example.sample.domain.model.battle.technique.magic;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

/**
 * 紫電
 */
public class Shiden implements Magic {

  @Override
  public String name() {
    return "紫電";
  }

  @Override
  public MagicPoint costMagicPoint(final Level level) {
    final int value = 3 + (int)(level.value() * 0.2);
    return new MagicPoint(value);
  }

  @Override
  public AttackPower attackPower(final Level level) {
    final int value = 4 + (int)(level.value() * 1.5);
    return new AttackPower(value);
  }

  @Override
  public String description() {
    return "雷攻撃 消費魔力3+レベル*0.2";
  }
}
