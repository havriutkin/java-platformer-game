package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "JavaPlatformer/res/Main Character/MC_ATLAS.png";
    
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        File file = new File(fileName);

        try{
            img = ImageIO.read(file);
        } catch (IOException e){ 
            e.printStackTrace();
        }

        return img;
    }
}
