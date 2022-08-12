package display;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(){
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("SnakeGame");
        this.setVisible(true);
    }

    public static void init(){
        new Frame();
    }
}
