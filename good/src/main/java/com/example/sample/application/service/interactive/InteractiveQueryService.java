package com.example.sample.application.service.interactive;

import com.example.sample.application.repository.interactive.InteractiveRepository;
import com.example.sample.domain.model.interactive.Interactions;
import org.springframework.stereotype.Service;

/**
 * 相互作用トリガーの参照サービス
 */
@Service
public class InteractiveQueryService {

  private final InteractiveRepository interactiveRepository;

  public InteractiveQueryService(InteractiveRepository interactiveRepository) {
    this.interactiveRepository = interactiveRepository;
  }

  /**
   * 相互作用トリガーのコレクションをみつける
   */
  public Interactions find() {
    return interactiveRepository.find();
  }
}
