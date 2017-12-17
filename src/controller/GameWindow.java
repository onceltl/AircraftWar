package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.DatagramSocket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connect.Client;
import connect.Listener;
import connect.Packet;
import connect.Sender;
import connect.Server;
import sprites.Dir;
import sprites.Plane;


public class GameWindow extends JPanel{
	public static int width=380;
	public static int height=600;
	public int startop=0;
	private KeyBoard keyboard;
	//private GraphicsThread  graphicsthread;
	private JFrame window;
	public JDialog createdialog,joindialog;
	private JTextField roomnumbertext,ipaddrtext,porttext;
	private JButton joinButton,createButton;  
	private Server server;
	private Listener  serverlisten,clientlisten;
	public boolean isP1;
	public Client clientp1,clientp2;
	public int positionp1,positionp2,serverp1,serverp2;
	public DatagramSocket serversocket;
	public DatagramSocket clientsocket;
	public Sender serversender;
	public SpriteThread spritethread;
	public GameWindow() {
		//窗口基本设置
		setSize(width, height);
		window=new JFrame();
		window.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width, height+35);
		window.setResizable(false);
		window.setVisible(true);
		window.setIconImage(ImageController.getInstance().icon);
		//jdialog设置
		//GridLayout layout=new GridLayout(3,1);
		joindialog=new JDialog(window,"房间",true);
		//joindialog.setLocation(100,50);  
		ipaddrtext=new JTextField("127.0.0.1",20);
		porttext=new JTextField("2000",20);
		joinButton=new JButton("加入");
		joindialog.add(ipaddrtext,BorderLayout.NORTH);	  
		joindialog.add(porttext);
		joindialog.add(joinButton,BorderLayout.SOUTH);
		joindialog.pack();
		
		createdialog=new JDialog(window,"房间",true);
		//createdialog.setLocation(100,50);  
		roomnumbertext=new JTextField("2000",20);
		createButton=new JButton("创建");
		createdialog.add(roomnumbertext);
		createdialog.add(createButton,BorderLayout.SOUTH);
		createdialog.pack();
		//按键时间监听
		 MyItemListener lis=new MyItemListener();
		 joinButton.addActionListener(lis);
		 createButton.addActionListener(lis);
		//键盘监听
		keyboard=new KeyBoard(this);
		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				keyboard.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keyboard.keyReleased(e);
			}
		});
		//渲染进程
		//graphicsthread=new GraphicsThread(this);
		//graphicsthread.run();
	}
	public void start() {
		//渲染进程
		//  graphicsthread=new GraphicsThread(this);
		//  graphicsthread.run();
	}
	public void startgame() {
		Packet packet= new Packet(clientp1.getAddress(),clientp1.getPort(),
				"Start!");
		serversender.addPacket(packet);
		if (clientp2!=null) {
			packet= new Packet(clientp2.getAddress(),clientp2.getPort(),
					"Start!");
			serversender.addPacket(packet);
		}
	
		//飞机
		SpriteController.getInstance().clear();
		Plane herop1=new Plane(100,530,70,70,serverp1,1,new Dir(0,0));
		SpriteController.getInstance().sethero1p(herop1);
		if (clientp2!=null) {
			Plane herop2=new Plane(200,530,70,70,serverp2,1,new Dir(0,0));
			SpriteController.getInstance().sethero2p(herop2);
		}
		System.out.println(SpriteController.getInstance().getinfo());
		//游戏线程：产生敌人,move,碰撞
		spritethread=new SpriteThread(this);
		spritethread.start();
	}
	public void togame() {
		State.getInstance().nextstate();
	}
	public static void main(String[] args) {
		GameWindow gamewindow=new GameWindow();
		gamewindow.start();
	}
	public void paint(Graphics g) {
		if (State.getInstance().isStart()) {
			g.drawImage(ImageController.getInstance().load, 0, 0,width,height,null);
			if (startop==0)g.drawImage(ImageController.getInstance().createroomon, 100, 380,200,100,null);
				else g.drawImage(ImageController.getInstance().createroom, 100, 380,200,100,null);
			if (startop==1)g.drawImage(ImageController.getInstance().joinroomon, 100, 440,200,100,null);
				else g.drawImage(ImageController.getInstance().joinroom, 100, 440,200,100,null);
			if (startop==2)g.drawImage(ImageController.getInstance().helpon, 100, 500,200,100,null);
				else g.drawImage(ImageController.getInstance().help, 100, 500,200,100,null);
			return;
		}
		if (State.getInstance().isInRoom()) {
			g.drawImage(ImageController.getInstance().choicewindow, -30, -30,width+60,height+50,null);
			switch(positionp1) {
				case 1:
					g.drawImage(ImageController.getInstance().choiceboard, 40, 140,120,120,null);
					g.drawImage(ImageController.getInstance().choicep1, 80, 260,40,40,null);
					
					break;
				case 2:
					g.drawImage(ImageController.getInstance().choiceboard, 213, 140,120,120,null);
					g.drawImage(ImageController.getInstance().choicep1, 253, 260,40,40,null);
					break;
				case 3:
					g.drawImage(ImageController.getInstance().choiceboard, 40, 310,120,120,null);
					g.drawImage(ImageController.getInstance().choicep1, 80, 430,40,40,null);
					break;
				case 4:
					g.drawImage(ImageController.getInstance().choiceboard, 213, 310,120,120,null);
					g.drawImage(ImageController.getInstance().choicep1, 253, 430,40,40,null);
					
					break;
				default:
					;
			}
			
			if (positionp2!=0) {
				switch(positionp2) {
				case 1:
					g.drawImage(ImageController.getInstance().choiceboard, 40, 140,120,120,null);
					g.drawImage(ImageController.getInstance().choicep2, 80, 260,40,40,null);
					
					break;
				case 2:
					g.drawImage(ImageController.getInstance().choiceboard, 213, 140,120,120,null);
					g.drawImage(ImageController.getInstance().choicep2, 253, 260,40,40,null);
					break;
				case 3:
					g.drawImage(ImageController.getInstance().choiceboard, 40, 310,120,120,null);
					g.drawImage(ImageController.getInstance().choicep2, 80, 430,40,40,null);
					break;
				case 4:
					g.drawImage(ImageController.getInstance().choiceboard, 213, 310,120,120,null);
					g.drawImage(ImageController.getInstance().choicep2, 253, 430,40,40,null);
					break;
				default:
					;
				}
			}
			g.drawImage(ImageController.getInstance().plane[1][1], 40, 140,120,120,null);
			g.drawImage(ImageController.getInstance().plane[2][1], 213, 140,120,120,null);
			g.drawImage(ImageController.getInstance().plane[3][1], 40, 310,120,120,null);
			g.drawImage(ImageController.getInstance().plane[4][1], 213, 310,120,120,null);
			
			return;
		}
		if (State.getInstance().isInGame()) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,width,height);
			SpriteShow.getInstance().paint(g);
			return;
		}
		if (State.getInstance().isInTest()) {
			g.drawImage(ImageController.getInstance().backgroud[1], 0,0,380,600,null);
			g.drawImage(ImageController.getInstance().plane[1][1], 100,530,70,70,null);
			g.drawImage(ImageController.getInstance().plane[2][1], 200,530,70,70,null);

			g.drawImage(ImageController.getInstance().enemy[1], 100,100,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[2], 200,100,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[3], 300,100,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[4], 100,200,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[5], 100,300,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[6], 200,200,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[7], 200,300,70,70,null);
			g.drawImage(ImageController.getInstance().enemy[8], 300,300,70,70,null);
		}
	}
	public void create_Room() {
		createdialog.setLocation(window.getX() + window.getWidth()/2 - createdialog.getWidth()/2, window.getY() +window.getHeight()/2 - createdialog.getHeight()/2);
		createdialog.setVisible(true);
	}
	public void join_Room() {
		joindialog.setLocation(window.getX() + window.getWidth()/2 - joindialog.getWidth()/2, window.getY() +window.getHeight()/2 - joindialog.getHeight()/2);
		joindialog.setVisible(true);
	}
	public void certain_join_room() {
		int port=Integer.parseInt(porttext.getText());
		if (port<2000||port>3000) {
			JOptionPane.showMessageDialog(this, "房间号只能从2000~3000", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.isP1=false;
		try {
			clientsocket=new DatagramSocket(Constant.getInstance().getport2p());
			clientlisten=new Listener(this);
			clientlisten.listen(clientsocket);
			server=new Server(ipaddrtext.getText(),port);
			Packet packet = new Packet(server.getAddress(),server.getPort(),"Join");
			clientsocket.send(packet.getDataPacket());
			this.positionp2=0;
			this.positionp1=0;
			State.getInstance().nextstate();
			//createdialog.setVisible(false);
			//this.repaint();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "加入房间失败", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void certain_create_room() {
		int port=Integer.parseInt(roomnumbertext.getText());
		if (port<2000||port>3000) {
			JOptionPane.showMessageDialog(this, "房间号只能从2000~3000", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//状态设计
		this.isP1=true;
		this.positionp1=1;
		this.positionp2=0;
		this.serverp1=1;
		this.serverp2=0;
		try {
			//clientp1
			
			clientsocket=new DatagramSocket(Constant.getInstance().getport1p());
			clientp1=new Client("房主","localhost",Constant.getInstance().getport1p());
			clientlisten=new Listener(this);
			clientlisten.listen(clientsocket);
			clientp2=null;
			//服务器
			serversocket=new DatagramSocket(port);
			serverlisten=new Listener(this);
			serverlisten.listen(serversocket);
			serversender=new Sender();
			serversender.start(serversocket);
			server=new Server("localhost",port);
			//改变状态
			State.getInstance().nextstate();
			createdialog.setVisible(false);
			this.repaint();
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "房间号被占用", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void askstart() {
		Packet packet = new Packet(server.getAddress(),server.getPort(),"AskStart");
		try{
			clientsocket.send(packet.getDataPacket());
		}catch(Exception e) {
			System.out.println("sendwrong");
		}
	}
	public void  leftPosition() {
		Packet packet = new Packet(server.getAddress(),server.getPort(),"MoveLFET");
		try{
			clientsocket.send(packet.getDataPacket());
		}catch(Exception e) {
			System.out.println("sendwrong");
		}
	}
	public void  rightPosition() {
		Packet packet = new Packet(server.getAddress(),server.getPort(),"MoveRIGHT");
		try{
			clientsocket.send(packet.getDataPacket());
		}catch(Exception e) {
			System.out.println("sendwrong");
		}
	}
	 private class MyItemListener implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  Object obj=e.getSource();//获得事件源
			  if (obj==joinButton) 
				  certain_join_room();
			  if (obj==createButton) 
				  certain_create_room();	
		  }
	  }
	 
	//双缓冲
	/*private Image iBuffer;
	private Graphics gBuffer;
	public void update(Graphics g)
	{
	    if(iBuffer==null)
	    {
	       iBuffer=createImage(this.getSize().width,this.getSize().height);
	       gBuffer=iBuffer.getGraphics();
	    }
	    	System.out.println("test");
	      // gBuffer.setColor(getBackground());
	       gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
	       paint(gBuffer);
	       g.drawImage(iBuffer,0,0,this);
	}*/
}
