package flappybirds;
import java.awt.Rectangle;

import pkg2dgamesframework.Objects;

public class Bird extends Objects{
	
	private float vt = 0;
	private boolean isflying = false;
	private Rectangle rect;
	private boolean islive=true;
public Bird(int x, int y, int w,int h) {
	super(x,y,w,h);
	rect = new Rectangle(x,y,w,h);
}

 public void update(long deltaTime) {
	 vt+=FlappyBirds.g;
	 this.setPosY(this.getPosY() + vt);
	 this.rect.setLocation((int)this.getPosX(), (int)this.getPosY());
	 if(vt < 0) isflying = true;
	 else isflying = false;   
 }
 public Rectangle getRect() {
	 return this.rect;
 }
 public void setlive(boolean b) {
	 islive = b;
 }
 public boolean getlive() {
	 return islive;
 }
 public void fly() {
	 vt =-3;
 }
 public void setvt(float x) {
	 this.vt = x;
 }
 public boolean getflying() {
		 return isflying;
 }
}
