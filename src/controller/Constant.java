package controller;

public class Constant {
	private static Constant INSTANCE = new Constant();
	private int port1p=10085;
	private int port2p=10086;
	
	private Constant(){
	}
	public int  getport1p() {
		return port1p;
	}
	public int  getport2p() {
		return port2p;
	}
	public static Constant getInstance() {
		return INSTANCE;
	}
}