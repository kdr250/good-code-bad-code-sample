package com.example.sample.domain.model;

import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerItems {
  private static final int MAX_ITEM_COUNT = 20;
  private final List<Item> items;

  public PlayerItems() {
    items = new ArrayList<>();
  }

  private PlayerItems(final List<Item> items) {
    this.items = items;
  }

  public PlayerItems add(final Item newItem) {
    if (items.size() == MAX_ITEM_COUNT) {
      throw new RuntimeException("これ以上アイテムを持てません。");
    }

    List<Item> adding = new ArrayList<>(items);
    adding.add(newItem);
    return new PlayerItems(adding);
  }

  public List<Item> items() {
    return Collections.unmodifiableList(items);
  }

  public boolean hasKey() {
    return items.stream().anyMatch(item -> item instanceof Key);
  }

  public PlayerItems deleteKeyIfExists() {
    Optional<Item> key = items.stream().filter(item -> item instanceof Key).findFirst();
    if (key.isEmpty()) return this;
    List<Item> deleting = new ArrayList<>(items);
    deleting.remove(key.get());
    return new PlayerItems(deleting);
  }
}
