package flappybirds;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pkg2dgamesframework.Objects;
public class Chimney extends Objects{
	
private Rectangle rect;
public static int wsize = 105;
public static int hsize = 411;

public Chimney(int x,int y,int w, int h) {
	super(x,y,w,h);
	rect = new Rectangle(x,y,w,h);
}
public void update() {
   this.setPosX(this.getPosX() -2);
   this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
}
public Rectangle getRect() {
	return this.rect;
}
public void setRect(int x,int y) {
	this.rect.setLocation(x, y);
}
}
