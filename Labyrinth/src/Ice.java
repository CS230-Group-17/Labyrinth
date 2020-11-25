import java.util.ArrayList;

/**
 * ices appropriate tiles
 * @author Marijus Gudiskis 1901701
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
        FloorTile[][] temp = Game.getEffectedTiles(true);

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                temp[i][j].isFrozen = true;
                temp[i][j].isFrozenForTheNextNTurns = Game.currentTurn + Game.numOfPlayers;
            }
        }
    }
}
