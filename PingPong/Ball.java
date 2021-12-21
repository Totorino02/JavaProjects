import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends Rectangle{

    Random random;
    double xVelocity = 2;
    double yVelocity = 2;
    double speed = 2;

    public Ball(int xBall, int yBall, int ballWidth, int ballHeight){
        super(xBall, yBall, ballWidth, ballHeight);
    }

    public void setXDirection(double xSpeed){
        if(xVelocity > 0){
            xVelocity += xSpeed;
        }else{
            xVelocity -= xSpeed;
        }
    }

    public void setYDirection(double ySpeed){
        if(yVelocity > 0){
            yVelocity += ySpeed;
        }else{
            yVelocity -= ySpeed;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public void move(){
        y -= yVelocity;
        x += xVelocity;
    }
}