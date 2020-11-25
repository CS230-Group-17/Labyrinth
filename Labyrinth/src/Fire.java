import java.util.ArrayList;

/**
 * sets appropriate tiles on fire
 * @author Marijus Gudiskis 1901701
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
        FloorTile[][] temp = Game.getEffectedTiles(true);
        if(isIsSafeToSetFire(temp)) {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    temp[i][j].isOnFire = true;
                    temp[i][j].isOnFireForTheNextNTurns = Game.currentTurn + (2 * Game.numOfPlayers);
                }
            }
        }else {
            System.out.println("there are players in that area");
        }
    }

    /**
     * checks if in the given field there were players
     * @param field the 3 by 3 filed on the board
     * @return true if there are no players on the field and false otherwise
     */
    private boolean isIsSafeToSetFire(FloorTile[][] field){
        boolean noPlayers = true;
        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for(int k = 0; k < Game.numOfPlayers; k++) {
                    if(Game.players[k].getPosition() == field[i][j]) {
                        noPlayers = false;
                    }
                }
            }
        }

        return noPlayers;
    }
}
