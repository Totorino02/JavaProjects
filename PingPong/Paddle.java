import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

    int speed = 4;
    int Ydirection;
    int Xdirection;
    int PADDLE_HEIGHT;
    int PADDLE_WIDTH;
    int Yvelocity;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.555);
    int id;
    public Paddle(int Xdirection, int Ydirection, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(Xdirection, Ydirection, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e){
        if(id == 1){
            switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    if(y > 0){
                        setYDirection(-speed);
                        move();
                    }
                    break;
                case KeyEvent.VK_W:
                    if(y < (GAME_HEIGHT - height)){ 
                        setYDirection(speed);
                        move();
                    }
                    break;
            }
        }
        if(id == 2){
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    if(y > 0){
                        setYDirection(-speed);
                        move();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(y < (GAME_HEIGHT - height)){   
                        setYDirection(speed);
                        move();
                    }
                    break;
            }
        }
    }
 
    public void keyReleased(KeyEvent e){}

    public void setYDirection(int direction){
        Yvelocity = direction;
    }

    public void move(){    
        y = y + Yvelocity;
    }

    public void draw(Graphics g){
        if(id == 2){
            g.setColor(Color.RED);
        }
        else if(id == 1){
            g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, width, height);
    }
}