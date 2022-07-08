package com.example.sample.domain.model.interactive;

import java.util.Collections;
import java.util.List;

/**
 * 相互作用トリガーのコレクション
 */
public class Interactions {
  private List<Interactive> interactions;

  public Interactions(final List<Interactive> interactions) {
    this.interactions = interactions;
  }

  public List<Interactive> interactions() {
    return Collections.unmodifiableList(interactions);
  }

  public void remove(final Interactive interactive) {
    interactions.remove(interactive);
  }
}
