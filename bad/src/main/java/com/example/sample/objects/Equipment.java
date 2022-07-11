package com.example.sample.objects;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Equipment {

  int defence();

  BufferedImage image();

  List<Integer> maxMagicPointIncrements();
}
