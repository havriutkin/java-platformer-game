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
    private int xDelta = 100, yDelta = 100;
    private BufferedImage[] images;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex = 0, aniSpeed = 30;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel(){
        setPanelSize();

        importImg();
        loadAnimations();

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));   // Listen for keyboard
        addMouseListener(mouseInputs);          // Listen for mouse clicks
        addMouseMotionListener(mouseInputs);    // Listen for mouse moving
    }

    private void loadAnimations() {
        animations = new BufferedImage[10][];

        for(int i = 0; i < images.length; i++){
            int frames = images[i].getWidth() / 128;

            animations[i] = new BufferedImage[frames];

            for(int j = 0; j < frames; j++)
                animations[i][j] = images[i].getSubimage(j*128, 0, 128, 128);
        }
    }

    private void importImg() {
        File[] files = new File[10];
        files[0] = new File("JavaPlatformer/res/Main Character/MC_Attack_1.png");
        files[1] = new File("JavaPlatformer/res/Main Character/MC_Attack_2.png");
        files[2] = new File("JavaPlatformer/res/Main Character/MC_Attack_3.png");
        files[3] = new File("JavaPlatformer/res/Main Character/MC_Attack_4.png");
        files[4] = new File("JavaPlatformer/res/Main Character/MC_Dead.png");
        files[5] = new File("JavaPlatformer/res/Main Character/MC_Hurt.png");
        files[6] = new File("JavaPlatformer/res/Main Character/MC_Idle.png");
        files[7] = new File("JavaPlatformer/res/Main Character/MC_Jump.png");
        files[8] = new File("JavaPlatformer/res/Main Character/MC_Run.png");
        files[9] = new File("JavaPlatformer/res/Main Character/MC_Walk.png");

        try{
            this.images = new BufferedImage[10];
            for(int i = 0; i < images.length; i++)
                this.images[i] = ImageIO.read(files[i]);
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateAnimationTick();

        setAnimation();

        updatePosition();

        g.drawImage(animations[playerAction][aniIndex], xDelta, yDelta, 128, 128, null);
    }

    private void updatePosition() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 2;
                    break;
                case UP:
                    yDelta -= 2;
                    break;
                case RIGHT:
                    xDelta += 2;
                    break;
                case DOWN:
                    yDelta += 2;
                    break;
            }
        }
    }

    private void setAnimation() {
        if (moving)
            playerAction = RUN;
        else
            playerAction = IDLE;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            aniIndex %= GetSpriteAmount(playerAction);
        }
    }
}
