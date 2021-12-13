import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 600;
    final int SCREEN_UNIT = 25;
    final int OBJECT_X_DIM = (int)(SCREEN_WIDTH/SCREEN_UNIT);
    final int OBJECT_Y_DIM = (int)(SCREEN_HEIGHT/SCREEN_UNIT);
    int DELAY = 90;
    int FOOD_X;
    int FOOD_Y;
    char direction = 'D';
    boolean isRunning = false;
    int foodEaten = 0;
    int bodyPart = 6;
    int[] X = new int[SCREEN_UNIT];
    int[] Y = new int[SCREEN_UNIT];
    Random ranadom;
    Timer timer;

    public GamePanel(){
        ranadom = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.startGame();
    }

    public void startGame(){
        this.isRunning = true;
        this.newFood();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.draw(g);
    }

    public  void draw(Graphics g){
        if(isRunning){
            /*
            //draw vertical lines
            for(int i=0; i<OBJECT_X_DIM; i++){
                g.drawLine(i*SCREEN_UNIT, 0, i*SCREEN_UNIT, SCREEN_HEIGHT);
            }
            //draw horizontal lines
            for(int i=0; i<OBJECT_Y_DIM; i++){
                g.drawLine(0, i*SCREEN_UNIT, SCREEN_WIDTH, i*SCREEN_UNIT);
            }*/

            //draw the apple
            g.setColor(Color.YELLOW);
            g.fillOval(FOOD_X, FOOD_Y, SCREEN_UNIT, SCREEN_UNIT);
            //
            for(int i=0; i<bodyPart; i++){
                if(i==0){
                    g.setColor(Color.RED);
                    g.fillRect(X[i], Y[i], SCREEN_UNIT, SCREEN_UNIT);
                }else{
                    g.setColor(new Color(ranadom.nextInt(255), ranadom.nextInt(255),ranadom.nextInt(255)));
                    g.fillRect(X[i], Y[i], SCREEN_UNIT, SCREEN_UNIT);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 42));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Score: "+foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+foodEaten ))/2, g.getFont().getSize());

        }else{
            this.gameOver(g);
        }
    }

    public void newFood(){
        FOOD_X = SCREEN_UNIT*this.ranadom.nextInt(OBJECT_X_DIM);
        FOOD_Y = SCREEN_UNIT*this.ranadom.nextInt(OBJECT_Y_DIM);
    }

    public void move(){
        for(int i=bodyPart; i>0; i--){
            X[i] = X[i-1];
            Y[i] = Y[i-1];
        }
        switch (direction){
            case 'U':
                Y[0] = Y[0] - SCREEN_UNIT;
                break;
            case 'D':
                Y[0] = Y[0] + SCREEN_UNIT;
                break;
            case 'L':
                X[0] = X[0] - SCREEN_UNIT;
                break;
            case 'R':
                X[0] = X[0] + SCREEN_UNIT;
                break;
        }
    }

    public void checkFood(){
        if((X[0] == FOOD_X) && (Y[0] == FOOD_Y)){
            bodyPart++;
            foodEaten++;
            this.newFood();
        }
    }

    public void checkCollision(){
        if(X[0] >= SCREEN_WIDTH || X[0]<0 || Y[0]<0 || Y[0] >= SCREEN_HEIGHT){
            isRunning = false;
        }
    }

    public void gameOver(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 42));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+foodEaten ))/2, g.getFont().getSize());

        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(isRunning){
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_DOWN :
                    if(direction != 'U') direction = 'D';
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') direction = 'L';
                    break;
                case  KeyEvent.VK_RIGHT:
                    if(direction != 'L') direction = 'R';
                    break;
            }
        }
    }
}