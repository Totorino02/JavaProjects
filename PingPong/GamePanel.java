
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.555);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 25 ;
    static final int PADDLE_WIDTH = 20;
    static final int PADDLE_HEIGHT = 25;
    Thread gameThread;
    Paddle paddle1;
    Paddle paddle2;

    public GamePanel(){
        
        this.setPreferredSize(SCREEN_SIZE);
        this.setFocusable(true);

    }

    public void newBall(){}

    public void newPaddles(){}

    public void draw(){}

    public void move(){}

    public void paint(Graphics g){

    }

    public void checkCollision(){}

    public void run(){}

    public class MykeyAdapter extends KeyAdapter{

        public void keyPressed(KeyEvent e){}

        public void keyReleased(KeyEvent e){}

    }
}