package sprites;

public class Supply extends Sprite
{
	int kind;
	public static int supplyoffset=0;
	public Supply(int x,int y,int width,int height,int kind,Dir dir) {
		super(x,y,width,height,dir);
		this.kind=kind;
	}
	public  int shownumber() {
		return kind+supplyoffset;
	}	
}

