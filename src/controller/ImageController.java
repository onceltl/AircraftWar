package controller;

import java.awt.Image;

import javax.imageio.ImageIO;

public class ImageController {
	private static ImageController INSTANCE=new ImageController();
	public Image load,createroom,joinroom,help,helpon,joinroomon,createroomon;
	public Image choicewindow;
	private ImageController() {
		try {
			   load=ImageIO.read(this.getClass().getResource("/images/load.png"));
			   createroom=ImageIO.read(this.getClass().getResource("/images/createroom.png"));
			   joinroom=ImageIO.read(this.getClass().getResource("/images/joinroom.png"));
			   help=ImageIO.read(this.getClass().getResource("/images/help.png"));
			   helpon=ImageIO.read(this.getClass().getResource("/images/help_on.png"));
			   joinroomon=ImageIO.read(this.getClass().getResource("/images/joinroom_on.png"));
			   createroomon=ImageIO.read(this.getClass().getResource("/images/createroom_on.png"));
			   choicewindow=ImageIO.read(this.getClass().getResource("/images/choicewindow.png"));
		}catch(Exception e) {
			   System.out.println("No point png.");
		   }
	}
	public static ImageController getInstance() {
		return INSTANCE;
	}
}
