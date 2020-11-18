import java.util.ArrayList;

/**
 * sets appropriate tiles on fire
 */
public class Fire extends ActionTile {
    public Fire(String imgPath) {
        super(imgPath);
    }

    /**
     * sets the FloorTiles on fire
     * @param player the player who called this class, it is not needed but otherwise it would not work in other classes
     */
    @Override
    public void ActionTile(Player player) {
        ArrayList<FloorTile> onFire = Game.getEffectedTiles(true);
        for (FloorTile i: onFire) {
            i.setOnFire();
            i.setOnFireForNextTurns(Game.numOfPlayers + Game.currentTurn);
        }
    }
}
