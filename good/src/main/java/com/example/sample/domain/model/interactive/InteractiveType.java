package com.example.sample.domain.model.interactive;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 相互作用トリガーの種類
 */
public enum InteractiveType {
  CHEST,
  DOOR;

  public static List<String> names() {
    return Arrays.stream(values()).map(InteractiveType::name).collect(Collectors.toList());
  }
}
