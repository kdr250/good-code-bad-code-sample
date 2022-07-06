/*
 * 位置を表現するクラス
 * ○リスト5.18 引数を変更しない構造へ改善
 * ○リスト12.10 エラーは例外をスローする形にする
 */
package com.example.sample.domain.model.worldmap;

import lombok.Getter;

/**
 * 位置
 */
@Getter
public class Location {
  private final int x;
  private final int y;

  public Location(final int x, final int y) {
    if (valid(x, y)) {
      throw new IllegalArgumentException("不正な位置です");
    }

    this.x = x;
    this.y = y;
  }

  public Location shift(final int shiftX, final int shiftY) {
    final int nextX = x + shiftX;
    final int nextY = y + shiftY;
    return new Location(nextX, nextY);
  }

  public Location shift(final Vector vector) {
    final int nextX = x + vector.getX();
    final int nextY = y + vector.getY();
    return new Location(nextX, nextY);
  }

  public static final Location EMPTY = new Location(0, 0);

  private boolean valid(int x, int y) {
    return x < 0 || y < 0;
  }

  @Override
  public String toString() {
    return "Location{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
