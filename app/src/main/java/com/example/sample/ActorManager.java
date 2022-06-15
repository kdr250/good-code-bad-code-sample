package com.example.sample;

import com.example.sample.entity.Location;

/**
 * ×リスト5.14 引数の変更をしている
 */
public class ActorManager {

  // ゲームキャラの位置を移動する
  void shift(Location location, int shiftX, int shiftY) {
    location.worldX += shiftX;
    location.worldY += shiftY;
  }
}
