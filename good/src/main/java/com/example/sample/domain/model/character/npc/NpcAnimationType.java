package com.example.sample.domain.model.character.npc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NPCのアニメーション種類
 */
public enum NpcAnimationType {
  OLD_MAN_UP_ONE,
  OLD_MAN_UP_TWO,
  OLD_MAN_DOWN_ONE,
  OLD_MAN_DOWN_TWO,
  OLD_MAN_LEFT_ONE,
  OLD_MAN_LEFT_TWO,
  OLD_MAN_RIGHT_ONE,
  OLD_MAN_RIGHT_TWO;

  public static List<String> names() {
    return Arrays.stream(values()).map(NpcAnimationType::name).collect(Collectors.toList());
  }
}
