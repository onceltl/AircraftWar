package controller;

public class Constant {
	private static Constant INSTANCE = new Constant();
	private int port1p=10085;
	private int port2p=10086;
	public static int maxbulletframelen=10;
	public static int []bulletframelen= {0,10,6,8,4,10};
	public static int []staticframelen= {0,12,8,6,4,10};
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