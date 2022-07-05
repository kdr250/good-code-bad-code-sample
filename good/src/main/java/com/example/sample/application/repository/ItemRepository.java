package com.example.sample.application.repository;

import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;

import java.util.List;

public interface ItemRepository {
  List<Item> find();

  Item find(ItemType itemType);

  ItemImage findImage(ItemType itemType);
}
