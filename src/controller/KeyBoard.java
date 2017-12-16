package controller;

import java.awt.event.KeyEvent;

public class KeyBoard {
	public GameWindow gamewindow;
	public KeyBoard(GameWindow gamewindow) {
		this.gamewindow=gamewindow;
	}
	public void keyPressed(KeyEvent e) {
		if (State.getInstance().isStart()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_UP:
					if (gamewindow.startop!=0) gamewindow.startop--;
					gamewindow.repaint();
					break;
				case KeyEvent.VK_DOWN:
					if (gamewindow.startop!=2) gamewindow.startop++;
					gamewindow.repaint();
					break;
				default:
					
			}
		}
		if (State.getInstance().isInRoom()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_LEFT:
					gamewindow.leftPosition();
					break;
				case KeyEvent.VK_RIGHT:
					gamewindow.rightPosition();
					break;
				case KeyEvent.VK_ENTER:
					if (gamewindow.isP1) gamewindow.askstart();
					break;
				default:
					
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (State.getInstance().isStart()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_ENTER:
					//if (gamewindow.startop!=2) gamewindow.startop++;
					//State.getInstance().nextstate();
					if (gamewindow.startop==0) {
						gamewindow.create_Room();
					}else if (gamewindow.startop==1) {
						gamewindow.join_Room();
					}
					break;
				default:
			}
		}
	}
}
