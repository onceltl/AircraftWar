package sprites;

import controller.Constant;

public class FrameSprite  extends Sprite
{
	public  static int framespriteoffset=0;
	private int oneframecounter;
	private static int  nextframe=10;
	private int frame;
	private int kind;
	public FrameSprite(int x,int y,int width,int height,int kind,Dir dir) {
		super(x,y,width,height,dir);
		this.kind=kind;
		oneframecounter=0;
		frame=1;
	}
	public void move() {
		super.move();
		oneframecounter++;
		if (oneframecounter==nextframe) {
			oneframecounter=0;
			frame++;
			if (frame==Constant.getInstance().staticframelen[kind]) islive=false;
		}
	}
	public  int shownumber() {
		int number=framespriteoffset;
		if (kind==1) {
			return number+frame;
		}
		
		number+=Constant.getInstance().staticframelen[1];
		return number+=kind-1;
	}
}
