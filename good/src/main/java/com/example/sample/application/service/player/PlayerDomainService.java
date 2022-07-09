package com.example.sample.application.service.player;

import com.example.sample.application.service.item.ItemQueryService;
import com.example.sample.domain.model.battle.AttackPower;
import com.example.sample.domain.model.battle.technique.Technique;
import com.example.sample.domain.model.character.enemy.Enemy;
import com.example.sample.domain.model.item.Item;
import com.example.sample.domain.model.worldmap.Collidable;
import com.example.sample.domain.model.character.player.Player;
import com.example.sample.domain.model.worldmap.Vector;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * プレイヤーのドメインサービス
 */
@Service
public class PlayerDomainService {

  private final ItemQueryService itemQueryService;

  public PlayerDomainService(ItemQueryService itemQueryService) {
    this.itemQueryService = itemQueryService;
  }

  /**
   * プレイヤーを移動する
   */
  public void move(final Player player, final List<Collidable> collidableList, final Vector vector) {
    if (!player.canMove(collidableList, vector)) {
      player.changeDirection(vector);
      return;
    }

    player.move(vector);
  }

  /**
   * プレイヤーが敵に攻撃する
   */
  public void attackEnemy(final Player player, final Technique technique, final Enemy enemy) {
    player.consumeCostForAttack(technique);
    AttackPower attackPower = player.totalAttackPower(technique);
    enemy.damageHitPoint(attackPower.toDamage());
  }

  /**
   * プレイヤーが敵からの攻撃を受ける
   */
  public void damaged(final Player player,final Enemy enemy) {
    AttackPower attackPower = enemy.attackPower();
    player.damageHitPoint(attackPower.toDamage());
  }

  /**
   * プレイヤーが敵のドロップしたアイテムを取得する
   */
  public void pickUpDropItem(final Player player, final Enemy enemy) {
    Item dropItem = itemQueryService.find(enemy.dropItemType());
    player.pickUp(dropItem);
  }
}
