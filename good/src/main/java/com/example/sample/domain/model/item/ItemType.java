package com.example.sample.domain.model.item;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ItemType {
  KEY,
  CHEST,
  DOOR,
  WEAPON,
  MAGICAL_WEAPON,
  BODY_ARMOR,
  POTION_RED,
  POTION_BLUE,
  SHIELD_NORMAL,
  SHIELD_BLUE,
  CRYSTAL_BLANK,
  CRYSTAL_FULL;

  public static List<String> names() {
    return Arrays.stream(values()).map(ItemType::name).collect(Collectors.toList());
  }
}
