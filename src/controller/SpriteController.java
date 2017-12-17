package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import sprites.*;
public class SpriteController {
	private List<Bullet> bullets;
	private List<Plane> planes;
	private List<Supply> supplys;
	private Plane hero1p,hero2p;
	private int score;
	private static SpriteController INSTANCE = new SpriteController();
	private static int already=100;
	private static int startx=100;
	private static int starty=-70;
	private static int planewidth=70;
	private static int planeheight=70;
	private int backgroundkind;
	private int backy1,backy2;
	private Random random;
	private int producecount;
	private SpriteController() {
		bullets=new ArrayList<Bullet>();
		planes=new ArrayList<Plane>();
		supplys=new ArrayList<Supply>();
		random=new Random();
		producecount=0;
	}
	public void clear() {
		bullets.clear();
		planes.clear();
		supplys.clear();
		hero1p=null;
		hero2p=null;
		score=0;
		backgroundkind=random.nextInt(4);
		backy1=0;
		backy2=-GameWindow.height;
		producecount=0;
	}
	public void addSupply(Supply supply) {
		supplys.add(supply);
	}
	public void sethero1p(Plane plane) {
		this.hero1p=plane;
	}
	public void sethero2p(Plane plane) {
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
		producecount++;
		if (producecount==already) {
			producecount=0;
			Plane plane=new Plane(random.nextInt(GameWindow.width),starty,planewidth,planeheight,
					random.nextInt(13)+1,0,new Dir(0,1));
			planes.add(plane);
		}
	}
	public void move() {
		backy1+=1;
		if (backy1>=GameWindow.height) backy1=-GameWindow.height;
		backy2+=1;
		if (backy2>=GameWindow.height) backy2=-GameWindow.height;
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
			hero1p.intersectSupply(supplys);
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
			if (!plane.islive) it.remove();
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
	}
	public String getinfo() {
		StringBuffer info = new StringBuffer();
		info.append("GAME,"+score);
		int n=bullets.size()+planes.size()+supplys.size()+2;
		if (hero1p!=null) n++;
		if (hero2p!=null) n++;
		info.append(","+n);
		info.append(","+"0,"+backy1+","+GameWindow.width+","+GameWindow.height+","+(backgroundkind+5));
		info.append(","+"0,"+backy2+","+GameWindow.width+","+GameWindow.height+","+(backgroundkind+5));
		for (Bullet e:bullets) info.append(","+e.getinfo());
		for (Plane e:planes) info.append(","+e.getinfo());
		for (Supply e:supplys) info.append(","+e.getinfo());
		if (hero1p!=null) info.append(","+hero1p.getinfo());
		if (hero2p!=null) info.append(","+hero2p.getinfo());
		return info.toString();
	}
	public void step() {
		produce();
		move();
		intersect();
	}
}
