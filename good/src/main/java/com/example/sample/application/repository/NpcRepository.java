package com.example.sample.application.repository;

import com.example.sample.domain.model.Npc;

import java.util.List;

public interface NpcRepository {
  List<Npc> find();
}
