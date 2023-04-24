package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "JavaPlatformer/res/Main Character/Atlas/MC_ATLAS.png";
    public static final String PLAYER_DIR = "JavaPlatformer/res/Main Character/Animations";

    // Function finds and returns one file with given filename, that represents sprite atlas
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

    // Function returns array of images (sprites) from given directory. Sprites will be sorted in alphabetic order
    public static BufferedImage[] GetSpriteArray(String dirPath){
        BufferedImage[] result = null;
        File directory = new File(dirPath);     // Get directory
        File[] files = directory.listFiles();   // Get array of files in given directory
        
        // Sort files by alphabetic order
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2){
                return f1.getName().compareTo(f2.getName());
            }
        });

        // Load files as images
        result = new BufferedImage[files.length];
        for (int i = 0; i < files.length; i++) {
            try {
                result[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
