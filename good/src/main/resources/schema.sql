CREATE TABLE IF NOT EXISTS tb_world_map (
    id INTEGER PRIMARY KEY,
    horizontal_size INTEGER NOT NULL,
    vertical_size INTEGER NOT NULL,
    tiles TEXT NOT NULL
);

--CREATE TABLE IF NOT EXISTS tb_world_map_placement (
--    id INTEGER PRIMARY KEY,
--    FOREIGN KEY fk_world_map_id (world_map_id) REFERENCES tb_world_map(id),
--    name VARCHAR(100)
--);

CREATE TABLE IF NOT EXISTS tb_tile_image (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_player_image (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_npc_image (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_enemy_image (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_item_image (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);
