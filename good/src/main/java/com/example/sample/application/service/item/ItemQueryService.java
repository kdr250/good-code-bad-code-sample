package com.example.sample.application.service.item;

import com.example.sample.application.repository.item.ItemRepository;
import com.example.sample.domain.model.item.Items;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import org.springframework.stereotype.Service;

/**
 * アイテムの参照サービス
 */
@Service
public class ItemQueryService {

  private final ItemRepository itemRepository;

  public ItemQueryService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  /**
   * アイテムのコレクションをみつける
   */
  public Items find() {
    return itemRepository.find();
  }

  /**
   * アイテムをみつける
   */
  public Item find(ItemType itemType) {
    return itemRepository.find(itemType);
  }

  /**
   * アイテムのイメージをみつける
   */
  public ItemImage findImage(ItemType itemType) {
    return itemRepository.findImage(itemType);
  }
}
