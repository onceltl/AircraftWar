package connect;

public class Server {
	private String address;
	private String port;
	
	public Server(String address, String port) {
		this.address = address;
		this.port = port;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
