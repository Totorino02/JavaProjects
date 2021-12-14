import java.awt.Color;
import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public GameFrame(){

        this.add(new GamePanel());
        this.setTitle("Ping Pong Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}