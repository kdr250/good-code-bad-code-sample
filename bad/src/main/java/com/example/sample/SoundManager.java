package com.example.sample;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class SoundManager {

  Clip clip;
  URL[] soundURL = new URL[30];
  FloatControl fc;
  int volumeScale = 3;
  float volume;

  public SoundManager() {
    try {
      soundURL[0] = Paths.get("src/main/resources/sounds/coin.wav").toUri().toURL();
      soundURL[1] = Paths.get("src/main/resources/sounds/fanfare.wav").toUri().toURL();
      soundURL[2] = Paths.get("src/main/resources/sounds/cursor.wav").toUri().toURL();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setFile(int i) {
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);
      fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
      checkVolume();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play() {
    clip.start();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop() {
    clip.stop();
  }

  public void checkVolume() {
    switch (volumeScale) {
      case 0:
        volume = -80f;
        break;
      case 1:
        volume = -20;
        break;
      case 2:
        volume = -12;
        break;
      case 3:
        volume = -5f;
        break;
      case 4:
        volume = 1f;
        break;
      case 5:
        volume = 6f;
        break;
    }
    fc.setValue(volume);
  }
}
