package com.example.sample.domain.model.character.enemy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EnemyAnimationType {
  SLIME_DOWN_ONE,
  SLIME_DOWN_TWO;

  public static List<String> names() {
    return Arrays.stream(values()).map(EnemyAnimationType::name).collect(Collectors.toList());
  }
}
