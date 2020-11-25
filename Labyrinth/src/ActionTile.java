import java.util.SplittableRandom;

/**
 * abstract action tile for other action tiles to follow
 * @author Marijus Gudiskis 1901701
 */
public abstract class ActionTile extends Tile {

    public ActionTile(String imgPath) {
        super(imgPath);
    }


    /**
     * united method that all action tiles will use
     * @param player the player who calls this class
     */
    public abstract void ActionTile(Player player);
}
