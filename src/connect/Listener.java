package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import controller.Constant;
import controller.GameWindow;
import controller.SpriteShow;
import controller.State;


public class Listener {

	private DatagramSocket socket = null;
	private DatagramPacket receivePacket;
	private GameWindow gamewindow;
	public Listener(GameWindow gamewindow) {
		this.gamewindow=gamewindow;
	}
	public void listen(DatagramSocket mysocket) {
		this.socket=mysocket;
		Thread thread = new Thread() {
			public void moveleft() {
				if (receivePacket.getPort()==Constant.getInstance().getport1p()) {
					gamewindow.serverp1--;
					if (gamewindow.serverp1==0) gamewindow.serverp1=4;
					if (gamewindow.serverp2==gamewindow.serverp1)
					{
						gamewindow.serverp1--;
						if (gamewindow.serverp1==0) gamewindow.serverp1=4;
					}
					Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
							"P1Move,"+Integer.toString(gamewindow.serverp1));
					gamewindow.serversender.addPacket(packet);
					if (gamewindow.clientp2!=null) {
						packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
								"P1Move,"+Integer.toString(gamewindow.serverp1));
						gamewindow.serversender.addPacket(packet);
					}
										
				} else {
					gamewindow.serverp2--;
					if (gamewindow.serverp2==0) gamewindow.serverp2=4;
					if (gamewindow.serverp2==gamewindow.serverp1)
					{
						gamewindow.serverp2--;
						if (gamewindow.serverp2==0) gamewindow.serverp2=4;
					}
					Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
							"P2Move,"+Integer.toString(gamewindow.serverp2));
					gamewindow.serversender.addPacket(packet);
					
					packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
								"P2Move,"+Integer.toString(gamewindow.serverp2));
						gamewindow.serversender.addPacket(packet);
				}
			}
			public void moveright() {
				if (receivePacket.getPort()==Constant.getInstance().getport1p()) {
					gamewindow.serverp1++;
					if (gamewindow.serverp1==5) gamewindow.serverp1=1;
					if (gamewindow.serverp2==gamewindow.serverp1)
					{
						gamewindow.serverp1++;
						if (gamewindow.serverp1==5) gamewindow.serverp1=1;
					}
					Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
							"P1Move,"+Integer.toString(gamewindow.serverp1));
					gamewindow.serversender.addPacket(packet);
					if (gamewindow.clientp2!=null) {
						packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
								"P1Move,"+Integer.toString(gamewindow.serverp1));
						gamewindow.serversender.addPacket(packet);
					}
										
				} else {
					gamewindow.serverp2++;
					if (gamewindow.serverp2==5) gamewindow.serverp2=1;
					if (gamewindow.serverp2==gamewindow.serverp1)
					{
						gamewindow.serverp2++;
						if (gamewindow.serverp2==5) gamewindow.serverp2=1;
					}
					Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
							"P2Move,"+Integer.toString(gamewindow.serverp2));
					gamewindow.serversender.addPacket(packet);
					
					packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
								"P2Move,"+Integer.toString(gamewindow.serverp2));
						gamewindow.serversender.addPacket(packet);
				}
			}
			public void p1move(String positon) {
				gamewindow.positionp1=Integer.parseInt(positon);
				gamewindow.repaint();
			}
			public void p2move(String positon) {
				gamewindow.positionp2=Integer.parseInt(positon);
				gamewindow.repaint();
			}
			public void myjoin() {
				gamewindow.serverp2=1;
				if (gamewindow.serverp2==gamewindow.serverp1)
				{
					gamewindow.serverp2++;
					if (gamewindow.serverp2==5) gamewindow.serverp2=1;
				}
				Packet packet = new Packet(gamewindow.clientp1.getAddress(),gamewindow.clientp1.getPort(),
						"JoinACK,"+Integer.toString(gamewindow.serverp1)+","+Integer.toString(gamewindow.serverp2));
				gamewindow.serversender.addPacket(packet);
				
				packet = new Packet(gamewindow.clientp2.getAddress(),gamewindow.clientp2.getPort(),
						"JoinACK,"+Integer.toString(gamewindow.serverp1)+","+Integer.toString(gamewindow.serverp2));
				gamewindow.serversender.addPacket(packet);
			}
			public void joinack(String p1,String p2) {
				if (!gamewindow.isP1) gamewindow.joindialog.setVisible(false);
				gamewindow.positionp1=Integer.parseInt(p1);
				gamewindow.positionp2=Integer.parseInt(p2);
				gamewindow.repaint();
			}
			public void show(String[] datas) {
				SpriteShow.getInstance().clear();
				int score=Integer.parseInt(datas[1]);
				int n=Integer.parseInt(datas[2]);
				
				SpriteShow.getInstance().setscore(score);
				for (int i=0;i<n;i++)
				{
					int x=Integer.parseInt(datas[5*i+3]);
					int y=Integer.parseInt(datas[5*i+4]);
					int width=Integer.parseInt(datas[5*i+5]);
					int height=Integer.parseInt(datas[5*i+6]);
					int imagenumber=Integer.parseInt(datas[5*i+7]);
					SpriteShow.getInstance().addshow(x, y, width, height, imagenumber);
				}
				gamewindow.repaint();
			}
			public void run() {
				try {
					byte[] buffer = new byte[65536*16];
					receivePacket = new DatagramPacket(buffer, buffer.length);
					while (true) {
						socket.receive(receivePacket);
						byte[] data = receivePacket.getData();
						String tmp = new String(data, 0, receivePacket.getLength());
						System.out.println("Receive: " + tmp); 
						String[] datas = tmp.split(",");

						switch (datas[0]) {
						//server
						case "Join":
							String ip=receivePacket.getAddress().toString();
							ip = ip.substring(ip.lastIndexOf("/") + 1 );
							
							gamewindow.clientp2=new Client("��Ա",ip,
									receivePacket.getPort());
							
							myjoin();
							break;
						case "MoveLFET":
							moveleft();
							break;
						case "MoveRIGHT":
							moveright();
							break;
						case "AskStart":
							gamewindow.startgame();
							break;
						//
						//client
						case "P1Move":
							p1move(datas[1]);
							break;
						case "P2Move":
							p2move(datas[1]);
							break;
						case "JoinACK":
							joinack(datas[1],datas[2]);
							//System.out.println("right"+datas[1]+" "+datas[2]);
							break;
						case "GAME":
							show(datas);
							break;
						case "Start!":
							gamewindow.togame();
							break;
						default:
							System.out.println(socket.getPort()+"Bad data: " +tmp);
						}

						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	public void close() {
		socket.close();
	}
}