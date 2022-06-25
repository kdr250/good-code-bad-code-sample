package com.example.sample.application.repository;

import com.example.sample.domain.model.item.ItemImage;

import java.util.List;

public interface ItemRepository {

  // TODO: 要リファクタリング
  public List<ItemImage> find();
}
