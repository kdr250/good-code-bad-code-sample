package com.example.sample.application.service.interactive;

import com.example.sample.application.repository.interactive.InteractiveRepository;
import com.example.sample.domain.model.interactive.Interactions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 相互作用トリガーの参照サービス
 */
@Service
@RequiredArgsConstructor
public class InteractiveQueryService {

  private final InteractiveRepository interactiveRepository;

  /**
   * 相互作用トリガーのコレクションをみつける
   */
  public Interactions find() {
    return interactiveRepository.find();
  }
}
