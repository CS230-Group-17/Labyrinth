import java.util.ArrayList;
//this is for testing only, but you can take this if needed
public class Game {
    public static int numOfPlayers;
    public static int currentTurn;
    public static Player[] players = new Player[4];//this for testing
    public static ArrayList<ArrayList<FloorTile>> board = new ArrayList<ArrayList<FloorTile>>();//FloorTile

    public Player getPlayer(int index) {
        return players[index];
    }

    public static Player getEffectedPlayers(Player hostPlayer) {
        //the chosen player will be returned
        return players[0];
    }

    public static FloorTile getEffectedTiles(boolean fire) {
        //the player selects the tiles on which the file or ice is set, before that is chechks if
        //any player are standing on those tiles if this is for fire
    }
}
