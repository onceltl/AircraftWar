package sprites;

import java.awt.Rectangle;
import java.util.List;

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
	public void fire() {
		firecount++;
		if (firecount==100) {
			firecount=0;
			if (owen==0){
				Bullet bullet=new Bullet(x+width/2-10,y+height,20,30,kind%8+1,owen,new Dir(0,3));
				SpriteController.getInstance().addBullet(bullet);
		
			}else{
				Bullet bullet=new Bullet(x+width/2-10,y,20,30,kind,owen,new Dir(0,-10));
				SpriteController.getInstance().addBullet(bullet);
			}
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
					} else {
						boom=new FrameSprite(bullet.x,bullet.y,70,70,1,new Dir(0,0));
						SpriteController.getInstance().addFrameSprite(boom);
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
							SpriteController.getInstance().score++;
							islive=false;
							boom=new FrameSprite(x,y,width,height,1,new Dir(0,0));
							SpriteController.getInstance().addFrameSprite(boom);
					} else {
						boom=new FrameSprite(bullet.x,bullet.y,70,70,1,new Dir(0,0));
						SpriteController.getInstance().addFrameSprite(boom);
					}
					break;
				}
			}
	}
	
}
