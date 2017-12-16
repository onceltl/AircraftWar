package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Listener {

	private DatagramSocket socket = null;
	private DatagramPacket receivePacket;

	public void listen(int listenport) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new DatagramSocket(listenport);
					byte[] buffer = new byte[1024];
					receivePacket = new DatagramPacket(buffer, buffer.length);
					while (true) {
						socket.receive(receivePacket);
						byte[] data = receivePacket.getData();
						String tmp = new String(data, 0, receivePacket.getLength());
						System.out.println("Receive: " + tmp); 
						String[] datas = tmp.split(",");

						switch (datas[0]) {
						case "JOIN":
							//Client c = new Client(datas[2], receivePacket.getAddress(), receivePacket.getPort() - 1);
							//RoomController.getInstance().getRooms().get(Integer.parseInt(datas[1])).addClient(c);
							Packet packet2 = new Packet(receivePacket.getAddress(), receivePacket.getPort() - 1,
									"JOIN,SUCCESS");
							//RoomController.getInstance().getResponse().addPacket(packet2);
							break;
						default:
							System.out.println("Bad data: " + tmp);
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