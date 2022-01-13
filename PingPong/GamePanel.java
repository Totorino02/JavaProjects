
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.555);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 25 ;
    static final int PADDLE_WIDTH = 50;
    static final int PADDLE_HEIGHT = 200;
    static final double speed = 1;
    Thread gameThread;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Image image;
    Graphics graphics;

    public GamePanel(){
        this.newBall();
        this.newPaddles();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(SCREEN_SIZE);
        this.setFocusable(true);
        this.addKeyListener(new MykeyAdapter()); 
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        ball = new Ball(GAME_WIDTH/2 -BALL_DIAMETER/2, GAME_HEIGHT/2 -BALL_DIAMETER/2, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles(){
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
    }

    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){
        ball.move();
    }

    /**
     * When the game start it paint the panel
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Check if the ball touch the the game top or bottom bar or if it collied the paddles
     * if G -> top or bottom bar we change the yVellocity(direction) to it's opposite
     * if G -> Paddle we change the xVellocity(direction) to it's opposite
     */
    public void checkCollision(){
        if(ball.y <= BALL_DIAMETER/2 || ball.y >= GAME_HEIGHT -BALL_DIAMETER/2){
            ball.yVelocity = -ball.yVelocity;
        }
        if(ball.intersects(paddle1) || ball.intersects(paddle2)){
            ball.xVelocity = -ball.xVelocity;
            ball.setXDirection(speed);
        }
        if(ball.x < (BALL_DIAMETER/2)){
            score.player1++;
            newBall();
        }
        if(ball.x > (GAME_WIDTH - BALL_DIAMETER/2)){
            score.player2++;
            newBall();
        }
    }

    /**
     * reload function like move checkCollision ant repaint the UI all the time
     */
    public void run(){
        boolean isRunning = true;
        long lastTime = System.nanoTime();
        double amountsOfTicks = 60.0;
        double delta = 0;
        long ns = (long) (1000000000/amountsOfTicks) ;
        
        //game loop
        while(isRunning){
            long now = System.nanoTime();
            delta += (double)(now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1.5){
                this.move();
                this.checkCollision();
                this.repaint();
                delta--;
            }
        }
    }

    public class MykeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            //key listener for the game
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }
}