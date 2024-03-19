package flappybirds;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import pkg2dgamesframework.QueueList;
public class ChimneyGroup {
private QueueList<Chimney> chimneys;
private BufferedImage chimneyImage;
private BufferedImage chimneyImage2;
private int topchimneyY = -300;
private int bottomchimneyY = 250;
public static int SIZE = 6;

public Chimney get(int id) {
	return chimneys.get(id);
}

public int getRandomY() {
	Random random = new Random();
	int a = random.nextInt(10); 
	return a*20;
}

public void resetChimney() {
	chimneys = new QueueList<Chimney>();
	Chimney cn;
	for(int i=0;i<3;i++) {
		cn =  new Chimney(600 + (i+1)*400,this.bottomchimneyY/*+this.getRandomY()*/,Chimney.wsize,Chimney.hsize);
		chimneys.push(cn);
		cn =  new Chimney(600 + (i+1)*400,this.topchimneyY/*+this.getRandomY()*/,Chimney.wsize,Chimney.hsize);
		chimneys.push(cn);
	}
}

public ChimneyGroup() {
	//System.out.println("up");
	chimneys = new QueueList<Chimney>();
	
	try {
		chimneyImage = ImageIO.read(new File("asset/chimney.png"));
		chimneyImage2 = ImageIO.read(new File("asset/chimney2.png"));
	} catch (IOException e) {}
	
	Chimney cn;
	for(int i=0;i<this.SIZE/2;i++) {
		cn =  new Chimney(600 + (i+1)*400,this.bottomchimneyY + this.getRandomY(),Chimney.wsize,Chimney.hsize);
		chimneys.push(cn);
		cn =  new Chimney(600 + (i+1)*400,this.topchimneyY + this.getRandomY(),Chimney.wsize,Chimney.hsize);
		chimneys.push(cn);
	}
}
public void update() {
	for(int i=0;i<this.SIZE;i++) {
		chimneys.get(i).update();
	}
	//System.out.println("update");
		if(chimneys.get(0).getPosX() < -91) {
			Chimney cn;
			cn = chimneys.pop(); 
			cn.setPosX(chimneys.get(4).getPosX()+300);
			cn.setPosY(300 + this.getRandomY());
			cn.setRect((int)cn.getPosX(),(int) cn.getPosY());
			chimneys.push(cn);
			
			cn = chimneys.pop();
			cn.setPosX(chimneys.get(4).getPosX());
			cn.setPosY(-300 + this.getRandomY());
			cn.setRect((int)cn.getPosX(),(int) cn.getPosY());
			chimneys.push(cn);
		}
	}

public void paint(Graphics2D g2) {
	//System.out.println("paint");
	for(int i=0;i<this.SIZE;i++) {
		if(i%2==0) {
		g2.drawImage(chimneyImage,(int)chimneys.get(i).getPosX(),(int)chimneys.get(i).getPosY()  ,null);
		}
		else {
		g2.drawImage(chimneyImage2,(int)chimneys.get(i).getPosX(),(int)chimneys.get(i).getPosY() ,null);
	}	
 }
}
}
