package com.example.sample.application.service.item;

import com.example.sample.application.repository.item.ItemRepository;
import com.example.sample.domain.model.item.Items;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemQueryService {

  private final ItemRepository itemRepository;

  public Items find() {
    return itemRepository.find();
  }

  public Item find(ItemType itemType) {
    return itemRepository.find(itemType);
  }

  public ItemImage findImage(ItemType itemType) {
    return itemRepository.findImage(itemType);
  }
}
