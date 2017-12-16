package controller;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connect.Client;
import connect.Listener;
import connect.Sender;
import connect.Server;


public class GameWindow extends JPanel{
	public static int width=380;
	public static int height=600;
	public int startop=0;
	private KeyBoard keyboard;
	private GraphicsThread  graphicsthread;
	private JFrame window;
	private JDialog createdialog,joindialog;
	private JTextField roomnumbertext,ipaddrtext,porttext;
	private JButton joinButton,createButton;  
	private Server server;
	private Client clientp1,clientp2;
	private Sender serversender;
	private Listener  serverlisten,clientlisten;
	public GameWindow() {
		//窗口基本设置
		setSize(width, height);
		window=new JFrame();
		window.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width, height+35);
		window.setResizable(false);
		window.setVisible(true);
		//jdialog设置
		//GridLayout layout=new GridLayout(3,1);
		joindialog=new JDialog(window,"房间",true);
		//joindialog.setLocation(100,50);  
		ipaddrtext=new JTextField("127.0.0.1",20);
		porttext=new JTextField("10086",20);
		joinButton=new JButton("加入");
		joindialog.add(ipaddrtext,BorderLayout.NORTH);	  
		joindialog.add(porttext);
		joindialog.add(joinButton,BorderLayout.SOUTH);
		joindialog.pack();
		
		createdialog=new JDialog(window,"房间",true);
		//createdialog.setLocation(100,50);  
		roomnumbertext=new JTextField("10085",20);
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
		  graphicsthread=new GraphicsThread(this);
		  graphicsthread.run();
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
			return;
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
		System.out.println(ipaddrtext.getText()+" "+porttext.getText());
	}
	public void certain_create_room() {
		int port=Integer.parseInt(roomnumbertext.getText());
		if (port<2000||port>3000) {
			JOptionPane.showMessageDialog(this, "房间号只能从2000~3000", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//clientp1
		clientp1=new Client("房主","localhost","10085");
		clientlisten=new Listener();
		clientlisten.listen(10085);
		//服务器
		serverlisten=new Listener();
		serverlisten.listen(port);
		serversender=new Sender(port);
		serversender.start();
		State.getInstance().nextstate();
		createdialog.setVisible(false);
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
