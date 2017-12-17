package controller;

import connect.Packet;

public class SpriteThread extends Thread{
	private GameWindow gamewindow;
	public SpriteThread(GameWindow gamewindow) {
		this.gamewindow=gamewindow;
	}
	public void run() {
		while(true) {
			try {
				SpriteController.getInstance().step();
				String str=SpriteController.getInstance().getinfo();
				//System.out.println(str);
				Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
						str);
				gamewindow.serversender.addPacket(packet);
				if (gamewindow.clientp2!=null) {
					packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
							str);
					gamewindow.serversender.addPacket(packet);
				}
				sleep(20);
			}catch(Exception e) {
			}
		}
	}
}
