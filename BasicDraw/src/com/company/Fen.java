package com.company;

import javax.swing.*;
import java.awt.*;

public class Fen extends JFrame {
    public Fen(){
        this.setTitle("Draw");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,400));
        this.setSize(600,400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();

        Dessin panel = new Dessin();
        this.getContentPane().add(panel, BorderLayout.NORTH);
        this.setVisible(true);
    }

}
