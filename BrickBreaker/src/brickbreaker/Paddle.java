package brickbreaker;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author totorino
 */

public class Paddle extends Rectangle{
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    final int PADDLE_HEIGHT = 10;
    final int PADDLE_WIDTH = 100;
    int SPEED = 10;
    final int xSpeed = 10;
    
    public Paddle(int paddleX, int paddleY, int paddleWidth, int paddleHeight){
        super(paddleX, paddleY, paddleWidth, paddleHeight);
    }
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, (SCREEN_HEIGHT-height), width, height);
    }
    
    public void move(){
        x += SPEED;
    }
    
    public void setXdirection(int xSpeed){
        if(xSpeed < 0){
            SPEED = -Math.abs(xSpeed);
        }else{
            SPEED = Math.abs(xSpeed);
        }
    }
    
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if((x- xSpeed) > 0){
                    this.setXdirection(-xSpeed);
                    this.move();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if((x+PADDLE_WIDTH+ xSpeed) < SCREEN_WIDTH){
                    this.setXdirection(xSpeed);
                    this.move();
                }
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){}
}
