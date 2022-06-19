package com.example.sample.application.repository;

import com.example.sample.domain.model.WorldMap;

public interface WorldMapRepository {
  WorldMap find(Integer worldId);
}
