package connect;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Packet {
	private String address;
	private int port;
	private String data;
	
	public Packet(String add, int port, String data) {
		this.address = add;
		this.port = port;
		this.data = data;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public DatagramPacket getDataPacket() {

        byte[] sendData = data.getBytes();
        try {
        	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(address), port);
        	return sendPacket;
        }catch(Exception e) {
        	
        }
        return null;
     }
	
	
}

