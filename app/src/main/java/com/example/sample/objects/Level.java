package com.example.sample.objects;

/**
 * リスト9.16 メンバーのレベルを表現するクラス
 */
public class Level {
  private static final int MIN = 1;
  private static final int MAX = 99;
  final int value;

  private Level(final int value) {
    if (value < MIN || MAX < value) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }

  // 初期レベルを返す
  public static Level initialize() {
    return new Level(MIN);
  }

  // レベルアップする
  public Level increase() {
    if (value < MAX) return new Level(value + 1);
    return this;
  }

  public int value() {
    return value;
  }
}
