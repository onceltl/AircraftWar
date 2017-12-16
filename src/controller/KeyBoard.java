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
					break;
				case KeyEvent.VK_DOWN:
					if (gamewindow.startop!=2) gamewindow.startop++;
					break;
				default:
					
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (State.getInstance().isStart()) {
			int key = e.getKeyCode();
			System.out.println("!"+key+" "+KeyEvent.VK_UP);
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
