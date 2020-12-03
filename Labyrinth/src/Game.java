package sample;

import java.util.ArrayList;

/**
 * controls the main processes of the game(moves the player, slides the tile and so on)
 * @author Marijus Gudiskis 1901701
 */
public class Game {
    public static int numOfPlayers;
    public static int currentTurn;
    public static Player[] players;//this for testing
    public static FloorTile[][] board;//FloorTile
    private FloorTile goalTile;
    public static ArrayList<Tile> silkBag = new ArrayList<Tile>();

    public Game(Player[] players, FloorTile[][] board, FloorTile goalTile,
                ArrayList<Tile> silkBag, int currentTurn, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.currentTurn = currentTurn;
        this.players = players;
        this.board = board;
        this.silkBag = silkBag;
        this.goalTile = goalTile;
    }

    public void nextTurn(){
        removeIceAndFire();
        currentTurn++;
    }
    public FloorTile getTileFromTheBoard(int row, int col) {
        return board[row][col];
    }

    /**
     * slides the tile if the row or column is not fixed or frozen. Row represents in which row you want to insert and
     * col tell in which column to insert, so a row:0 and col:2 will insert from the top at column 2 and row:2 and
     * col:3(max_num_of_col_ will insert from right in the row 2 or row:3(max_num_of_row) col:3 will insert at col
     * 3 from the bottom. The numbers in example ar arbitrary
     * @param row the row where it is wanted to insert
     * @param col the col where it is wanted to insert
     * @param newTile the new tile
     * @return returns true if successful, false otherwise
     */
    static public Boolean slideTile(int row, int col, FloorTile newTile) {
        FloorTile oldTile1 = newTile;
        FloorTile oldTile2;
        boolean isNotFixed = true;
        if(row == 0 || row == board.length-1){//determine that the columns will be changed
            for (int i = 0; i < board.length; i++) {//check if there are any fixed or frozen tiles in the col
                if(board[i][col].isFixedTile() || board[i][col].isFrozen){
                    isNotFixed = false;
                }
            }
            //move the tiles
            if(row == 0 && isNotFixed) {

                for(int i = 0; i < board.length; i++) {
                    oldTile2 = board[i][col];
                    board[i][col] = oldTile1;
                    oldTile1 = oldTile2;
                }
            }else if(isNotFixed) {

                for(int i = board.length-1; i >= 0; i--) {
                    oldTile2 = board[i][col];
                    board[i][col] = oldTile1;
                    oldTile1 = oldTile2;
                }
            }else {
                return false;
            }
        }else {//determine that the rows will be changed

            for (int i = 0; i < board[row].length; i++) {
                if(board[row][i].isFixedTile() || board[i][col].isFrozen){
                    isNotFixed = false;
                }
            }

            if(col == 0  && isNotFixed) {
                for(int i = 0; i < board[row].length; i++) {
                    oldTile2 = board[row][i];
                    board[row][i] = oldTile1;
                    oldTile1 = oldTile2;
                }

            }else if(isNotFixed) {
                for(int i = board[row].length-1; i >= 0; i--) {
                    oldTile2 = board[row][i];
                    board[row][i] = oldTile1;
                    oldTile1 = oldTile2;
                }
            }else {
                return false;
            }

        }
        //if a player was standing on that tile it will be relocated to a new tile
        for(int i = 0; i < numOfPlayers; i++) {
            if(oldTile1 == players[i].getPosition()) {
                players[i].updateGetBackTiles(players[i].getPosition());
                players[i].setPosition(newTile);
            }
        }
        oldTile1.isFrozen = false;
        oldTile1.isOnFire = false;
        oldTile1.isFrozenForTheNextNTurns = 0;
        oldTile1.isOnFireForTheNextNTurns = 0;
        silkBag.add(oldTile1);
        return true;
    }

    /**
     * before moving a player it checks if it can move on that tile and makes sure that the player
     * doesn't leave the board
     * @param player the player that is moving
     * @param right is it going right
     * @param left is it going left
     * @param down is it going down
     * @param up is it going up
     * @return returns true if successful, false otherwise
     */
    static public boolean movePlayer(Player player, boolean right, boolean left, boolean down, boolean up) {

        int playerX = 0;
        int playerY = 0;
        int nX;
        int nY;
        //finding the player on the board
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(player.getPosition() == board[i][j]) {
                    playerX = i;
                    playerY = j;
                }
            }
        }
        //making sure that the player stays on the board
        if(playerY == board[0].length-1 && right) {
            return false;
        }else if(playerY == 0 && left) {
            return false;
        }else if(playerX == 0 && up) {
            return false;
        }else if(playerX == board.length-1 && down) {
            return false;
        }else if(right) {
            nX = playerX;
            nY = playerY+1;
            if(board[nX][nY].west && board[playerX][playerY].east && !board[nX][nY].isOnFire){//if the tile will accept the player
                player.updateGetBackTiles(player.getPosition());
                player.setPosition(board[nX][nY]);
                return true;
            }
        }else if(left) {
            nX = playerX;
            nY = playerY-1;
            if(board[nX][nY].east && board[playerX][playerY].west && !board[nX][nY].isOnFire){
                player.updateGetBackTiles(player.getPosition());
                player.setPosition(board[nX][nY]);
                return true;
            }
        }else if(up) {
            nX = playerX-1;
            nY = playerY;
            if(board[nX][nY].south && board[playerX][playerY].north && !board[nX][nY].isOnFire){
                player.updateGetBackTiles(player.getPosition());
                player.setPosition(board[nX][nY]);
                return true;
            }
        }else if(down) {
            nX = playerX+1;
            nY = playerY;
            if(board[nX][nY].north && board[playerX][playerY].south && !board[nX][nY].isOnFire){
                player.updateGetBackTiles(player.getPosition());
                player.setPosition(board[nX][nY]);
                return true;
            }
        }
        return false;
    }

    /**
     *removes the fire and Ice from the board after certain turns
     */
    public static void removeIceAndFire(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].isOnFireForTheNextNTurns == currentTurn){
                    board[i][j].isOnFire = false;
                }
                if(board[i][j].isFrozenForTheNextNTurns == currentTurn){
                    board[i][j].isFrozen = false;
                }
            }
        }
    }

    /**
     * checks who has won te game
     * @return the player who has won the game
     */
    public Player hasWonTheGame() {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for(int k = 0; k < numOfPlayers; k++) {
                    if(players[k].getPosition() == board[i][j]){
                        return players[k];
                    }
                }
            }
        }
        return null;
    }

    public Player[] getPlayers() {
        return players;
    }

    public FloorTile getGoalTile() {
        return goalTile;
    }

    public void removeFromSilkBag() {
        silkBag.remove(0);
    }

    public static boolean canPlayerMove(Player player){
        int playerX = 0;
        int playerY = 0;
        int nX;
        int nY;
        //finding the player on the board
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(player.getPosition() == board[i][j]) {
                    playerX = i;
                    playerY = j;
                }
            }
        }
        //making sure that the player stays on the board

        if(playerY != board[0].length-1) {
            nX = playerX;
            nY = playerY + 1;
            if (board[nX][nY].west && board[playerX][playerY].east && !board[nX][nY].isOnFire) {//if the tile will accept the player
                return true;
            }
        }

        if(playerY != 0) {
            nX = playerX;
            nY = playerY - 1;
            if (board[nX][nY].east && board[playerX][playerY].west && !board[nX][nY].isOnFire) {
                return true;
            }
        }

        if(playerX != 0) {
            nX = playerX - 1;
            nY = playerY;
            if (board[nX][nY].south && board[playerX][playerY].north && !board[nX][nY].isOnFire) {
                return true;
            }
        }

        if(playerX != board.length-1) {
            nX = playerX + 1;
            nY = playerY;
            if (board[nX][nY].north && board[playerX][playerY].south && !board[nX][nY].isOnFire) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Tile> getSilkBag() {
        return silkBag;
    }

    /**
     * returns an ordered larboard
     * @return array of player profiles
     */
    public PlayerProfile[] getLeaderboardOrdered() {
        PlayerProfile[] temp = new PlayerProfile[players.length];
        for(int i = 0; i < players.length; i++) {
            temp[i] = players[i].getPlayerProfile();
        }
        int n = temp.length;
        //bubble sort...
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (temp[j].getWins() > temp[j+1].getWins())
                {
                    // swap arr[j+1] and arr[j]
                    PlayerProfile temp2 = temp[j];
                    temp[j] = temp[j+1];
                    temp[j+1] = temp2;
                }
        return temp;
    }

    public FloorTile[][] getBoard(){
        return board;
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public static Player getEffectedPlayers(Player efPlayer) {
        //the chosen player will be returned
        return efPlayer;
    }

    public static ArrayList<FloorTile> getEffectedTiles() {

        ArrayList<FloorTile> temp = new ArrayList<>();
        for(int i = Controller.ytile-1; i < Controller.ytile + 2; i++) {
            for(int j = Controller.xtile-1; j < Controller.xtile + 2; j++) {
                if(i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    temp.add(board[i][j]);
                }
            }
        }
        return temp;
    }
}
