package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_label = new JPanel();
    JLabel textField =  new JLabel();
    JButton[] buttons = new JButton[9];
    Random random = new Random();
    boolean isPlayerX;
    boolean isEnd = false;

    public TicTacToe(){
        frame.setTitle("TicTacToe");
        frame.setBackground(new Color(50,50,50));
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setForeground(new Color(240, 13, 13));
        textField.setFont(new Font("Ink Free",Font.BOLD, 25));
        textField.setText("TicTacToe");
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setVerticalAlignment(JLabel.CENTER);

        title_panel.setPreferredSize(new Dimension(45,50));
        title_panel.setBackground(new Color(25,25,25));
        title_panel.add(textField);

        button_label.setLayout(new GridLayout(3, 3));
        button_label.setBackground(new Color(150,150,150));
        //button_label.setBounds();
        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            button_label.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 150));
            buttons[i].addActionListener(this);
        }
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_label);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i] && !isEnd){
                if(buttons[i].getText().equals("")){
                    if(isPlayerX){
                        buttons[i].setForeground(Color.GREEN);
                        buttons[i].setText("X");
                        isPlayerX = !isPlayerX;
                        textField.setText("O Turn");
                        checkAll();
                    }else {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        isPlayerX = !isPlayerX;
                        textField.setText("X Turn");
                        checkAll();
                    }
                }
            }
        }
    }
    public void firstTurn(){
        if(random.nextInt(2) == 0){
            isPlayerX = true;
            textField.setText("X Turn");
        }else{
            isPlayerX = false;
            textField.setText("O Turn");
        }
    }
    public void checkAll(){
        String[] players = {"X", "O"};
        int[][] winner = {{1,2,3},{1,4,7},{7,8,9},{3,6,9},{2,5,8},{4,5,6},{1,5,9},{3,5,7}};
        for(int i=0; i<2 ; i++){
            for(int j=0; j<8; j++){
                int a = winner[j][0];
                int b = winner[j][1];
                int c = winner[j][2];
                check(a, b, c, players[i]);
            }
        }
    }
    public void check(int a, int b , int c, String player){
        a = a-1;
        b = b-1;
        c = c-1;
        if(
                buttons[a].getText().equals(player) &&
                buttons[b].getText().equals(player) &&
                buttons[c].getText().equals(player)
        ){
            for(int i=0; i<9; i++){
                if(a!=i && b!=i && c!=i){
                    buttons[i].setForeground(new Color(53, 34, 34));
                }else{
                    if(i==a || i==b || i==c){
                        buttons[i].setBackground(new Color(182, 216, 122));
                    }
                }
            }
            isEnd = true;
            textField.setForeground(Color.GREEN);
            textField.setFont(new Font("Ink Free", Font.BOLD, 25));
            textField.setText(player +" Is the Winner");
        }
    }
}