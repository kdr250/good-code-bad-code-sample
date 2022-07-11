/*
 * ○リスト6.37 魔法interfaceの値オブジェクト導入版
 * を参考にしたインターフェース
 */
package com.example.sample.domain.model.battle.technique.magic;

import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.Level;
import com.example.sample.domain.model.battle.MagicPoint;

/**
 * 魔法攻撃
 */
public interface Magic {
  String name();
  MagicPoint costMagicPoint(Level level);
  AttackPower attackPower(Level level);
  String description();
}
