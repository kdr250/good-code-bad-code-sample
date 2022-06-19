CREATE TABLE IF NOT EXISTS tb_world_map (
    id INTEGER PRIMARY KEY,
    horizontal_size INTEGER NOT NULL,
    vertical_size INTEGER NOT NULL,
    tiles TEXT NOT NULL
);
