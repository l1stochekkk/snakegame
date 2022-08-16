package display;

import core.GameMap;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("SnakeGame");
    }

    public static void init(){
        Frame frame = new Frame();
        frame.setLayout(new GridLayout(GameMap.size, GameMap.size));
        for (int i = 0; i < GameMap.size; i++) {
            for (int j = 0; j < GameMap.size; j++) {
                frame.add(new JLabel(GameMap.map[i][j].getEntity()));
            }
        }

        frame.setVisible(true);
    }
}
