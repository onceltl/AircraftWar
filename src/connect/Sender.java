package connect;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;


public class Sender {
	private DatagramSocket socket = null;
    public Sender(DatagramSocket mysocket) {
    	this.socket=mysocket;
    }
    
    public void addPacket(Packet packet) {
    	try
    	{
    		socket.send(packet.getDataPacket());
    	}catch (IOException e) {
            e.printStackTrace();
        }
    }
}