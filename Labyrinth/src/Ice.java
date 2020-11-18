import java.util.ArrayList;

/**
 * ices appropriate tiles
 */

public class Ice extends ActionTile {

    public Ice(String imgPath) {
        super(imgPath);
    }

    /**
     * sets the FloorTiles iced
     * @param player the player who called this class, it is not needed but otherwise it would not work in other classes
     */
    @Override
    public void ActionTile(Player player) {
        ArrayList<FloorTile> iced = Game.getEffectedTiles(true);
        for (FloorTile i: iced) {
            i.setIce();
            i.setIceForNextTurns(Game.numOfPlayers + Game.currentTurn);
        }
    }
}
