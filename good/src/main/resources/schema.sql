CREATE TABLE IF NOT EXISTS tb_location (
    id INTEGER PRIMARY KEY,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_world_map (
    id INTEGER PRIMARY KEY,
    player_start_location_id INTEGER NOT NULL,
    horizontal_size INTEGER NOT NULL,
    vertical_size INTEGER NOT NULL,
    tiles TEXT NOT NULL,
    CONSTRAINT fk_player_start_location_id FOREIGN KEY (player_start_location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_enemy (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    world_map_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    CONSTRAINT fk_enemy_world_map_id FOREIGN KEY (world_map_id) REFERENCES tb_world_map(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_enemy_location_id FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_npc (
    id INTEGER PRIMARY KEY,
    world_map_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    CONSTRAINT fk_npc_world_map_id FOREIGN KEY (world_map_id) REFERENCES tb_world_map(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_npc_location_id_npc FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_item (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    world_map_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    CONSTRAINT fk_item_world_map_id FOREIGN KEY (world_map_id) REFERENCES tb_world_map(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_item_location_id_npc FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

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
