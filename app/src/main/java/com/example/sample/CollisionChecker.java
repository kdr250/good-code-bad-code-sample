package com.example.sample;

import com.example.sample.entity.Entity;

public class CollisionChecker {

  GamePanel panel;

  public CollisionChecker(GamePanel panel) {
    this.panel = panel;
  }

  public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.location.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.location.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.location.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.location.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeftWorldX / panel.tileSize;
    int entityRightCol = entityRightWorldX / panel.tileSize;
    int entityTopRow = entityTopWorldY / panel.tileSize;
    int entityBottomRow = entityBottomWorldY / panel.tileSize;

    int tileNum1, tileNum2;

    switch (entity.direction) {
      case "up":
        entityTopRow = (entityTopWorldY - entity.speed) / panel.tileSize;
        tileNum1 = panel.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = panel.tileM.mapTileNum[entityRightCol][entityTopRow];
        if (panel.tileM.tiles[tileNum1].collision || panel.tileM.tiles[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "down":
        entityBottomRow = (entityBottomWorldY + entity.speed) / panel.tileSize;
        tileNum1 = panel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = panel.tileM.mapTileNum[entityRightCol][entityBottomRow];
        if (panel.tileM.tiles[tileNum1].collision || panel.tileM.tiles[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "left":
        entityLeftCol = (entityLeftWorldX - entity.speed) / panel.tileSize;
        tileNum1 = panel.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = panel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        if (panel.tileM.tiles[tileNum1].collision || panel.tileM.tiles[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "right":
        entityRightCol = (entityRightWorldX + entity.speed) / panel.tileSize;
        tileNum1 = panel.tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = panel.tileM.mapTileNum[entityRightCol][entityBottomRow];
        if (panel.tileM.tiles[tileNum1].collision || panel.tileM.tiles[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
    }
  }

  public int checkObject(Entity entity, boolean player) {
    int index = 999;

    for (int i = 0; i < panel.objects.length; i++) {
      if (panel.objects[i] != null) {
        // Entityのコリジョンエリア
        entity.solidArea.x = entity.location.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.location.worldY + entity.solidArea.y;

        // objectsのコリジョンエリア
        panel.objects[i].solidArea.x = panel.objects[i].location.worldX + panel.objects[i].solidArea.x;
        panel.objects[i].solidArea.y = panel.objects[i].location.worldY + panel.objects[i].solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(panel.objects[i].solidArea)) {
              if (panel.objects[i].collision) entity.collisionOn = true;
              if (player) index = i;
            }
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(panel.objects[i].solidArea)) {
              if (panel.objects[i].collision) entity.collisionOn = true;
              if (player) index = i;
            }
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(panel.objects[i].solidArea)) {
              if (panel.objects[i].collision) entity.collisionOn = true;
              if (player) index = i;
            }
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(panel.objects[i].solidArea)) {
              if (panel.objects[i].collision) entity.collisionOn = true;
              if (player) index = i;
            }
            break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        panel.objects[i].solidArea.x = panel.objects[i].solidAreaDefaultX;
        panel.objects[i].solidArea.y = panel.objects[i].solidAreaDefaultY;
      }
    }
    return index;
  }

  public int checkEntity(Entity entity, Entity[] targets) {
    int index = 999;
    for (int i = 0; i < targets.length; i++) {
      if (targets[i] != null) {
        // Entityのコリジョンエリア
        entity.solidArea.x = entity.location.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.location.worldY + entity.solidArea.y;

        // objectsのコリジョンエリア
        targets[i].solidArea.x = targets[i].location.worldX + targets[i].solidArea.x;
        targets[i].solidArea.y = targets[i].location.worldY + targets[i].solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            break;
        }
        if (entity.solidArea.intersects(targets[i].solidArea) && entity != targets[i]) {
          entity.collisionOn = true;
          index = i;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        targets[i].solidArea.x = targets[i].solidAreaDefaultX;
        targets[i].solidArea.y = targets[i].solidAreaDefaultY;
      }
    }
    return index;
  }
}
