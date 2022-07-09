/*
 * ○リスト10.20 パーティーの所持品を表現するクラス
 * を参考にしたクラス
 */
package com.example.sample.domain.model.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * プレイヤーの所持品
 */
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

  public PlayerItems remove(Item item) {
    List<Item> newItems = items.stream().filter(i -> i != item).collect(Collectors.toList());
    return new PlayerItems(newItems);
  }

  public boolean hasKey() {
    return items.stream().anyMatch(Key.class::isInstance);
  }

  public PlayerItems removeKeyIfExists() {
    Optional<Item> key = items.stream().filter(Key.class::isInstance).findFirst();
    if (key.isEmpty()) return this;
    List<Item> deleting = new ArrayList<>(items);
    deleting.remove(key.get());
    return new PlayerItems(deleting);
  }
}
