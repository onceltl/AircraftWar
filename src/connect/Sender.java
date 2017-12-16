package connect;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;


public class Sender {
	private DatagramSocket socket = null;
    private ArrayList<Packet> packets = new ArrayList<>();
    private int port;
    
    public Sender(int port) {
    	this.port = port;
    }
    
    public void addPacket(Packet packet) {
    	packets.add(packet);
    }
    
    public void start() {
    	Thread thread = new Thread() {
            public void run() {
                try {
                    socket = new DatagramSocket(port);
                    while (true) {
                    	if (packets.size() > 0) {
                    		socket.send(packets.get(0).getDataPacket());
                    		System.out.println(packets.get(0).getClientAddress());
                    		System.out.println(packets.get(0).getClientPort());
                    		System.out.println("Send server" + packets.get(0).getData());
                    		packets.remove(0);
                    	}
                    	
                    	try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
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
    
    public  void close() {
    	socket.close();
    }
}