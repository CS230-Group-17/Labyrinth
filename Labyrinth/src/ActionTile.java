import java.util.SplittableRandom;

/**
 * abstract action tile for other action tiles to follow
 */
public abstract class ActionTile {
    private final String imgPath;

    public ActionTile(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    /**
     * united method that all action tiles will use
     * @param player the player who calls this class
     */
    public abstract void ActionTile(Player player);
}
