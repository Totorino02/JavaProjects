package brickbreaker;

import java.awt.Color;
import javax.swing.JFrame;

 
/**
 * @author totorino
 */
public class GameFrame extends JFrame {
    
    public GameFrame(){
        this.setTitle("Brick Breacking Game");
        this.add(new GamePanel());
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
