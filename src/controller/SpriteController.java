package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.*;
public class SpriteController {
	private List<Bullet> bullets;
	private List<Plane> planes;
	private List<Supply> supplys;
	private List<FrameSprite> framesprites;
	public HeroPlane hero1p,hero2p;
	private static SpriteController INSTANCE = new SpriteController();
	private static int starty=-70;
	private static int planewidth=70;
	private static int planeheight=70;
	private int backgroundkind;
	private int backy1,backy2;
	private Random random;
	public int score;
	
	private SpriteController() {
		bullets=new ArrayList<Bullet>();
		planes=new ArrayList<Plane>();
		supplys=new ArrayList<Supply>();
		framesprites=new ArrayList<FrameSprite>();
		random=new Random();
	}
	public void clear() {
		bullets.clear();
		planes.clear();
		supplys.clear();
		framesprites.clear();
		hero1p=null;
		hero2p=null;
		score=0;
		backgroundkind=random.nextInt(4)+1;
		backy1=0;
		backy2=-GameWindow.height;
	}
	public void addFrameSprite(FrameSprite frameSprite) {
		framesprites.add(frameSprite);
	}
	public void addSupply(Supply supply) {
		supplys.add(supply);
	}
	public void sethero1p(HeroPlane plane) {
		this.hero1p=plane;
	}
	public void sethero2p(HeroPlane plane) {
		this.hero2p=plane;
	}
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	public void addPlane(Plane plane) {
		planes.add(plane);
	}
	public static SpriteController getInstance() {
		return INSTANCE;
	}
	public void produce() {
		if (random.nextInt(10000)==0){
			Boss boss=new Boss(random.nextInt(GameWindow.width/2),starty,300,300,1,0,new Dir(0,0));
			planes.add(boss);
		}
		if (random.nextInt(100)==0) {
			Plane plane=new Plane(random.nextInt(GameWindow.width),starty,planewidth,planeheight,
					random.nextInt(13)+1,0,new Dir(0,4));
			planes.add(plane);
		}
		if (random.nextInt(1000)==0) {
			int bulletkind=random.nextInt(4)+9;
			int w=0,h=0;
			int speed=0;
			if (bulletkind==9) {
				w=100;
				h=100;
				speed=2;
			}
			if (bulletkind==10) {
				w=100;
				h=100;

				speed=2;
			}
			if (bulletkind==11) {
				w=40;
				h=80;

				speed=5;
			}
			if (bulletkind==12) {
				w=30;
				h=80;

				speed=5;
			}
			Bullet bullet=new Bullet(random.nextInt(GameWindow.width),starty,w,h,
					bulletkind,0,new Dir(0,speed));
			bullets.add(bullet);
		}
		if (random.nextInt(1000)==0) 
		{
			Supply supply=new Supply(random.nextInt(GameWindow.width),-30,30,30,
					random.nextInt(2)+1,new Dir(0,5));
			supplys.add(supply);
		}
	}
	public void move() {
		backy1+=1;
		if (backy1>=GameWindow.height) backy1=-GameWindow.height;
		backy2+=1;
		if (backy2>=GameWindow.height) backy2=-GameWindow.height;
		for (FrameSprite e:framesprites)
			e.move();
		for (Bullet e:bullets)
			e.move();
		for (Plane e:planes)
			e.move();
		for (Supply e:supplys)
			e.move();
		for (Plane e:planes)
			e.fire();
		if (hero1p!=null) { 
			hero1p.move();
			hero1p.fire();
			
		}
		if (hero2p!=null) {
			hero2p.move();
			hero2p.fire();
		}
	}
	public void intersect() {
		if (hero1p!=null) {
			hero1p.intersectPlane(planes);
			hero1p.intersectBullet(bullets);
			hero1p.intersectSupply(supplys);
		}
		if (hero2p!=null) {
			hero2p.intersectPlane(planes);
			hero2p.intersectBullet(bullets);
			hero2p.intersectSupply(supplys);
		}
		for (Plane e:planes)
			e.intersectBullet(bullets);
		if (hero1p!=null&&!hero1p.islive) {
			hero1p=null;
		}
		if (hero2p!=null&&!hero2p.islive) {
			hero2p=null;
		}
		if (hero2p==null&&hero1p==null) {
			//gameover
		}
		Iterator it=planes.iterator();
		while (it.hasNext()) {
			Plane plane=(Plane)it.next();
			if (!plane.islive) 
				{
					it.remove();
				}
		}
		it=bullets.iterator();
		while (it.hasNext()) {
			Bullet bullet=(Bullet)it.next();
			if (!bullet.islive) it.remove();
		}
		
		it=supplys.iterator();
		
		
		while (it.hasNext()) {
			Supply supply=(Supply)it.next();
			if (!supply.islive) it.remove();
		}
		it=framesprites.iterator();
		while (it.hasNext()) {
			FrameSprite framesprite=(FrameSprite)it.next();
			if (!framesprite.islive) it.remove();
		}
	}
	public String getinfo() {
		if (hero1p==null&&hero2p==null) {
			int []sendscore=new int[20];
			StringBuffer str=new StringBuffer(","+score);
			
			try {
				File file=new File(this.getClass().getResource("/scorerank.txt").toURI());
				FileInputStream in=new FileInputStream(file);
				System.setIn(in);
				Scanner scan=new Scanner(System.in);
				
				sendscore[0]=score;
				for (int i=1;i<=10;i++)
					sendscore[i]=scan.nextInt();
				Arrays.sort(sendscore, 0,11);
				scan.close();
				in.close();
				PrintStream ps=new PrintStream(new FileOutputStream(file));
				for (int i=10;i>0;i--) {
					ps.println(sendscore[i]);
					str.append(","+sendscore[i]);
				}
				ps.close();
			}catch(Exception e) {
				System.out.println("No file");
			}
			return "GameOver!"
					+","+"0,"+backy1+","+GameWindow.width+","+GameWindow.height+","+backgroundkind
					+","+"0,"+backy2+","+GameWindow.width+","+GameWindow.height+","+backgroundkind
					+str;
		}
		StringBuffer info = new StringBuffer();
		info.append("GAME,"+score);
		int n=bullets.size()+planes.size()+supplys.size()+framesprites.size()+2;
		if (hero1p!=null){
				n++;
				if (hero1p.superfire>0)n++;
				if (hero1p.isupdate)n++;
			}
		if (hero2p!=null){
			n++;
			if (hero2p.superfire>0)n++;
			if (hero2p.isupdate)n++;
		}
		info.append(","+n);
		info.append(","+"0,"+backy1+","+GameWindow.width+","+GameWindow.height+","+backgroundkind);
		info.append(","+"0,"+backy2+","+GameWindow.width+","+GameWindow.height+","+backgroundkind);
		for (Plane e:planes) info.append(","+e.getinfo());
		for (Supply e:supplys) info.append(","+e.getinfo());
		for (FrameSprite e:framesprites) info.append(","+e.getinfo());
		if (hero1p!=null) info.append(","+hero1p.getinfo());
		if (hero2p!=null) info.append(","+hero2p.getinfo());
		for (Bullet e:bullets) info.append(","+e.getinfo());
		return info.toString();
	}
	public void step() {
		produce();
		move();
		intersect();
	}
}
