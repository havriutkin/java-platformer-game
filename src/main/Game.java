package main;

public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Game(){
        this.gamePanel = new GamePanel();   // Init game panel
        this.gameWindow = new GameWindow(this.gamePanel); // Init game window
        gamePanel.requestFocus();   // Start listen for inputs
    }
}
