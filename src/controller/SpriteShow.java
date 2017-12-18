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
	int score;
	private SpriteShow(){
		shows=new ArrayList<Show>();
		score=0;
	}
	public void addshow(int x,int y,int width,int height,int imagenumber) {
		Show show=new Show(x,y,width,height,imagenumber);
		shows.add(show);
	}
	public void setscore(int score) {
		this.score=score;
	}
	public void clear() {
		shows.clear();
	}
	public void paint(Graphics g) {
		
		for (Show show:shows) {
			g.drawImage(ImageController.getInstance().find(show.imagenumber), show.x, show.y,show.width,show.height,null);
		}
		g.drawImage(ImageController.getInstance().score, 200,20,45,25,null);
		String str=Integer.toString(score);
		for (int i=0;i<str.length();i++) {
			int index=str.charAt(i)-'0';
			g.drawImage(ImageController.getInstance().number[index], 260+i*18,22,15,20,null);
		}
	}
	public static SpriteShow getInstance() {
		return INSTANCE;
	}
}
