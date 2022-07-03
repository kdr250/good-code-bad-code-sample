CREATE TABLE IF NOT EXISTS tb_location (
    id INTEGER PRIMARY KEY,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_hit_point (
    id INTEGER PRIMARY KEY,
    hit_point INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_attack_power (
    id INTEGER PRIMARY KEY,
    attack_power INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_magic_power (
    id INTEGER PRIMARY KEY,
    magic_power INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_technique (
    id INTEGER PRIMARY KEY,
    type ENUM('Physics', 'Magic') NOT NULL,
    name varchar(100) NOT NULL,
    cost INTEGER NOT NULL,
    attack INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_world_map (
    id INTEGER PRIMARY KEY,
    player_start_location_id INTEGER NOT NULL,
    horizontal_size INTEGER NOT NULL,
    vertical_size INTEGER NOT NULL,
    tiles TEXT NOT NULL,
    CONSTRAINT fk_player_start_location_id FOREIGN KEY (player_start_location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_player (
    id INTEGER PRIMARY KEY,
    location_id INTEGER NOT NULL,
    hit_point_id INTEGER NOT NULL,
    attack_power_id INTEGER NOT NULL,
    magic_power_id INTEGER NOT NULL,
    CONSTRAINT fk_player_location_id FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_player_hit_point_id FOREIGN KEY (hit_point_id) REFERENCES tb_hit_point(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_player_attack_power_id FOREIGN KEY (attack_power_id) REFERENCES tb_attack_power(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_player_magic_power_id FOREIGN KEY (magic_power_id) REFERENCES tb_magic_power(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_techniques (
    id INTEGER PRIMARY KEY,
    player_id INTEGER NOT NULL,
    technique_one INTEGER NOT NULL,
    technique_two INTEGER NOT NULL,
    technique_three INTEGER NOT NULL,
    technique_four INTEGER NOT NULL,
    CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES tb_player(id),
    CONSTRAINT fk_technique_one FOREIGN KEY (technique_one) REFERENCES tb_technique(id),
    CONSTRAINT fk_technique_two FOREIGN KEY (technique_two) REFERENCES tb_technique(id),
    CONSTRAINT fk_technique_three FOREIGN KEY (technique_three) REFERENCES tb_technique(id),
    CONSTRAINT fk_technique_four FOREIGN KEY (technique_four) REFERENCES tb_technique(id)
);

CREATE TABLE IF NOT EXISTS tb_enemy (
    id INTEGER PRIMARY KEY,
    world_map_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    hit_point_id INTEGER NOT NULL,
    attack_power_id INTEGER NOT NULL,
    CONSTRAINT fk_world_map_id FOREIGN KEY (world_map_id) REFERENCES tb_world_map(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_enemy_location_id FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_enemy_hit_point_id FOREIGN KEY (hit_point_id) REFERENCES tb_hit_point(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_enemy_attack_power_id FOREIGN KEY (attack_power_id) REFERENCES tb_attack_power(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS tb_npc (
    id INTEGER PRIMARY KEY,
    world_map_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    CONSTRAINT fk_npc_world_map_id FOREIGN KEY (world_map_id) REFERENCES tb_world_map(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_npc_location_id_npc FOREIGN KEY (location_id) REFERENCES tb_location(id) ON DELETE RESTRICT ON UPDATE RESTRICT
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
