package com.example.sample.infrastructure.datasource.item;

import com.example.sample.application.repository.ItemRepository;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemDataSource implements ItemRepository {

  private final ItemMapper itemMapper;

  private static final int FIRST_WORLD_ID = 1;

  @Override
  public List<Item> find() {
    Map<ItemType, BufferedImage> imageMap = itemMapper.selectItemImageDtoList(ItemType.names()).stream()
        .collect(Collectors.toMap(ItemImageDto::itemType, ItemImageDto::bufferedImage));

    return itemMapper.selectItemDtoList(FIRST_WORLD_ID).stream()
        .map(itemDto -> itemDto.toItem(imageMap))
        .collect(Collectors.toList());
  }

  @Override
  public Item find(ItemType itemType) {
    ItemImageDto itemImageDto = itemMapper.selectItemImageDto(itemType.name());
    return itemImageDto.toItem();
  }

  @Override
  public ItemImage findImage(ItemType itemType) {
    return itemMapper.selectItemImageDtoList(ItemType.names()).stream()
      .map(ItemImageDto::toItemImage)
        .filter(i -> i.getItemType() == itemType)
        .findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
