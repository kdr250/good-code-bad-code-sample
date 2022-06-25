package com.example.sample.infrastructure.datasource.item;

import com.example.sample.application.repository.ItemRepository;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemDataSource implements ItemRepository {

  private final ItemMapper itemMapper;

  // TODO: 要リファクタリング
  @Override
  public List<ItemImage> find() {
    return itemMapper.selectItemImageDto(ItemType.names()).stream()
      .map(ItemImageDto::toItemImage)
      .collect(Collectors.toList());
  }
}
