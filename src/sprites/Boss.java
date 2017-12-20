package sprites;

import java.util.Random;

import controller.SpriteController;

public class Boss extends Plane{
	public static int bossoffset=0;
	public static Random bossrandom=new Random();
	private int frame;
	private int count;
	public Boss(int x,int y,int width,int height,int kind,int owen,Dir dir) {
		super(x,y,width,height,20,owen,dir);
		frame=1;
		count=0;
		firecount=80;
		HP=30;
	}
	public void fire() {
		firecount++;
		if (firecount==80) {
			SuperBullet bullet=new SuperBullet(x+width/2-30,y+height-60,70,70,5,owen,new Dir(0,5));
			SpriteController.getInstance().addBullet(bullet);
			SpriteController.getInstance().sounds.add("BossBullet.mp3");
		}
		if (firecount==120) {
			firecount=0;
			SuperBullet bullet=new SuperBullet(x+width/2+70,y+height-80,70,70,5,owen,new Dir(0,5));
			SpriteController.getInstance().addBullet(bullet);
			bullet=new SuperBullet(x+30,y+height-80,70,70,5,owen,new Dir(0,5));
			SpriteController.getInstance().addBullet(bullet);
			SpriteController.getInstance().sounds.add("BossBullet.mp3");
		}
		
	}
	public void move() {
		if (bossrandom.nextInt(10)==0) {
			if (bossrandom.nextInt(2)==0)x+=bossrandom.nextInt(20);
				else x-=bossrandom.nextInt(20);
		}
		if (bossrandom.nextInt(10)==0)
			y+=bossrandom.nextInt(10);
		count=(count+1)%10;
		if (count==0)
		{
			frame=(frame+1)%12;
			if (frame==0) frame=12;
		}
	}
	public int shownumber() {
		return bossoffset+frame;
	}
}
