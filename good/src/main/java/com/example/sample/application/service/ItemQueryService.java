package com.example.sample.application.service;

import com.example.sample.application.repository.ItemRepository;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemQueryService {

  private final ItemRepository itemRepository;

  public List<Item> find() {
    return itemRepository.find();
  }

  public ItemImage findImage(ItemType itemType) {
    return itemRepository.findImage(itemType);
  }
}
