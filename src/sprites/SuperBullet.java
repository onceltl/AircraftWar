package sprites;

import controller.Constant;

public class SuperBullet  extends Bullet
{
	public static int superoffset=0;
	public int frame;
	public int divcount;
	public SuperBullet(int x,int y,int width,int height,int kind,int owen,Dir dir) {
		super(x,y,width,height,kind,owen,dir);
		frame=1;
		divcount=0;
	}

	public void move() {
		super.move();
		divcount=(divcount+1)%3;
		if (divcount==0)
		{
			frame=(frame+1)%Constant.getInstance().bulletframelen[kind];
			if (frame==0) frame=Constant.getInstance().bulletframelen[kind];
		}
	}
	public  int shownumber() {
		return (kind-1)*10+superoffset+frame;
	}
}
