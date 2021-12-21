
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Score{

    int GAME_WIDTH;
    int GAME_HEIGHT ;
    int player1 = 0;
    int player2 = 0; 

    public Score(int GAME_WIDTH, int GAME_HEIGHT){
        this.GAME_WIDTH =  GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
    }

    /**
     * Draw the user score on the panel
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2) - 85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2) + 20, 50);
    }
}