package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        this.jframe = new JFrame();    // Create JFrame

        // Set up JFrame
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jframe.add(gamePanel);  // Add game panel to JFrame
        jframe.setLocationRelativeTo(null);     // Spawn window in the center
        jframe.setResizable(false); // Resize is prohibitted
        jframe.pack();  // Fit size to game panel
        
        jframe.setVisible(true);
    }
}
