package com.example.sample;

import com.example.sample.objects.PhysicalAttackType;

public class PhysicalAttackManager {

  public String getName(PhysicalAttackType physicalAttackType) {
    String name = "";

    switch (physicalAttackType) {
      case PUNCH:
        name = "正拳突き";
        break;
      case KICK:
        name = "中段蹴り";
        break;
    }

    return name;
  }

  public String description(PhysicalAttackType physicalAttackType) {
    String description = "";

    switch (physicalAttackType) {
      case PUNCH:
        description = "突きをくりだす コストなし";
        break;
      case KICK:
        description = "蹴りをくりだす コストなし";
        break;
    }

    return description;
  }
}
