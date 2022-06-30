package com.example.sample.domain.model.technique.magic;

public enum MagicType {
  FIRE(new Fire()),
  SHIDEN(new Shiden()),
  HELL_FIRE(new HellFire());

  private final Magic magic;

  MagicType(final Magic magic) {
    this.magic = magic;
  }

  public Magic getMagic() {
    return magic;
  }
}
