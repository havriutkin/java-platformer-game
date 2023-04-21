package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class Player extends Entity{
    private BufferedImage[] images;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex = 0, aniSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float speed = 2.0f;
    
    public Player(float x, float y){
        super(x, y);
        importImages();
        loadAnimations();
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
    private void importImages(){
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

    private void loadAnimations() {
        animations = new BufferedImage[10][];

        for(int i = 0; i < images.length; i++){
            int frames = images[i].getWidth() / 128;

            animations[i] = new BufferedImage[frames];

            for(int j = 0; j < frames; j++)
                animations[i][j] = images[i].getSubimage(j*128, 0, 128, 128);
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
