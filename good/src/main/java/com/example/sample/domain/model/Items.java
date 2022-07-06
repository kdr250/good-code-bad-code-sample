package com.example.sample.domain.model;

import com.example.sample.domain.model.item.Item;

import java.util.Collections;
import java.util.List;

public class Items {
  private final List<Item> items;

  public Items(final List<Item> items) {
    this.items = items;
  }

  public List<Item> items() {
    return Collections.unmodifiableList(items);
  }

  public void remove(Item item) {
    items.remove(item);
  }
}
