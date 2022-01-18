package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Dessin extends JPanel implements Runnable{
    private String[] str = {"ROND","TRIANGLE","CARRE","ETOILE"};
    private JComboBox combo = new JComboBox(str);
    private String dessin = "ROND";
    private JPanel pan = new JPanel();

    public Dessin(){
        JLabel label = new JLabel("Choissisez la figure");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600,400));
        pan.setBackground(Color.DARK_GRAY);
        this.setBackground(Color.RED);
        combo.setPreferredSize(new Dimension(150,25));
        combo.addActionListener(new ItemAction());
        label.setForeground(Color.white);
        Font font = new Font("Times Roman",Font.BOLD,18);
        combo.setForeground(Color.BLACK);
        combo.setFont(font);
        pan.add(label);
        pan.add(combo);
        this.add(pan, BorderLayout.NORTH);

        Thread th = new Thread(this);
        th.start();
    }

    /**
     * @param g
     */
    public void draw(Graphics g){
        switch (this.dessin) {
            case "ROND" : {
                g.setColor(Color.RED);
                g.fillOval(200, 100, 100, 100);
                break;
            }
            case "TRIANGLE": {
                g.setColor(Color.BLACK);
                int[] width = {200, 300, 250};
                int[] height = {250, 250, 150};
                g.fillPolygon(width, height, 3);
                break;
            }
            case "CARRE": {
                g.setColor(Color.ORANGE);
                g.fillRect(200, 150, 150, 75);
                break;
            }
            case "ETOILE": {
                g.setColor(Color.BLUE);
                int[] wid = {200, 300, 250};
                int[] hei = {250, 250, (int)(250 -Math.sqrt(7500))};
                int[] widt = {250,300 , 200};
                int[] heig = {(int)(200 +Math.sqrt(7500)), 200, 200};
                g.fillPolygon(wid, hei, 3);
                g.fillPolygon(widt, heig, 3);
                break;
            }
            default: {
            }
        }
    }

    /**
     * @param g
     */
    public void paintComponent(Graphics g){
        this.draw(g);
    }

    @Override
    public void run() {
        boolean isRunning = true;
        long lastTime = System.nanoTime();
        double nbTick = 60.0;
        long nano = (long)(1000000000/nbTick);
        long now;
        double delta = 0;
        while (isRunning){
            now = System.nanoTime();
            delta += (double) (now - lastTime)/nano;
            if(delta >= 1.5){
                repaint();
                lastTime = now;
                delta--;
            }
        }
    }

    private class ItemAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                dessin = Objects.requireNonNull(combo.getSelectedItem()).toString();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
}
