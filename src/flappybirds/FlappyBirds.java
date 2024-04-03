package flappybirds;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

public class FlappyBirds extends GameScreen {
	private BufferedImage anhhung;
	private BufferedImage chimney;
	private BufferedImage background;
	private BufferedImage gameover;
	private BufferedImage titlegame;
	private Animation anhhung_anim;
	private int point=0;
	public static float g = 0.1f; // gia tốc trọng trường
	private Bird bird = new Bird(250, 250, 30, 21); // khởi tạo đối tượng
	private ChimneyGroup chimneys = new ChimneyGroup();
	private Ground ground = new Ground();
	
	private int BEGIN_SCREEN = 0;
	private int GAME_PLAY_SCREEN = 1;
	private int GAME_OVER_SCREEN = 2;
	private int CurrenScreen = BEGIN_SCREEN;
 
	public FlappyBirds() {
		super(600, 600); // khởi tạo cửa sổ game;
		try {
			anhhung = ImageIO.read(new File("asset/anhhung.png"));
			background = ImageIO.read(new File("asset/background.png"));
			gameover = ImageIO.read(new File("asset/gameover.png"));
			titlegame = ImageIO.read(new File("asset/titlegame.png"));
			
		} catch (IOException e) {
		}
		anhhung_anim = new Animation(500); // mns; chuỗi hình ảnh hoạt động dc
		AFrameOnImage f = new AFrameOnImage(0, 0, 30, 21); // hình ảnh đơn lẻ
		anhhung_anim.AddFrame(f); // add vào list hình ảnh chuyển động

		AFrameOnImage f1 = new AFrameOnImage(45, 0, 30, 21);
		anhhung_anim.AddFrame(f1);

		AFrameOnImage f2 = new AFrameOnImage(91, 0, 30, 21);
		anhhung_anim.AddFrame(f2);

		AFrameOnImage f3 = new AFrameOnImage(137, 0, 30, 21);
		anhhung_anim.AddFrame(f3);


		BeginGame();
	}

	public FlappyBirds(int w, int h) {
		super(w, h);
	}
    private void resetGame() {
    	this.point =0;
    	bird.setPos(250,250);
    	bird.setvt(0.0f);
    	chimneys.resetChimney();
    }
	@Override
	public void GAME_UPDATE(long deltaTime) {
		if (CurrenScreen == BEGIN_SCREEN) {
			 bird.setlive(true); this.resetGame();
		} 

		else if (CurrenScreen == GAME_PLAY_SCREEN) {
			anhhung_anim.Update_Me(deltaTime);
			bird.update(deltaTime);
			ground.update();
			chimneys.update();
			if(bird.getPosY() +  bird.getH() > ground.getYGround()) {
				CurrenScreen =GAME_OVER_SCREEN;
			
			}
           for(int i=0;i<ChimneyGroup.SIZE;i++) {
        	   if(bird.getRect().intersects(chimneys.get(i).getRect())) {
        		   bird.setlive(false); CurrenScreen =GAME_OVER_SCREEN;
        	   }
           }
           for(int i=0;i<chimneys.SIZE;i++) {
        	   if(bird.getPosX() == chimneys.get(i).getPosX()) {   
        		   point ++;
        	   }
           }
           
			
		} else if (CurrenScreen == GAME_OVER_SCREEN) {bird.setlive(false);}
		
	}

	@Override
	public void GAME_PAINT(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.drawImage(background,0,0,null); 
	    if (bird.getflying()) {
			anhhung_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), anhhung, g2, 0, -1);
		} else {
			anhhung_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), anhhung, g2, 0, 0);
		}

		chimneys.paint(g2);
		ground.paint(g2);
		
		if (CurrenScreen == BEGIN_SCREEN) {
		
			g2.drawImage(titlegame,200,200,null);
		}

		else if (CurrenScreen == GAME_PLAY_SCREEN) {

		} else if (CurrenScreen == GAME_OVER_SCREEN) {
			
			g2.drawImage(gameover,200,200,null);

		}
		g2.drawString("Point : " + point, 20, 30);
		
	}

	@Override
	public void KEY_ACTION(KeyEvent e, int Event) {
		if (Event == KEY_PRESSED) {
		    CurrenScreen = GAME_PLAY_SCREEN;
		}
		 if ( CurrenScreen == GAME_PLAY_SCREEN) {
		 	bird.fly();
		}
		else if (CurrenScreen == GAME_OVER_SCREEN) {
			CurrenScreen = BEGIN_SCREEN ; 
		}
		}
		
	
	public static void main(String[] args) {
		new FlappyBirds();
	}
}
