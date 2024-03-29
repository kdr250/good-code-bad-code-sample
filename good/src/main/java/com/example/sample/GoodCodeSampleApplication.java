/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.sample;

import com.example.sample.presentation.GamePanel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GoodCodeSampleApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(GoodCodeSampleApplication.class)
        .headless(false).run(args);

    GamePanel gamePanel = ctx.getBean(GamePanel.class);

    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2D Adventure");

    window.add(gamePanel);

    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamePanel.startGameThread();
  }
}
