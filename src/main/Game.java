package main;

import java.awt.Graphics;

import javax.swing.text.Style;

import entities.Player;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public Game(){
        initClasses();

        this.gamePanel = new GamePanel(this);   // Init game panel
        this.gameWindow = new GameWindow(this.gamePanel); // Init game window
        gamePanel.requestFocus();   // Start listen for inputs

        startGameLoop();
    }

    private void initClasses() {
        this.player = new Player(0, 0);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;   // In nano seconds
        double timePerUpdate = 1000000000.0 / UPS_SET;   // In nano seconds

        long previousTime = System.nanoTime();

        int frames = 0;         // Amount of frames 
        int updates = 0;        // Amount of updates

        long lastCheck = System.currentTimeMillis();

        // Differences between now and last check
        double deltaU = 0;     
        double deltaF = 0;

        while(true){

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Update change
            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            // Frame change
            if (deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--; 
            }

            // FPS check
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " |  UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Getters
    public Player getPlayer(){
        return this.player;
    }


    public void windowFocusLost(){
        player.resetDirBooleans();
    }
}
