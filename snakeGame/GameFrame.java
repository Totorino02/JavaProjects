import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame{

    public GameFrame(){
        this.add(new GamePanel());
        this.pack();
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Game");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}