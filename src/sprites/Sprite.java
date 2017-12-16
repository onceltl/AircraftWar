package sprites;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Sprite {
	protected int x,y;
	protected int width,height;
	public Sprite(int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public abstract void move();
	public abstract void drawSuperFire(Graphics g);
	public Rectangle getRectangle(){
		return new Rectangle(x,y,width,height);
	}
}
