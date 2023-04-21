package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(){
        this.jframe = new JFrame();

        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
}
