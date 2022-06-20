CREATE TABLE IF NOT EXISTS tb_world_map (
    id INTEGER PRIMARY KEY,
    horizontal_size INTEGER NOT NULL,
    vertical_size INTEGER NOT NULL,
    tiles TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_tile_image (
    id INTEGER PRIMARY KEY,
    name ENUM('GRASS', 'WALL', 'WATER', 'FLOOR', 'TREE', 'SAND') UNIQUE NOT NULL,
    image LONGTEXT NOT NULL
);
