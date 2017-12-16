package controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageController {
	private static ImageController INSTANCE=new ImageController();
	public Image load,createroom,joinroom,help,helpon,joinroomon,createroomon;
	public Image choicewindow,choiceboard,choicep1,choicep2;
	public Image [][]plane=new Image[5][5];
	public List<Image> images;
	//Set<Integer> numbers=new TreeSet<Integer>();
	private ImageController() {
		images=new ArrayList<Image>();
		try {
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
			   plane[2][1]=ImageIO.read(this.getClass().getResource("/images/plane2.png"));
			   plane[2][2]=ImageIO.read(this.getClass().getResource("/images/plane2_f.png"));
			   plane[2][3]=ImageIO.read(this.getClass().getResource("/images/plane2_l.png"));
			   plane[3][1]=ImageIO.read(this.getClass().getResource("/images/plane3.png"));
			   plane[3][2]=ImageIO.read(this.getClass().getResource("/images/plane3_f.png"));
			   plane[3][3]=ImageIO.read(this.getClass().getResource("/images/plane3_l.png"));
			   plane[4][1]=ImageIO.read(this.getClass().getResource("/images/plane4.png"));
			   plane[4][2]=ImageIO.read(this.getClass().getResource("/images/plane4_f.png"));
			   plane[4][3]=ImageIO.read(this.getClass().getResource("/images/plane4_l.png"));
			   
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
