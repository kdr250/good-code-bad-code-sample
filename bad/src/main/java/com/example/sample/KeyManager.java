package com.example.sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

  GamePanel panel;

  public boolean upPressed, downPressed, leftPressed, rightPressed, checkDrawTime, enterPressed, shotKeyPressed;

  public KeyManager(GamePanel panel) {
    this.panel = panel;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (panel.ui.isGameFinished) {
      gameFinishState(code);
    }
    // タイトル状態
    else if (panel.gameState == panel.titleState) {
      titleState(code);
    }
    // プレイ状態
    else if (panel.gameState == panel.playState) {
      playState(code);
    } else if (panel.gameState == panel.pauseState) {
      pauseState(code);
    } else if (panel.gameState == panel.dialogState) {
      dialogState(code);
    } else if (panel.gameState == panel.characterState) {
      characterState(code);
    } else if (panel.gameState == panel.optionState) {
      optionState(code);
    } else if (panel.gameState == panel.gameOverState) {
      gameOverState(code);
    } else if (panel.gameState == panel.battleState) {
      battleState(code);
    }
  }

  private void gameFinishState(int code) {
    if (code == KeyEvent.VK_ENTER) {
      enterPressed = true;
    }
  }

  private void battleState(int code) {
    if (code == KeyEvent.VK_ENTER) {
      enterPressed = true;
      return;
    }

    int maxCommandNum = 0;
    switch (panel.ui.subState) {
      case 0:
        maxCommandNum = 1;
        break;
      case 1:
      case 2:
        maxCommandNum = 3;
        break;
    }

    if (code == KeyEvent.VK_W) {
      panel.ui.commandNum--;
      if (panel.ui.commandNum < 0) {
        panel.ui.commandNum = maxCommandNum;
      }
      panel.playSE(2);
    }
    else if (code == KeyEvent.VK_S) {
      panel.ui.commandNum++;
      if (panel.ui.commandNum > maxCommandNum) {
        panel.ui.commandNum = 0;
      }
      panel.playSE(2);
    }
  }

  private void gameOverState(int code) {
    if (code == KeyEvent.VK_W) {
      panel.ui.commandNum--;
      if (panel.ui.commandNum < 0) {
        panel.ui.commandNum = 1;
      }
      panel.playSE(2);
    }
    if (code == KeyEvent.VK_S) {
      panel.ui.commandNum++;
      if (panel.ui.commandNum > 1) {
        panel.ui.commandNum = 0;
      }
      panel.playSE(2);
    }
    if (code == KeyEvent.VK_ENTER) {
      if (panel.ui.commandNum == 0) {
        panel.gameState = panel.playState;
        panel.retry();
      } else if (panel.ui.commandNum == 1) {
        panel.gameState = panel.titleState;
      }
    }
  }

  private void optionState(int code) {
    if (code == KeyEvent.VK_ESCAPE) {
      panel.gameState = panel.playState;
      return;
    } else if (code == KeyEvent.VK_ENTER) {
      enterPressed = true;
      return;
    }

    int maxCommandNum = 0;
    switch (panel.ui.subState) {
      case 0:
        maxCommandNum = 4;
        break;
      case 3:
        maxCommandNum = 1;
        break;
    }

    if (code == KeyEvent.VK_W) {
      panel.ui.commandNum--;
      panel.playSE(2);
      if (panel.ui.commandNum < 0) {
        panel.ui.commandNum = maxCommandNum;
      }
    } else if (code == KeyEvent.VK_S) {
      panel.ui.commandNum++;
      panel.playSE(2);
      if (panel.ui.commandNum > maxCommandNum) {
        panel.ui.commandNum = 0;
      }
    } else if (code == KeyEvent.VK_A) {
      if (panel.ui.subState == 0) {
        if (panel.ui.commandNum == 0 && panel.music.volumeScale > 0) {
          panel.music.volumeScale--;
          panel.music.checkVolume();
          panel.playSE(2);
        }
        if (panel.ui.commandNum == 1 && panel.se.volumeScale > 0) {
          panel.se.volumeScale--;
          panel.playSE(2);
        }
      }
    } else if (code == KeyEvent.VK_D) {
      if (panel.ui.subState == 0) {
        if (panel.ui.commandNum == 0 && panel.music.volumeScale < 5) {
          panel.music.volumeScale++;
          panel.music.checkVolume();
          panel.playSE(2);
        }
        if (panel.ui.commandNum == 1 && panel.se.volumeScale < 5) {
          panel.se.volumeScale++;
          panel.playSE(2);
        }
      }
    }

    panel.keyManager.enterPressed = false;
  }

  private void titleState(int code) {
    switch (code) {
      case KeyEvent.VK_W:
        panel.ui.commandNum = panel.ui.commandNum <= 0 ? panel.ui.commandNum : panel.ui.commandNum - 1;
        break;
      case KeyEvent.VK_S:
        panel.ui.commandNum = panel.ui.commandNum >= 2 ? panel.ui.commandNum : panel.ui.commandNum + 1;
        break;
      case KeyEvent.VK_ENTER:
        if (panel.ui.commandNum == 0) {
          panel.gameState = panel.playState;
        } else if (panel.ui.commandNum == 1) {
          System.exit(0);
        }
        break;
    }
  }

  private void playState(int code) {
    switch (code) {
      case KeyEvent.VK_W:
        upPressed = true;
        break;
      case KeyEvent.VK_S:
        downPressed = true;
        break;
      case KeyEvent.VK_A:
        leftPressed = true;
        break;
      case KeyEvent.VK_D:
        rightPressed = true;
        break;
      case KeyEvent.VK_F:
        shotKeyPressed = true;
        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
      case KeyEvent.VK_P:
        panel.gameState = panel.pauseState;
        break;
      case KeyEvent.VK_I:
        panel.gameState = panel.characterState;
        break;
      case KeyEvent.VK_ESCAPE:
        panel.gameState = panel.optionState;
        break;
    }
  }

  private void pauseState(int code) {
    if (code == KeyEvent.VK_P) panel.gameState = panel.playState;
  }

  private void dialogState(int code) {
    if (code == KeyEvent.VK_ENTER) {
      enterPressed = false;
      panel.gameState = panel.playState;
    }
  }

  private void characterState(int code) {
    switch (code) {
      case KeyEvent.VK_I:
        panel.gameState = panel.playState;
        break;
      case KeyEvent.VK_W:
        if (panel.ui.slotRow != 0) {
          panel.ui.slotRow--;
          panel.playSE(2);
        }
        break;
      case KeyEvent.VK_A:
        if (panel.ui.slotCol != 0) {
          panel.ui.slotCol--;
          panel.playSE(2);
        }
        break;
      case KeyEvent.VK_S:
        if (panel.ui.slotRow != 3) {
          panel.ui.slotRow++;
          panel.playSE(2);
        }
        break;
      case KeyEvent.VK_D:
        if (panel.ui.slotCol != 4) {
          panel.ui.slotCol++;
          panel.playSE(2);
        }
        break;
      case KeyEvent.VK_ENTER:
        panel.player.selectItem();
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_W:
        upPressed = false;
        break;
      case KeyEvent.VK_S:
        downPressed = false;
        break;
      case KeyEvent.VK_A:
        leftPressed = false;
        break;
      case KeyEvent.VK_D:
        rightPressed = false;
        break;
      case KeyEvent.VK_F:
        shotKeyPressed = false;
        break;
    }
  }
}
