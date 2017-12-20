package sprites;

import java.awt.Rectangle;
import java.util.List;

import controller.GameWindow;
import controller.SpriteController;
import controller.State;

public class Plane extends Sprite
{
	public static int offset=0;
	int kind;
	int HP;
	int firecount;
	int owen;
	public Plane(int x,int y,int width,int height,int kind,int owen,Dir dir) {
		super(x,y,width,height,dir);
		this.kind=kind;
		this.owen=owen;
		HP=1;
		if (kind>10) HP=2;
		firecount=0; 	
	}
	public int shownumber() {
		if (owen==0) return kind+offset;
		return kind;
	}
	public void move() {
		if (kind==13) {
			HeroPlane hero=SpriteController.getInstance().hero1p;
			if (hero==null) hero=SpriteController.getInstance().hero2p;
			if (hero.x>x) x+=5;
				else if (hero.x<x) x-=5;
			y+=dir.y;
			return;
		}
		if (kind>10) {
			if (dir.x==0||x<=0||x+width>=GameWindow.width) {
			HeroPlane hero=SpriteController.getInstance().hero1p;
			if (hero==null) hero=SpriteController.getInstance().hero2p;
			if (hero.x>x) dir.x=5;
				else if (hero.x<x) dir.x=-5;
			}
		}
		x+=dir.x;
		y+=dir.y;

		if (x+width<0||x>GameWindow.width||y+height<0||y>GameWindow.height)
			islive=false;
	}
	public void fire() {
		firecount++;
		if (firecount==50&&kind<=10) {
			firecount=0;
			Bullet bullet=new Bullet(x+width/2-10,y+height,20,30,kind%8+1,owen,new Dir(0,5));
				SpriteController.getInstance().addBullet(bullet);
		}
	}
	public void intersectBullet(List<Bullet> bullets) {
		Rectangle size=getRectangle();
		if (owen!=0)
			for (Bullet bullet:bullets) {
				if (bullet.owen==0&&size.intersects(bullet.getRectangle())) {
				//get
					HP--;
					bullet.islive=false;
					FrameSprite boom;
					if (HP==0)
					{
							islive=false;
							boom=new FrameSprite(x,y,width,height,1,new Dir(0,0));
							SpriteController.getInstance().addFrameSprite(boom);
							SpriteController.getInstance().sounds.add("boom.mp3");
							
					} else {
						boom=new FrameSprite(bullet.x,bullet.y,70,70,1,new Dir(0,0));
						SpriteController.getInstance().addFrameSprite(boom);
						SpriteController.getInstance().sounds.add("boom.mp3");
						
					}
					break;
				}
			}
		if (owen==0)
			for (Bullet bullet:bullets) {
				if (bullet.owen!=0&&size.intersects(bullet.getRectangle())) {
				//get
					HP--;
					bullet.islive=false;
					FrameSprite boom;
					if (HP==0)
					{
							if (kind==20) SpriteController.getInstance().score+=10;
								else if (kind>10) SpriteController.getInstance().score+=2;
								else  SpriteController.getInstance().score++;
							islive=false;
							boom=new FrameSprite(x,y,width,height,1,new Dir(0,0));
							SpriteController.getInstance().addFrameSprite(boom);
							SpriteController.getInstance().sounds.add("boom.mp3");
							
					} else {
						boom=new FrameSprite(bullet.x,bullet.y,70,70,1,new Dir(0,0));
						SpriteController.getInstance().addFrameSprite(boom);
						SpriteController.getInstance().sounds.add("boom.mp3");
						
					}
					break;
				}
			}
	}
	
}
