import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;

    public Ball(){
        
    }

    public void setXDirection(){}

    public void setYDirection(){}

    public void draw(Graphics g){
        g.fillOval(arg0, arg1, arg2, arg3);
    }

    public void move(){}
}