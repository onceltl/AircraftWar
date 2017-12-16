package controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

class Show{
	public int x,y, width,height,imagenumber;
	public Show(int x,int y,int width,int height,int imagenumber) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.imagenumber=imagenumber;
	}
}
public class SpriteShow {
	private static SpriteShow INSTANCE = new SpriteShow();
	List<Show> shows;
	private SpriteShow(){
		shows=new ArrayList<Show>();
	}
	public void addshow(int x,int y,int width,int height,int imagenumber) {
		Show show=new Show(x,y,width,height,imagenumber);
		shows.add(show);
	}
	public void clear() {
		shows.clear();
	}
	public void paint(Graphics g) {
		for (Show show:shows) {
			g.drawImage(ImageController.getInstance().find(show.imagenumber), show.x, show.y,show.height,show.width,null);
		}
	}
	public static SpriteShow getInstance() {
		return INSTANCE;
	}
}
