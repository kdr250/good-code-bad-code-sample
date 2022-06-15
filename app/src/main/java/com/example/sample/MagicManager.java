package com.example.sample;

import com.example.sample.objects.MagicType;

public class MagicManager {

  /**
   * ×リスト6.11 switch文で表示名を切り替え
   */
  public String getName(MagicType magicType) {
    String name = "";

    switch (magicType) {
      case FIRE:
        name = "ファイア";
        break;
      case SHIDEN:
        name = "紫電";
        break;
      case HELL_FIRE:
        name = "地獄の業火";
        break;
    }

    return name;
  }

  /**
   * ×リスト6.15 costMagicPointメソッドにcase文追加
   */
  public int costMagicPoint(MagicType magicType) {
    int magicPoint = 0;

    switch (magicType) {
      case FIRE:
        magicPoint = 2;
        break;
      case SHIDEN:
        magicPoint = 3;
        break;
      case HELL_FIRE:
        magicPoint = 4;
        break;
    }

    return magicPoint;
  }

  public String description(MagicType magicType) {
    String description = "";

    switch (magicType) {
      case FIRE:
        description = "炎攻撃 消費魔力2";
        break;
      case SHIDEN:
        description = "雷攻撃 消費魔力3";
        break;
      case HELL_FIRE:
        description = "強化炎攻撃 消費魔力4";
        break;
    }

    return description;
  }
}

