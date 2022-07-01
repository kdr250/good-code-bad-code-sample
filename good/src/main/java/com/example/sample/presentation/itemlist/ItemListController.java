package com.example.sample.presentation.itemlist;

import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.Consumable;
import com.example.sample.domain.model.item.Equipment;
import com.example.sample.domain.model.item.Item;
import com.example.sample.presentation.KeyInputType;
import com.example.sample.presentation.worldmap.WorldMapController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;

@RequiredArgsConstructor
@Component
public class ItemListController {

  private final ApplicationContext applicationContext;

  private Player player;
  private ItemListView itemListView;

  public void setUp() {
    WorldMapController worldMapController = (WorldMapController) applicationContext.getBean("worldMapController");
    this.player = worldMapController.getPlayer();
    this.itemListView = new ItemListView(player);
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    if (player == null || itemListView == null) return;

    // TODO: itemListViewに選択ロジックを持たせないよう修正すること。Viewは表示するだけにしたい
    if (itemListView.countUpFpsThenIsKeyInputAllowed()) {
      if (keyInputType == KeyInputType.DECIDE) {
        ItemListViewChoice choice = itemListView.choice();
        if (choice == ItemListViewChoice.BACK) {
          gameMode.worldMap();
          return;
        }
        Item item = itemListView.selectingItem();
        if (item instanceof Consumable) {
          com.example.sample.domain.model.event.Event event = ((Consumable) item).consume();
          if (event instanceof PlayerEvent) {
            ((PlayerEvent) event).execute(player);
          }
        }
        if (item instanceof Equipment) {
          Event event = ((Equipment) item).equip();
          if (event instanceof PlayerEvent) {
            ((PlayerEvent) event).execute(player);
          }
        }
      } else {
        itemListView.moveCursor(keyInputType);
      }
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null || itemListView == null) return;

    itemListView.draw(g2);
  }
}
