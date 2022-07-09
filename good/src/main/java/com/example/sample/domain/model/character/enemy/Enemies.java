package com.example.sample.domain.model.character.enemy;

import java.util.Collections;
import java.util.List;

/**
 * 敵のコレクション
 */
public class Enemies {
  private List<Enemy> enemies;

  public Enemies(final List<Enemy> enemies) {
    this.enemies = enemies;
  }

  public List<Enemy> enemies() {
    return Collections.unmodifiableList(enemies);
  }

  public void remove(Enemy enemy) {
    enemies.remove(enemy); // TODO: 本当は不変にして、変更時は新しいインスタンスを返すようにしたい..
  }
}
