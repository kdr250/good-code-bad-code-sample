package com.example.sample.domain.model;

import com.example.sample.domain.model.item.Item;

import java.util.ArrayList;
import java.util.List;

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
}
