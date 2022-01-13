package brickbreaker;

import java.awt.*;

/**
 * @author totorino
 */
public class Brick{
    public int map[][];
    final int brickHeight = 40;
    final int brickWidth = 70;
    
    public Brick(int col, int row){
        map = new int[col][row];
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[0].length; j++){
                map[i][j] = 1;
            }
        }
    }
    
    public void draw(Graphics2D g){
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j*(brickWidth+10)+80, i*(brickHeight+10)+50, brickWidth, brickHeight);
                }
            }
        }
    }
    
    public void setValue(int value, int i, int j){
        map[i][j] = 0;
    }
}
