package com.example.sample.tiles;

import com.example.sample.GamePanel;
import com.example.sample.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class TileManager {

  GamePanel panel;
  public Tile[] tiles;
  public int[][] mapTileNum;

  public TileManager(GamePanel panel) {
    this.panel = panel;
    tiles = new Tile[10];
    mapTileNum = new int[panel.maxWorldCol][panel.maxWorldRow];
    getTileImage();
    loadMap("src/main/resources/maps/world01.txt");
  }

  public void getTileImage() {
    setup(0, "grass", false);
    setup(1, "wall", true);
    setup(2, "water", true);
    setup(3, "floor", false);
    setup(4, "tree", true);
    setup(5, "sand", false);
  }

  public void setup(int index, String imagePath, boolean collision) {
    try {
      tiles[index] = new Tile();
      BufferedImage originalImage = ImageIO.read(Paths.get("src/main/resources/tiles/" + imagePath + ".png").toFile());
      tiles[index].image = UtilityTool.scaleImage(originalImage, panel.tileSize, panel.tileSize);
      tiles[index].collision = collision;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadMap(String filePath) {
    try {
      InputStream is = new FileInputStream(Paths.get(filePath).toFile());
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < panel.maxWorldCol && row < panel.maxWorldRow) {
        String line = br.readLine();

        while (col < panel.maxWorldCol) {
          if (line != null) {
            String[] numbers = line.split(" ");
            int num = Integer.parseInt(numbers[col]);
            mapTileNum[col][row] = num;
          }
          col++;
        }
        if (col == panel.maxWorldCol) {
          col = 0;
          row++;
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;

    while (worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow) {

      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * panel.tileSize;
      int worldY = worldRow * panel.tileSize;
      int screenX = worldX - panel.player.location.worldX + panel.player.screenX;
      int screenY = worldY - panel.player.location.worldY + panel.player.screenY;

      if (
          worldX + panel.tileSize > panel.player.location.worldX - panel.player.screenX &&
              worldX - panel.tileSize < panel.player.location.worldX + panel.player.screenX &&
              worldY + panel.tileSize > panel.player.location.worldY - panel.player.screenY &&
              worldY - panel.tileSize < panel.player.location.worldY + panel.player.screenY
      ) {
        g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
      }

      worldCol++;

      if (worldCol == panel.maxWorldCol) {
        worldCol = 0;
        worldRow++;
      }
    }
  }
}
