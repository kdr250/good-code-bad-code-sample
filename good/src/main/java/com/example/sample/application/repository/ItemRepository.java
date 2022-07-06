package com.example.sample.application.repository;

import com.example.sample.domain.model.Items;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;

public interface ItemRepository {
  Items find();

  Item find(ItemType itemType);

  ItemImage findImage(ItemType itemType);
}
