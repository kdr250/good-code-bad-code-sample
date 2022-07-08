package com.example.sample.domain.model.interactive;

import com.example.sample.domain.model.item.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum InteractiveType {
  CHEST,
  DOOR;

  public static List<String> names() {
    return Arrays.stream(values()).map(InteractiveType::name).collect(Collectors.toList());
  }
}
