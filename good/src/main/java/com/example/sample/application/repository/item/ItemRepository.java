package com.example.sample.application.repository.item;

import com.example.sample.domain.model.item.Items;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;

/**
 * アイテムのレポジトリ
 */
public interface ItemRepository {
  Items find();

  Item find(ItemType itemType);

  ItemImage findImage(ItemType itemType);
}
