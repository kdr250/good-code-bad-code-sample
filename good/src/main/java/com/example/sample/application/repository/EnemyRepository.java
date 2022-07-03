package com.example.sample.application.repository;

import com.example.sample.domain.model.Enemy;

import java.util.List;

public interface EnemyRepository {
  List<Enemy> find();
}
