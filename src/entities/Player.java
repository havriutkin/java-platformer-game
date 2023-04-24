package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilz.LoadSave;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex = 0, aniSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float speed = 1.5f;
    
    public Player(float x, float y){
        super(x, y);
        loadAnimationsFromDir();
    }

    public void update(){
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 128, 128, null);
    }

    // Loading resources
    private void loadAnimationsFromAtlas() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[10][10];
        for(int i = 0; i < animations.length; i++)
            for(int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j*128, i*128, 128, 128);
    }

    private void loadAnimationsFromDir(){
        BufferedImage[] images = LoadSave.GetSpriteArray(LoadSave.PLAYER_DIR);

        animations = new BufferedImage[images.length][];
        for(int i = 0; i < animations.length; i++){
            animations[i] = new BufferedImage[images[i].getWidth() / 128];
            for(int j = 0; j < animations[i].length; j++)
                animations[i][j] = images[i].getSubimage(j * 128, 0, 128, 128);
        }
    }


    // Update functions
    private void updatePosition() {
        moving = false;

        if(left && !right){
            x -= speed;
            moving = true;
        } else if(right && !left){
            x += speed;
            moving = true;
        }

        if(up && !down){
            y -= speed;
            moving = true;
        } else if(down && !up){
            y += speed;
            moving = true;
        }
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUN;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK_1;

        if(startAni != playerAction){
            aniTick = 0;
            aniIndex = 0;
        }

    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    // Getters
    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }


    // Setters
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

}
