package com.example.sample.domain.model;

import lombok.Getter;

/**
 * ベクトルを表現するクラス
 */
@Getter
public class Vector {
  private final int x;
  private final int y;

  public Vector(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
}
