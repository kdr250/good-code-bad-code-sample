package com.example.sample.domain.model;

import lombok.Getter;

/**
 * プレイヤーを表現するクラス
 */
@Getter
public class Player {
  private Location location;

  public Player(final Location location) {
    this.location = location;
  }

  public void move(final Vector vector) {
    location = location.shift(vector);
  }
}
