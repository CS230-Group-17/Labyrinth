package sample;

import javafx.scene.image.Image;

/**
 * abstract class to help enforce other tile classes
 * @author Marijus Gudiskis 1901701
 */
public abstract class Tile {
    private String imgPath;
    public Image tileImage;

    public String getName() {
        return name;
    }

    private String name;

    public Tile(String imgPath, Image tileImage, String name) {
        this.imgPath = imgPath;
        this.tileImage = tileImage;
        this.name = name;
    }

    public String getImg() {
        return imgPath;
    }

    public Image getImage(){
        return tileImage;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    };
}
