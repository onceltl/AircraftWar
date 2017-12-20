package sprites;

import java.awt.Rectangle;
import java.util.List;

import controller.GameWindow;
import controller.SpriteController;

public class HeroPlane  extends Plane{
	public static int updateoffset=0;
	public static int superfiretime=500;
	public static int herooffset=0;
	public static int superoffset=0;
	public static int updatetime=500;
	
	//private int keyboardmove;//L,R,U,D
	public int superfire;
	public int isupdate;
	public boolean isfire;
	public boolean isleftmove,isrightmove,isupmove,isdownmove;
	public HeroPlane(int x,int y,int width,int height,int kind,int owen,Dir dir){
		super(x,y,width,height,kind,owen,dir);
		//keyboardmove=0;
		superfire=0;
		isupdate=0;
		isfire=false;
		isleftmove=isrightmove=isupmove=isdownmove=false;
	}
	public void superFire() {
		superfire=superfiretime;
	}
	public synchronized  void setFire() {
		isfire=true;
		firecount=40-1;
		
	}
	public synchronized  void closeFire() {
		isfire=false;
	}
	public  void setupdate() {
		isupdate=updatetime;
	}
	public synchronized void setpressdmove(int dir) {
		if (dir==1) isleftmove=true;
		if (dir==2) isrightmove=true;
		if (dir==3) isupmove=true;
		if (dir==4) isdownmove=true;
	}
	public synchronized void setreleasedmove(int dir) {
		if (dir==1) isleftmove=false;
		if (dir==2) isrightmove=false;
		if (dir==3) isupmove=false;
		if (dir==4) isdownmove=false;
	}
	public int shownumber() {
		int number=herooffset+(kind-1)*4+1;
		if (superfire>0) return number+1;
		if (isleftmove&&!isrightmove) return number+2;
		if (!isleftmove&&isrightmove) return number+3;
		return number;
	}
	public void move() {
		if (superfire>0) {
			superfire--;
			if (superfire==0)
				SpriteController.getInstance().sounds.add("gamebgm.mp3");
			
		}
		if (isupdate>0) {
			isupdate--;
		}
		if (isleftmove){x-=5;}
		if (isrightmove){x+=5;}
		if (isupmove){y-=5;}
		if (isdownmove){y+=5;}
		
		if (x<0)x=0;
		if (x+width>GameWindow.width) x=GameWindow.width-width;
		if (y<0)y=0;
		if (y+height>GameWindow.height) y=GameWindow.height-height;
	}
	public String getinfo() {
		String str="";
		if (superfire>0) str+=(x-20)+","+(-50)+","+(width+40)+","+(y+100)+","+(superoffset+superfire%10+1)+",";
		if (isupdate>0) str+=(x-45)+","+(y-30)+","+(width+90)+","+(height+90)+","+(kind+updateoffset)+",";
		str+=x+","+y+","+width+","+height+","+shownumber();
		return str;
	}
	public Rectangle getRectangle() {
		return new Rectangle(x+3,y+20,width-6,height-20);
	}
	public void fire() {
		if (!isfire) return;
		if(isupdate>0) {
			firecount++;
			if (firecount==40) {
				firecount=0;
				SpriteController.getInstance().sounds.add("fire.mp3");
				
				if (kind==1)
				{
					SuperBullet bullet=new SuperBullet(x,y-50,70,70,kind,owen,new Dir(0,-4));
					SpriteController.getInstance().addBullet(bullet);
					bullet=new SuperBullet(x-20,y-20,70,70,kind,owen,new Dir(-2,-2));
					SpriteController.getInstance().addBullet(bullet);
					bullet=new SuperBullet(x+20,y-20,70,70,kind,owen,new Dir(2,-2));
					SpriteController.getInstance().addBullet(bullet);
				}
				if (kind==2)
				{
					SuperBullet bullet=new SuperBullet(x,y-50,40,100,kind,owen,new Dir(0,-6));
					SpriteController.getInstance().addBullet(bullet);
					bullet=new SuperBullet(x+20,y-50,40,100,kind,owen,new Dir(0,-6));
					SpriteController.getInstance().addBullet(bullet);
					bullet=new SuperBullet(x+40,y-50,40,100,kind,owen,new Dir(0,-6));
					SpriteController.getInstance().addBullet(bullet);
				}
				if (kind==3)
				{
					SuperBullet bullet=new SuperBullet(x-10,y-80,50,100,kind,owen,new Dir(0,-4));
					SpriteController.getInstance().addBullet(bullet);
					bullet=new SuperBullet(x+30,y-80,50,100,kind,owen,new Dir(0,-4));
					SpriteController.getInstance().addBullet(bullet);
				}
				if (kind==4)
				{
					SuperBullet bullet=new SuperBullet(x-8,y-80,80,150,kind,owen,new Dir(0,-3));
					SpriteController.getInstance().addBullet(bullet);
				}
			}
			return;
		}
		firecount++;
		if (firecount==40) {
			firecount=0;
			SpriteController.getInstance().sounds.add("fire.mp3");
			
			Bullet bullet=new Bullet(x+width/2-5,y,10,10,1,owen,new Dir(0,-10));
			SpriteController.getInstance().addBullet(bullet);
			
		}
	}
	public void intersectPlane(List<Plane> planes) {
		Rectangle size=getRectangle();
		if (superfire>0) {
			Rectangle superfiresize=new Rectangle(x-20,0,width,y);
			for (Plane plane:planes) {
				if (plane.islive&&superfiresize.intersects(plane.getRectangle())) {
					plane.HP--;
					if (plane.HP<=0) 
						{
							plane.islive=false;
							SpriteController.getInstance().score++;
						}
					FrameSprite boom=new FrameSprite(plane.x+plane.width/2,plane.y+plane.height,70,70,1,new Dir(0,0));
					SpriteController.getInstance().addFrameSprite(boom);
					if (!(plane instanceof Boss))SpriteController.getInstance().sounds.add("boom.mp3");
					
				}
			}
		}
		for (Plane plane:planes) {
			if (plane.islive&&size.intersects(plane.getRectangle())) {
				HP--;
				plane.HP--;
				if (plane.HP<=0) 
					{
						plane.islive=false;
						if (plane.kind==20) SpriteController.getInstance().score+=10;
						else if (plane.kind>10) SpriteController.getInstance().score+=2;
						else  SpriteController.getInstance().score++;
					
						
					}
				FrameSprite boom=new FrameSprite(plane.x+plane.width/2,plane.y+plane.height,70,70,1,new Dir(0,0));
				SpriteController.getInstance().addFrameSprite(boom);
				boom=new FrameSprite(x,y,width,height,1,new Dir(0,0));
				SpriteController.getInstance().addFrameSprite(boom);
				SpriteController.getInstance().sounds.add("boom.mp3");
				islive=false;
				break;
			}
		}
	}
	public void intersectSupply(List<Supply> supplys) {
		Rectangle size=getRectangle();
		for (Supply supply:supplys) {
			if (supply.islive&&size.intersects(supply.getRectangle())) {
				if (supply.kind==1) superfire+=superfiretime;
				if (supply.kind==2) isupdate+=updatetime;
				supply.islive=false;
				FrameSprite menu=new FrameSprite(x,y,80,25,supply.kind+2,new Dir(0,2));
				SpriteController.getInstance().addFrameSprite(menu);
				SpriteController.getInstance().sounds.add("powerUp.mp3");
				if (supply.kind==1) SpriteController.getInstance().sounds.add("superfire.mp3");
			}
		}
	}
}
