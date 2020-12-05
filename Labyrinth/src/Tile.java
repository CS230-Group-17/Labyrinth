package sample;

import javafx.scene.image.Image;

/**
 * abstract class to help enforce other tile classes
 * @author Marijus Gudiskis 1901701
 */
public abstract class Tile {
    private String imgPath;
    public Image tileImage;

    public Tile(String imgPath, Image tileImage) {
        this.imgPath = imgPath;
        this.tileImage = tileImage;
    }

    public String getImg() {
        return imgPath;
    }

    public Image getImage() {
        return tileImage;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    };
}
