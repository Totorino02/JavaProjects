package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author totorino
 */
public class Ball extends Rectangle {
    
    double xVellocity = 1;
    double yVellocity = 1;
    double speed = 1;
    Random random;
    int rd;
    public Ball(int ballX, int ballY, int ballWidth, int ballHeight){
        super(ballX, ballY, ballWidth, ballHeight);
        random = new Random();
        rd = random.nextInt(4);
    }
    
    public void move(){
        if(rd%2 == 0) x -= xVellocity; else x += xVellocity;
        y -= yVellocity;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
    }
    
    public void setXdirection(double xSpeed){
        if(xVellocity > 0){
            xVellocity += xSpeed;
        }else{
            xVellocity -= xSpeed;
        }
    }
    
    public void setYdirection(double ySpeed){
        if(yVellocity >0){
            yVellocity += ySpeed;
        }else{
            yVellocity -= ySpeed;
        }
    }
    
}
