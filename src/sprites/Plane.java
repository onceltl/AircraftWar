package sprites;

import java.awt.Rectangle;
import java.util.List;

import controller.SpriteController;
import controller.State;

public class Plane extends Sprite
{
	private static int offset=8;
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
				Bullet bullet=new Bullet(x+width/2-5,y+height,10,10,1,owen,new Dir(0,10));
				SpriteController.getInstance().addBullet(bullet);
		
			}else{
				Bullet bullet=new Bullet(x+width/2-5,y,10,10,1,owen,new Dir(0,-10));
				SpriteController.getInstance().addBullet(bullet);
			}
		}
	}
	public void intersectPlane(List<Plane> planes) {
		Rectangle size=getRectangle();
		for (Plane plane:planes) {
			if (size.intersects(plane.getRectangle())) {
				HP--;
				plane.HP--;
				if (plane.HP<=0) plane.islive=false;
					//boom
				islive=false;
				break;
			}
		}
	}
	public void intersectSupply(List<Supply> supplys) {
		Rectangle size=getRectangle();
		
		for (Supply supply:supplys) {
			if (supply.islive&&size.intersects(supply.getRectangle())) {
				//get
				supply.islive=false;
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
					islive=false;
					break;
				}
			}
		if (owen==0)
			for (Bullet bullet:bullets) {
				if (bullet.owen!=0&&size.intersects(bullet.getRectangle())) {
				//get
					HP--;
					bullet.islive=false;
					islive=false;
					break;
				}
			}
	}
	
}
