package com.example.sample.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("位置を表現するクラス（Locationクラス）のテスト")
class LocationTest {

  @Test
  void x軸の移動量とy軸の移動量を渡して移動先の位置を返すことができる() {
    Location location = new Location(10, 20);

    Location actual = location.shift(30, 40);

    assertEquals(40, actual.getX());
    assertEquals(60, actual.getY());
  }

  @Test
  void ベクトルを渡して移動先の位置を返すことができる() {
    Location location = new Location(10, 20);
    Vector vector = new Vector(30, 40);

    Location actual = location.shift(vector);

    assertEquals(40, actual.getX());
    assertEquals(60, actual.getY());
  }
}