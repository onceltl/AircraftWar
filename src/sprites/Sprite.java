package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;

import controller.GameWindow;

public abstract class Sprite {
	protected int x,y;
	protected int width,height;
	public boolean islive;
	protected Dir dir;
	public Sprite(int x,int y,int width,int height,Dir dir) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.dir=dir;
		islive=true;
	}
	public void move() {
		x+=dir.x;
		y+=dir.y;
		if (x+width<0||x>GameWindow.width||y+height<0||y>GameWindow.height)
			islive=false;
	}
	public void setDir(Dir dir) {
		this.dir=dir;
	}
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);
	}
	public abstract int shownumber();
	public String getinfo() {
		return x+","+y+","+width+","+height+","+shownumber();
	}
}
