package com.example.sample.domain.model.item;

import java.util.Collections;
import java.util.List;

/**
 * アイテムのコレクション
 */
public class Items {
  private final List<Item> items;

  public Items(final List<Item> items) {
    this.items = items;
  }

  public List<Item> items() {
    return Collections.unmodifiableList(items);
  }

  public void remove(Item item) {
    items.remove(item); // TODO: 本当は不変にして、変更時は新しいインスタンスを返すようにしたい..
  }
}
