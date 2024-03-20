package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pkg2dgamesframework.Animation;
import pkg2dgamesframework.Objects;

public class Ground extends Objects{
	private int x1,y1,x2,y2,x3,y3,x4,y4;

	private BufferedImage groundImage;
	Ground(){
		
	try {
		groundImage = ImageIO.read(new File("asset/ground.png"));
	} catch (IOException e) {}
	
	this.x1 =0;
	this.y1=550;
	
	this.x2= x1 + 546;
	this.y2 = 550;
	
	this.x3= x2 + 546;
	this.y3 = 550;
	
	this.x4= x3 + 546;
	this.y4 = 550;
	}
	
	public void update() {
		 x1-=2;
		 x2-=2;
		 x3-=2;
		 x4-=2;
		if(x1 < -546) x1=x3+546;
		if(x2 < -546) x2=x1+546;
		if(x3 < -546) x3=x1+546;
	}
	public void paint(Graphics2D g2) {
		g2.drawImage(groundImage, x1, y1,null);
		g2.drawImage(groundImage, x2, y2,null);
		g2.drawImage(groundImage, x3, y3,null);
	//	g2.drawImage(groundImage, x4, y4,null);
	}
	 public int getYGround() {
		 return this.y1;
	 }
}
