package sprites;

import java.awt.Graphics;

public class Bullet extends Sprite
{
	private static int offset=21;
	int kind;
	int owen;
	public Bullet(int x,int y,int width,int height,int kind,int owen,Dir dir) {
		super(x,y,width,height,dir);
		this.kind=kind;
		this.owen=owen;
	}
	public  int shownumber() {
		return kind+offset;
	}
	
}
