package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){
        this.game = game;

        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));   // Listen for keyboard
        addMouseListener(mouseInputs);          // Listen for mouse clicks
        addMouseMotionListener(mouseInputs);    // Listen for mouse moving
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
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
