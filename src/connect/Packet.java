package connect;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Packet {
	private InetAddress clientAddress;
	private int clientPort;
	private String data;
	
	public Packet(InetAddress add, int port, String data) {
		this.clientAddress = add;
		this.clientPort = port;
		this.data = data;
	}
	
	public InetAddress getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(InetAddress clientAdress) {
		this.clientAddress = clientAdress;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public DatagramPacket getDataPacket() {

        byte[] sendData = data.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        return sendPacket;
	}
	
	
}

