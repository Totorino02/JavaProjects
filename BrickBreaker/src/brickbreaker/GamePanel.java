package brickbreaker;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * @author totorino
 */
public class GamePanel extends JPanel implements Runnable{
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    final int SCREEN_UNIT = 25;
    final int PADDLE_HEIGHT = 10;
    final int PADDLE_WIDTH = 100;
    final double speed = 0.25;
    int nbBricks;
    int score;
    boolean isRunning;
    Thread gameThread;
    Paddle paddle;
    Ball ball;
    Brick bricks;
    Random random;
    Dimension dimension = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    
    public GamePanel(){
        this.startGame();
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void startGame(){
        random = new Random();
        ball = new Ball(random.nextInt(10)*SCREEN_UNIT, SCREEN_HEIGHT/2, SCREEN_UNIT, SCREEN_UNIT);
        paddle =  new Paddle(SCREEN_WIDTH/2-PADDLE_WIDTH/2,SCREEN_HEIGHT-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        bricks = new Brick(3, 7);
        isRunning = true;
        nbBricks = 21;
        score = 0;
    }
    
    public void move(){
        ball.move();
    }
    public void draw(Graphics g){
        bricks.draw((Graphics2D)g);
        ball.draw(g);
        paddle.draw(g);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Score: "+score*5, SCREEN_WIDTH - metrics.stringWidth("Score: "+score*5) -10, 30 );
        
        if((ball.y + SCREEN_UNIT/2) >= SCREEN_HEIGHT){
            g.setColor(Color.RED);
            String over = "Game Over";
            String reloadMessage = "Please press Entrer to reload";
            g.drawString(reloadMessage, SCREEN_WIDTH/2 - metrics.stringWidth(reloadMessage)/2, SCREEN_HEIGHT/2 + 2*(g.getFont().getSize()/2));
            g.setFont(new Font("Ink Free", Font.BOLD, 40));            
            FontMetrics metrics2 = g.getFontMetrics(g.getFont());
            g.drawString(over, SCREEN_WIDTH/2 - metrics2.stringWidth(over)/2, SCREEN_HEIGHT/2 - (g.getFont().getSize()/2));
            isRunning = false;
         }
        if(nbBricks <= 0){
            g.setColor(Color.RED);
            String vin = "Congrats you Win :-)";
            String reloadMessage = "Please press Entrer to reload";
            g.drawString(reloadMessage, SCREEN_WIDTH/2 - metrics.stringWidth(reloadMessage)/2, SCREEN_HEIGHT/2 + 2*(g.getFont().getSize()/2));
            g.setFont(new Font("Ink Free", Font.BOLD, 40));            
            FontMetrics metrics2 = g.getFontMetrics(g.getFont());
            g.drawString(vin, SCREEN_WIDTH/2 - metrics2.stringWidth(vin)/2, SCREEN_HEIGHT/2 - (g.getFont().getSize()/2));
            isRunning = false;
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
      /**
     * cheeck the collisionnbetween things
    **/
    public void cheeckCollision(){
        if((ball.x - SCREEN_UNIT/2 )<= 0 || (ball.x + SCREEN_UNIT/2 - SCREEN_WIDTH) >= 0 ){
            ball.xVellocity = -ball.xVellocity;
        }
        
        //ball touch the game vertical bord
        if((ball.y - SCREEN_UNIT/2) <= 0 ){
            ball.yVellocity = -ball.yVellocity;
            ball.setYdirection(speed);
        }
        
        //intersection between paddle and brick
        if(ball.intersects(paddle)){
            ball.yVellocity = -ball.yVellocity;
            ball.setYdirection(speed);
        }   
        
        A : for(int i = 0; i< bricks.map.length; i++){
                for(int j = 0; j< bricks.map[0].length; j++){
                    if(bricks.map[i][j] > 0){
                        Rectangle rect = new Rectangle(j*(bricks.brickWidth+10)+80, i*(bricks.brickHeight+10)+50, bricks.brickWidth, bricks.brickHeight);
                        Rectangle brickRect = rect;
                        if(brickRect.intersects(ball)){
                            bricks.setValue(0, i, j);
                            nbBricks --;
                            score++;
                            if((ball.x+ball.width/2) >= brickRect.x || (ball.x-ball.width/2) <= ball.x+ball.width/2 ){
                                ball.yVellocity = -ball.yVellocity;
                            }else{
                                ball.xVellocity = -ball.xVellocity;
                            }
                            break A;
                        }
                    }
                }    
        } 
    }
        /**
     * Game loop reload all the time function like move, cheekCollision
     **/
    @Override
    public void run(){
        isRunning = true;
        long lastTime = System.nanoTime();
        double amoutsOfTicks = 60.0;
        long ns = (long)(1000000000/amoutsOfTicks);
        double delta = 0;
        
        while(true){
            long now = System.nanoTime();
            delta += (double)(now - lastTime)/ns;
            lastTime = now;
            if(delta > 1){
                if(isRunning){
                    this.move();
                    this.cheeckCollision();
                    this.repaint();
                }
                //System.out.println(isRunning);
                delta--;
            }
        }
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            paddle.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(!isRunning){
                    isRunning = true;
                    startGame();
                    repaint();
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            paddle.keyReleased(e);
        }
    }
}
