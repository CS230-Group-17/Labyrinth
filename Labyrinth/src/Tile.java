/**
 * abstract class to help enforce other tile classes
 * @author Marijus Gudiskis 1901701
 */
public abstract class Tile {
    private String imgPath;

    public Tile(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImg() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    };
}
