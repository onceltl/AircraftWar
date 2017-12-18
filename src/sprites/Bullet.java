package sprites;

import java.awt.Graphics;

import controller.GameWindow;

public class Bullet extends Sprite
{
	public static int offset=0;
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
