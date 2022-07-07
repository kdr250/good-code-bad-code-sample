package com.example.sample.presentation.itemlist;

import com.example.sample.application.service.item.ItemDomainService;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.Item;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemListController {

  private final ApplicationContext applicationContext;
  private final ItemDomainService itemDomainService;

  private Player player;
  private ItemListView itemListView;

  private static final int ITEM_LIST_ROW_SIZE = 5;
  private int itemListIndex = 0;
  private int fpsCounter = 0;

  public void setUp() {
    WorldMapController worldMapController = applicationContext.getBean(WorldMapController.class);
    this.player = worldMapController.getPlayer();
    this.itemListView = new ItemListView(player);
    this.itemListIndex = 0;
    this.fpsCounter = 0;
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (player == null || itemListView == null) return;

    if (countUpFpsThenIsKeyInputAllowed()) {
      if (keyInputType == KeyInputType.DECIDE) {
        ItemListViewChoice choice = choice();
        if (choice == ItemListViewChoice.BACK) {
          gameMode.worldMap();
          return;
        }

        itemDomainService.consumedOrEquipped(selectingItem(), player, gameMode);
        return;
      }

      updateItemListIndex(keyInputType);
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || itemListView == null) return;

    itemListView.draw(g2, itemListIndex);
  }

  private boolean countUpFpsThenIsKeyInputAllowed() {
    fpsCounter++;
    if (fpsCounter > 5) {
      fpsCounter = 0;
      return true;
    }
    return false;
  }

  private ItemListViewChoice choice() {
    List<Item> items = player.getPlayerItems().items();
    if (itemListIndex > -1 && itemListIndex < items.size()) return ItemListViewChoice.USE_ITEM;
    return ItemListViewChoice.BACK;
  }

  private Item selectingItem() {
    List<Item> items = player.getPlayerItems().items();
    if (itemListIndex > -1 && itemListIndex < items.size()) return items.get(itemListIndex);

    throw new IllegalArgumentException();
  }

  private void updateItemListIndex(KeyInputType keyInputType) {
    List<Item> items = player.getPlayerItems().items();
    switch (keyInputType) {
      case UP:
        int tempItemListIndexUp = itemListIndex == -1 ? items.size() - 1 : itemListIndex - ITEM_LIST_ROW_SIZE;
        itemListIndex = tempItemListIndexUp >= 0 ? tempItemListIndexUp : -1;
        break;
      case DOWN:
        int tempItemListIndexDown = itemListIndex == -1 ? 0 : itemListIndex + ITEM_LIST_ROW_SIZE;
        itemListIndex = tempItemListIndexDown < items.size() ? tempItemListIndexDown : -1;
        break;
      case LEFT:
        int tempItemListIndexLeft = itemListIndex == -1 ? items.size() - 1 : itemListIndex - 1;
        itemListIndex = tempItemListIndexLeft >= 0 ? tempItemListIndexLeft : -1;
        break;
      case RIGHT:
        int tempItemListIndexRight = itemListIndex == -1 ? 0 : itemListIndex + 1;
        itemListIndex = tempItemListIndexRight < items.size() ? tempItemListIndexRight : -1;
        break;
    }
  }
}
