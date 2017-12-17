package controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageController {
	private static ImageController INSTANCE=new ImageController();
	public Image load,createroom,joinroom,help,helpon,joinroomon,createroomon,icon;
	public Image choicewindow,choiceboard,choicep1,choicep2;
	public Image over,pause,score,rank;
	public Image [][]plane=new Image[5][5];
	public Image []backgroud=new Image[5];
	public Image []enemy=new Image[20];
	public Image testbullet;
	public Image []boss=new Image[13];
	public Image []boom=new Image[13];
	public Image [][]updatebullet=new Image[10][20];
	public Image []superfire=new Image[20];
	public Image []normalbullet=new Image[20];
	public Image []staticimage=new Image[20];
	public Image []number=new Image[20];
	public Image []update=new Image[20];
	public List<Image> images;
	//Set<Integer> numbers=new TreeSet<Integer>();
	private ImageController() {
		images=new ArrayList<Image>();
		try {
			   icon=ImageIO.read(this.getClass().getResource("/images/icon.png"));
			   over=ImageIO.read(this.getClass().getResource("/images/over.png"));
			   pause=ImageIO.read(this.getClass().getResource("/images/pause.png"));
			   score=ImageIO.read(this.getClass().getResource("/images/score.png"));
			   rank=ImageIO.read(this.getClass().getResource("/images/rank.png"));
			   load=ImageIO.read(this.getClass().getResource("/images/load.png"));
			   createroom=ImageIO.read(this.getClass().getResource("/images/createroom.png"));
			   joinroom=ImageIO.read(this.getClass().getResource("/images/joinroom.png"));
			   help=ImageIO.read(this.getClass().getResource("/images/help.png"));
			   helpon=ImageIO.read(this.getClass().getResource("/images/help_on.png"));
			   joinroomon=ImageIO.read(this.getClass().getResource("/images/joinroom_on.png"));
			   createroomon=ImageIO.read(this.getClass().getResource("/images/createroom_on.png"));
			   choicewindow=ImageIO.read(this.getClass().getResource("/images/choicewindow.png"));
			   choiceboard=ImageIO.read(this.getClass().getResource("/images/choiceboard.png"));
			   choicep1=ImageIO.read(this.getClass().getResource("/images/1P.png"));
			   choicep2=ImageIO.read(this.getClass().getResource("/images/2P.png"));
			   plane[1][1]=ImageIO.read(this.getClass().getResource("/images/plane1.png"));
			   plane[1][2]=ImageIO.read(this.getClass().getResource("/images/plane1_f.png"));
			   plane[1][3]=ImageIO.read(this.getClass().getResource("/images/plane1_l.png"));
			   plane[1][4]=ImageIO.read(this.getClass().getResource("/images/plane1_r.png"));
			   plane[2][1]=ImageIO.read(this.getClass().getResource("/images/plane2.png"));
			   plane[2][2]=ImageIO.read(this.getClass().getResource("/images/plane2_f.png"));
			   plane[2][3]=ImageIO.read(this.getClass().getResource("/images/plane2_l.png"));
			   plane[2][4]=ImageIO.read(this.getClass().getResource("/images/plane2_r.png"));
			   plane[3][1]=ImageIO.read(this.getClass().getResource("/images/plane3.png"));
			   plane[3][2]=ImageIO.read(this.getClass().getResource("/images/plane3_f.png"));
			   plane[3][3]=ImageIO.read(this.getClass().getResource("/images/plane3_l.png"));
			   plane[3][4]=ImageIO.read(this.getClass().getResource("/images/plane3_r.png"));
			   plane[4][1]=ImageIO.read(this.getClass().getResource("/images/plane4.png"));
			   plane[4][2]=ImageIO.read(this.getClass().getResource("/images/plane4_f.png"));
			   plane[4][3]=ImageIO.read(this.getClass().getResource("/images/plane4_l.png"));
			   plane[4][4]=ImageIO.read(this.getClass().getResource("/images/plane4_r.png"));
			   
			   backgroud[1]=ImageIO.read(this.getClass().getResource("/images/backgroud1.png"));
			   backgroud[2]=ImageIO.read(this.getClass().getResource("/images/backgroud2.png"));
			   backgroud[3]=ImageIO.read(this.getClass().getResource("/images/backgroud3.png"));
			   backgroud[4]=ImageIO.read(this.getClass().getResource("/images/backgroud4.png"));
			 //  testbullet=ImageIO.read(this.getClass().getResource("/images/testbullet.png"));
			   for (int i=1;i<=12;i++) {
				   boss[i]=ImageIO.read(this.getClass().getResource("/images/boss ("+i+").png"));
			   }
			   for (int i=1;i<=10;i++) {
				   updatebullet[1][i]=ImageIO.read(this.getClass().getResource("/images/bullet1 ("+i+").png"));
			   }
			   for (int i=1;i<=8;i++) {
				   updatebullet[2][i]=ImageIO.read(this.getClass().getResource("/images/bullet2 ("+i+").png"));
			   }
			   for (int i=1;i<=6;i++) {
				   updatebullet[3][i]=ImageIO.read(this.getClass().getResource("/images/bullet3 ("+i+").png"));
			   }
			   for (int i=1;i<=4;i++) {
				   updatebullet[4][i]=ImageIO.read(this.getClass().getResource("/images/bullet4 ("+i+").png"));
			   }
			   for (int i=1;i<=10;i++) {
				   updatebullet[5][i]=ImageIO.read(this.getClass().getResource("/images/bullet5 ("+i+").png"));
			   }
			   for (int i=1;i<=12;i++) {
				   normalbullet[i]=ImageIO.read(this.getClass().getResource("/images/normalbullet ("+i+").png"));
			   }
			   for (int i=0;i<10;i++) {
				   number[i]=ImageIO.read(this.getClass().getResource("/images/number ("+i+").png"));
			   }
			   for (int i=1;i<=12;i++) {
				   boom[i]=ImageIO.read(this.getClass().getResource("/images/boom ("+i+").png"));
			   }
			   for (int i=1;i<=4;i++) {
				   update[i]=ImageIO.read(this.getClass().getResource("/images/update"+i+".png"));
			   }
			   for (int i=1;i<=13;i++) {
				   enemy[i]=ImageIO.read(this.getClass().getResource("/images/enemy"+i+".png"));
				}
			   staticimage[1]=ImageIO.read(this.getClass().getResource("/images/BOSSAPP.png"));
			   staticimage[2]=ImageIO.read(this.getClass().getResource("/images/menu1.png"));
			   staticimage[3]=ImageIO.read(this.getClass().getResource("/images/menu2.png"));
			   staticimage[3]=ImageIO.read(this.getClass().getResource("/images/cloud1.png"));
			   staticimage[4]=ImageIO.read(this.getClass().getResource("/images/cloud2.png"));
			   /*
			   images.add(icon);
			   images.add(plane[1][1]);
			   images.add(plane[2][1]);
			   images.add(plane[3][1]);
			   images.add(plane[4][1]);
			   images.add(backgroud[1]);
			   images.add(backgroud[2]);
			   images.add(backgroud[3]);
			   images.add(backgroud[4]);
			   for (int i=1;i<=13;i++) {
				   //enemy[i]=ImageIO.read(this.getClass().getResource("/images/enemy"+i+".png"));
				   images.add(enemy[i]);
				}
			   images.add(testbullet);*/
		}catch(Exception e) {
			   System.out.println("No point png.");
		   }
	}
	public Image find(int index) {
		return images.get(index);
	}
	public static ImageController getInstance() {
		return INSTANCE;
	}
}
