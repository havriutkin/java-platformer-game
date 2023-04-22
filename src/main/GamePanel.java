package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){
        this.game = game;

        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));   // Listen for keyboard
        addMouseListener(mouseInputs);              // Listen for mouse clicks
        addMouseMotionListener(mouseInputs);        // Listen for mouse moving
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }


    // Getters 
    public Game getGame(){
        return this.game;
    }
}
