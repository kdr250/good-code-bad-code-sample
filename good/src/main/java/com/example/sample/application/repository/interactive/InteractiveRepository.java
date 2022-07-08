package com.example.sample.application.repository.interactive;

import com.example.sample.domain.model.interactive.Interactions;

/**
 * 相互作用トリガーのレポジトリ
 */
public interface InteractiveRepository {
  Interactions find();
}
