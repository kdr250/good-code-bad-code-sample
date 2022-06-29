package com.example.sample.presentation.worldmap;

import com.example.sample.application.service.EnemyQueryService;
import com.example.sample.application.service.ItemQueryService;
import com.example.sample.application.service.NpcQueryService;
import com.example.sample.application.service.PlayerQueryService;
import com.example.sample.application.service.WorldMapQueryService;
import com.example.sample.domain.model.Collidable;
import com.example.sample.domain.model.Enemy;
import com.example.sample.domain.model.EnemyAnimation;
import com.example.sample.domain.model.Location;
import com.example.sample.domain.model.Npc;
import com.example.sample.domain.model.NpcAnimation;
import com.example.sample.domain.model.Player;
import com.example.sample.domain.model.PlayerAnimation;
import com.example.sample.domain.model.Tile;
import com.example.sample.domain.model.Vector;
import com.example.sample.domain.model.WorldMap;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.DefensePower;
import com.example.sample.domain.model.battle.EnemyBattleStatus;
import com.example.sample.domain.model.battle.HitPoint;
import com.example.sample.domain.model.event.Event;
import com.example.sample.domain.model.event.GameModeEvent;
import com.example.sample.domain.model.event.PlayerEvent;
import com.example.sample.domain.model.gamemode.GameMode;
import com.example.sample.domain.model.item.Interactive;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.item.ItemChest;
import com.example.sample.domain.model.item.ItemDoor;
import com.example.sample.domain.model.item.ItemImage;
import com.example.sample.domain.model.item.ItemKey;
import com.example.sample.domain.model.item.ItemPotionRed;
import com.example.sample.domain.model.item.ItemShieldNormal;
import com.example.sample.domain.model.item.ItemType;
import com.example.sample.domain.model.item.ItemWeapon;
import com.example.sample.presentation.GamePanel;
import com.example.sample.presentation.KeyInputType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class WorldMapController {

  private Player player;
  private final PlayerQueryService playerQueryService;
  private Npc npc;
  private final NpcQueryService npcQueryService;
  private Enemy enemy;
  private final EnemyQueryService enemyQueryService;
  private List<Item> fieldItemList = new ArrayList<>();
  private final ItemQueryService itemQueryService;

  private final WorldMapQueryService worldMapQueryService;
  private WorldMap worldMap;

  public void start() {
    // TODO: 動作確認用、後でリファクタリングすること
    worldMap = this.worldMapQueryService.find();
    PlayerAnimation playerAnimation = playerQueryService.find();
    player = new Player(new Location(Tile.TILE_SIZE * 23, Tile.TILE_SIZE * 21), playerAnimation);
    NpcAnimation npcAnimation = npcQueryService.find();
    npc = new Npc(new Location(Tile.TILE_SIZE * 21, Tile.TILE_SIZE * 20), npcAnimation);
    EnemyAnimation enemyAnimation = enemyQueryService.find();
    enemy = new Enemy("スライム", new Location(Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 30), enemyAnimation, new EnemyBattleStatus(new HitPoint(6), new AttackPower(2)));
    ItemImage itemImage = itemQueryService.find(ItemType.POTION_RED);
    Item potionRed = new ItemPotionRed(new Location(Tile.TILE_SIZE * 22, Tile.TILE_SIZE * 7), itemImage);
    fieldItemList.add(potionRed);
    ItemImage itemImageKey = itemQueryService.find(ItemType.KEY);
    Item key = new ItemKey(new Location(Tile.TILE_SIZE * 22, Tile.TILE_SIZE * 9), itemImageKey);
    fieldItemList.add(key);
    ItemImage itemImageDoor = itemQueryService.find(ItemType.DOOR);
    Item door = new ItemDoor(new Location(Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 11), itemImageDoor);
    fieldItemList.add(door);
    ItemImage itemImageChest = itemQueryService.find(ItemType.CHEST);
    Item chest = new ItemChest(new Location(Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 7), itemImageChest);
    fieldItemList.add(chest);
    ItemImage itemImageShield = itemQueryService.find(ItemType.SHIELD_NORMAL);
    Item shieldNormal = new ItemShieldNormal(new DefensePower(2), new Location(Tile.TILE_SIZE * 23, Tile.TILE_SIZE * 7), itemImageShield);
    fieldItemList.add(shieldNormal);
    ItemImage itemImageSword = itemQueryService.find(ItemType.WEAPON);
    Item sword = new ItemWeapon(new AttackPower(2), new Location(Tile.TILE_SIZE * 24, Tile.TILE_SIZE * 7), itemImageSword);
    fieldItemList.add(sword);
  }

  public void update(KeyInputType keyInputType, GameMode gameMode) {
    Vector vector = keyInputType.getVector();
    Location playerWillMoveLocation = player.getLocation().shift(vector);

    for (Item item : fieldItemList) {
      if (item.getCollision().isCollide(player.getCollision().shift(vector))) {
        if (item instanceof Interactive) {
          Event event = ((Interactive) item).interact();
          if (event instanceof GameModeEvent) {
            ((GameModeEvent) event).execute(gameMode);
            return;
          }
          if (event instanceof PlayerEvent) {
            if (((PlayerEvent) event).execute(player)) {
              fieldItemList.remove(item);
            }
          }
        } else {
          player.pickUp(item);
          fieldItemList.remove(item);
        }
        break;
      }
    }

    List<Collidable> collidableListForPlayer = worldMap.getTilesFromLocation(playerWillMoveLocation);
    collidableListForPlayer.add(npc);
    collidableListForPlayer.addAll(fieldItemList.stream().filter(item -> item instanceof Interactive).collect(Collectors.toList()));
    if (player.canMove(collidableListForPlayer, vector)) {
      player.move(vector);
    } else {
      player.changeDirection(vector);
    }
    if (player.isOverlap(enemy)) {
      gameMode.battle();
    }

    Location npcWillMoveLocation = npc.getLocation().shift(npc.getNpcMovement().getVector());
    List<Collidable> collidableListForNpc = worldMap.getTilesFromLocation(npcWillMoveLocation);
    collidableListForNpc.add(player);
    collidableListForPlayer.add(enemy);
    if (npc.updateMovementThenCanMove(collidableListForNpc)) {
      npc.move();
    } else {
      npc.changeDirection();
    }

    Location enemyWillMoveLocation = enemy.getLocation().shift(enemy.getEnemyMovement().getVector());
    List<Collidable> collidableListForEnemy = worldMap.getTilesFromLocation(enemyWillMoveLocation);
    collidableListForEnemy.add(npc);
    if (enemy.updateMovementThenCanMove(collidableListForEnemy)) {
      enemy.move();
    }
    if (player.isOverlap(enemy)) {
      gameMode.battle();
    }
  }

  public void draw(Graphics2D g2) {
    if (player == null && worldMap == null) return;

    Location playerLocation = player.getLocation();
    for (Tile[] tiles : worldMap.getTiles()) {
      for (Tile tile : tiles) {
        Location tileLocation = tile.getLocation();
        int diffX = tileLocation.getX() - playerLocation.getX();
        int diffY = tileLocation.getY() - playerLocation.getY();
        if (
            Math.abs(diffX) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
                Math.abs(diffY) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE
        ) {
          g2.drawImage(tile.getBufferedImage(), GamePanel.screenCenterX + diffX, GamePanel.screenCenterY + diffY, null);
        }
      }
    }
    g2.drawImage(player.getAnimatedImage(), GamePanel.screenCenterX, GamePanel.screenCenterY, null);

    if (npc != null) {
      Location npcLocation = npc.getLocation();
      int diffX = npcLocation.getX() - playerLocation.getX();
      int diffY = npcLocation.getY() - playerLocation.getY();
      if (
        Math.abs(diffX) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
        Math.abs(diffY) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE
      ) {
        g2.drawImage(npc.getAnimatedImage(), GamePanel.screenCenterX + diffX, GamePanel.screenCenterY + diffY, null);
      }

      if (enemy != null) {
        Location enemyLocation = enemy.getLocation();
        int diffXForEnemy = enemyLocation.getX() - playerLocation.getX();
        int diffYForEnemy = enemyLocation.getY() - playerLocation.getY();
        if (
          Math.abs(diffXForEnemy) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
          Math.abs(diffYForEnemy) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE
        ) {
          g2.drawImage(enemy.getAnimatedImage(), GamePanel.screenCenterX + diffXForEnemy, GamePanel.screenCenterY + diffYForEnemy, null);
        }
      }

      for (Item item : fieldItemList) {
        Location potionRedLocation = item.getLocation();
        int diffXForPotionRed = potionRedLocation.getX() - playerLocation.getX();
        int diffYForPotionRed = potionRedLocation.getY() - playerLocation.getY();
        if (
          Math.abs(diffXForPotionRed) <= GamePanel.screenWidth / 2 + Tile.TILE_SIZE &&
          Math.abs(diffYForPotionRed) <= GamePanel.screenHeight / 2 + Tile.TILE_SIZE
        ) {
          g2.drawImage(item.getImage(), GamePanel.screenCenterX + diffXForPotionRed, GamePanel.screenCenterY + diffYForPotionRed, null);
        }
      }
    }
  }

  public Player getPlayer() {
    return player;
  }

  public Enemy getEnemy() {
    return enemy;
  }
}
