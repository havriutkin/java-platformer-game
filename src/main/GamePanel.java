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

public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GamePanel(){
        setPanelSize();
        importImg();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));   // Listen for keyboard
        addMouseListener(mouseInputs);          // Listen for mouse clicks
        addMouseMotionListener(mouseInputs);    // Listen for mouse moving
    }

    private void importImg() {
        File file = new File("JavaPlatformer/res/Main Character/MC_Idle.png");

        try{
            this.img = ImageIO.read(file);
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }

    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        subImg = img.getSubimage(0, 0, 128, 128);

        g.drawImage(subImg, xDelta, yDelta, 128, 128, null);
    }
}
