package controller;

import java.awt.event.KeyEvent;

public class KeyBoard {
	public GameWindow gamewindow;
	public boolean ispressdup,ispressddown,ispressdleft,ispressdright,ispressdspace;
	public KeyBoard(GameWindow gamewindow) {
		this.gamewindow=gamewindow;
		ispressdup=ispressddown=ispressdleft=ispressdright=ispressdspace=false;
	}
	public void keyPressed(KeyEvent e) {
		if (State.getInstance().isInEnd()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_ESCAPE:
					gamewindow.restart();
				break;
				default:
					
			}
		}
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
		if (State.getInstance().isInGame()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_LEFT:
					if (!this.ispressdleft) {
						gamewindow.pressedleft();
						this.ispressdleft=true;
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!this.ispressdright) {
						gamewindow.pressedright();
						this.ispressdright=true;
					}
					break;
				case KeyEvent.VK_UP:
					if (!this.ispressdup) {
						gamewindow.pressedup();
						this.ispressdup=true;
					}
					break;
				case KeyEvent.VK_DOWN:
					if (!this.ispressddown) {
						gamewindow.presseddown();
						this.ispressddown=true;
					}
					break;
				case KeyEvent.VK_SPACE:
					if (!this.ispressdspace) {
						gamewindow.pressedspace();
						this.ispressdspace=true;
					}
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
		if (State.getInstance().isInGame()) {
			int key = e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_LEFT:
					gamewindow.releasedleft();
					this.ispressdleft=false;
					break;
				case KeyEvent.VK_RIGHT:
					gamewindow.releasedright();
					this.ispressdright=false;
					break;
				case KeyEvent.VK_UP:
					gamewindow.releasedup();
					this.ispressdup=false;
					break;
				case KeyEvent.VK_DOWN:
					gamewindow.releaseddown();
					this.ispressddown=false;
					break;
				case KeyEvent.VK_SPACE:
					gamewindow.releasedspace();
					this.ispressdspace=false;
					break;
				default:
					
			}
		}
	}
}
