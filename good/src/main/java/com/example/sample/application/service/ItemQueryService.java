package com.example.sample.application.service;

import com.example.sample.application.repository.ItemRepository;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemQueryService {

  private final ItemRepository itemRepository;

  // TODO: 要リファクタリング
  public ItemImage find(ItemType itemType) {
    return itemRepository.find().stream()
      .filter(i -> i.getItemType() == itemType)
      .findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
