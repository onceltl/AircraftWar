package controller;

public class GraphicsThread extends Thread{
	private GameWindow gamewindow;
	public GraphicsThread(GameWindow gamewindow) {
		this.gamewindow=gamewindow;
	}
	public void run() {
		while(true) {
			try {
				gamewindow.repaint();
				sleep(10);
			}catch(Exception e) {
				
			}
		}
	}
}
