INSERT IGNORE INTO tb_world_map VALUES
(   1, 32, 39, CONCAT(
  -- ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩ ⑪ ⑫ ⑬ ⑭ ⑮ ⑯ ⑰ ⑱ ⑲ ⑳ ㉑ ㉒ ㉓ ㉔ ㉕ ㉖ ㉗ ㉘ ㉙ ㉚ ㉛ ㉜
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ①
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ②
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌊 🌊 🌊 🌊 🌊 🌱 🌳 🌳 🌳 🌳 🌳', -- ③
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌊 🌊 🌊 🌊 🌊 🌊 🌊 🌳 🌳 🌳 🌳 🌳', -- ④
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌊 🌊 🌊 🌊 🌊 🌊 🌊 🌳 🌳 🌳 🌳 🌳', -- ⑤
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌊 🌊 🌊 🌊 🌊 🌊 🌊 🌳 🌳 🌳 🌳 🌳', -- ⑥
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 🧱 🧱 🧱 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌊 🌊 🌊 🌊 🌊 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑦
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 ◾ ◾ ◾ 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 ◽ 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳', -- ⑧
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 ◾ ◾ ◾ 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑨
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 ◾ ◾ ◾ 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑩
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 ◾ ◾ ◾ 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑪
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🧱 🧱 🌱 🧱 🧱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑫
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑬
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑭
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑮
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑯
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑰
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 ◽ 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑱
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 ◽ 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑲
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 ◽ 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳', -- ⑳
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 ◽ 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳', -- ㉑
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ ◽ 🌳 🌳 🌳 🌳 🌳', -- ㉒
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 ◽ 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳', -- ㉓
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 ◽ 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉔
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 ◽ 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉕
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉖
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉗
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳', -- ㉘
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉙
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉚
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉛
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉜
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉝
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉞
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌱 🌱 🌱 🌱 🌱 🌱 🌱 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㉟
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㊱
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㊲
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳', -- ㊳
    '🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳') -- ㊴
);