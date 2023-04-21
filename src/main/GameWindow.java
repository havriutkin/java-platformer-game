package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        this.jframe = new JFrame();    // Create JFrame

        // Set up JFrame
        jframe.setSize(400, 400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jframe.add(gamePanel);  // Add game panel to JFrame
        jframe.setLocationRelativeTo(null);     // Spawn window in the center
        jframe.setVisible(true);
    }
}
