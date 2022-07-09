# 概要
自己学習用。
<br><br>
書籍『良いコード／悪いコードで学ぶ設計入門』で紹介されているコードを参考にした、サンプルRPGゲームです。
<br><br>
【参考文献】
<br>
ミノ駆動さん [良いコード／悪いコードで学ぶ設計入門 ― 保守しやすい 成長し続けるコードの書き方](https://www.amazon.co.jp/dp/B09Y1MWK9N/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1)

## 悪いコード例
- 未成熟なクラスの例
  - [×リスト1.17 生焼けオブジェクトの例](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AssetManager.java#L33)
- ヒットポイントの悪いコード例
  - [×リスト2.6 単なる変数として用意されたヒットポイント](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/entity/Entity.java#L41)
  - [×リスト2.7 どこかに書かれるヒットポイント減少ロジック](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/UI.java#L230)
  - [×リスト2.8 どこかに書かれるヒットポイント回復ロジック](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/ItemPotionRed.java#L22)
- 攻撃力・武器の悪いコード例
  - [×リスト4.6 攻撃力を表現するクラス](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AttackPower.java#L4)
  - [×リスト4.8 AttackPowerインスタンスを使い回している](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AssetManager.java#L18)
  - [×リスト4.9 使い回している攻撃力を変更すると...?](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AssetManager.java#L19)
  - [×リスト4.10 別の武器の攻撃力まで変化してしまう](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AssetManager.java#L20)
  - [×リスト4.13 攻撃力を変化させるメソッドを追加](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/AttackPower.java#L19)
- ゲームキャラの移動の悪いコード例
  - [×リスト5.14 引数の変更をしている](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/ActorManager.java#L6)
- 魔法力を回復する場合の悪いコード例
  - [×リスト5.23 引数の多いメソッド](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/objects/ItemPotionBlue.java#L30)
- 魔法攻撃の悪いコード例
  - [×リスト6.11 switch文で表示名を切り替え](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/MagicManager.java#L8)
  - [×リスト6.15 costMagicPointメソッドにcase文追加](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/MagicManager.java#L29)
  - [×リスト6.16 case文の追加漏れ](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/UI.java#L723)
- 装備のnull問題の悪いコード例
  - [×リスト9.6 装備防具と防御力を表現するロジックの一部](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/entity/Player.java#L30)
  - [×リスト9.7 装備していない状態をnullで表現している](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/entity/Player.java#L105)
  - [×リスト9.8 null前提だとnullチェックしなければならない](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/entity/Player.java#L114)
- リフレクションの悪いコード例
  - [×リスト9.17 リフレクションを用いた値の書き換え](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/UI.java#L757)
  - [×リスト9.18 不正な値に書き換わってしまう](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/UI.java#L758)
- 敵を表現するクラスの悪いコード例
  - [×リスト10.19 敵を表現するクラス](https://github.com/kdr250/good-code-bad-code-sample/blob/b11ac468e9cdc54a8f976376ca5a009ed329db90/app/src/main/java/com/example/sample/monsters/Enemy.java#L17)

## 良いコード例
- ヒットポイントの良いコード例
  - [○リスト2.9 クラスにすると強く関係するデータとロジックをまとめられる]()
- 攻撃力・武器の良いコード例
  - [○リスト4.18 不変で堅牢になったAttackPowerクラス]()
- ゲームキャラの移動の良いコード例
  - [○リスト5.18 引数を変更しない構造へ改善]()
  - [○リスト12.10 エラーは例外をスローする形にする]()
- 魔法力を回復する場合の良いコード例
  - [○リスト5.29 魔法力に関係するロジックをカプセル化]()
- 装備品を装備する場合の良いコード例
  - [○リスト5.31 詳細なロジックは呼ぶ側ではなく、呼ばれる側に実装しよう]()
- 魔法攻撃の良いコード例
  - [○リスト6.37 魔法interfaceの値オブジェクト導入版]()
  - [○リスト6.38 魔法「ファイア」の値オブジェクト導入版]()
  - [○リスト6.39 魔法「紫電」の値オブジェクト導入版]()
  - [○リスト6.40 魔法「地獄の業火」の値オブジェクト導入版]()
- 装備のnull問題の良いコード例
  - [○リスト9.11 「装備なし」をnullでない方法で表現]()
- パーティーの所持品の良いコード例
  - [○リスト10.20 パーティーの所持品を表現するクラス]()

- モデリングの例
  - 13.3.7 モデルと実装は必ず相互にフィードバックするの例
    - モデル図を [good/docs/modeling]() 配下に配置しました。
    - jigで出力した図を [good/docs/jig]() 配下に配置しました。

## 起動方法
```
./gradlew :good:run
```

## JIG 設計ドキュメントの生成
```
./gradlew :good:clean build jig
```
※参考: [jig](https://github.com/dddjava/jig) 

## 解説記事
- [Qiita - ミノ駆動本を読んでクソゲー作ってみた](https://qiita.com/KeiFunahashi/items/eb42d9de6290a01722df)
