/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * ducdung
 */
public abstract class GameScreen extends JFrame implements KeyListener{

    public static int KEY_PRESSED = 0;
    public static int KEY_RELEASED = 1;
    
    public int CUSTOM_WIDTH  = 450;
    public int CUSTOM_HEIGHT = 400;
    
    private GameThread G_Thread;
    
    public static int MASTER_WIDTH = 450, MASTER_HEIGHT = 400;
    
    public GameScreen(){
        InitThread();
        InitScreen();
    }
    
    public void RegisterImage(int id, BufferedImage image){
        
    }
    
    public BufferedImage getImageWithID(int id){ // lấy hình ảnh từ 1 mã
        return null;
    }
    
    public GameScreen(int w, int h){ // khởi tạo cửa sổ game
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread(); // phương thức khởi tạo luồng trò chơi
        InitScreen(); // khởi tạo của sổ
    }
    
    private void InitScreen(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);
        
    }
    
    public void BeginGame(){ // bắt đầu trò chơi
        G_Thread.StartThread();
    }
    
    private void InitThread(){ // khởi tạo game_thread
        G_Thread = new GameThread(this);
        add(G_Thread);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { //lesten nhấp phím
        KEY_ACTION(e, GameScreen.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e) { // nhả phím
        KEY_ACTION(e, GameScreen.KEY_RELEASED);
    }
    
    public abstract void GAME_UPDATE(long deltaTime);
    public abstract void GAME_PAINT(Graphics2D g2);
    public abstract void KEY_ACTION(KeyEvent e, int Event);
}
