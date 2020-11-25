import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void printPlace(Game itsAGame){
        for(int i = 0; i < itsAGame.board.length; i++){
            for(int j = 0; j < itsAGame.board[i].length; j++) {
                boolean isPlayer = false;
                for (int o = 0; o < itsAGame.numOfPlayers; o++){
                    if(itsAGame.players[o].getPosition() == itsAGame.board[i][j]) {
                        isPlayer = true;
                    }
                }
                if(isPlayer) {
                    System.out.print("*");
                }
                System.out.print("  |  ");
            }
            System.out.println();
        }
    }

    public static void printBoard(Game itsAGame){
        for(int i = 0; i < itsAGame.board.length; i++){
            for(int j = 0; j < itsAGame.board[i].length; j++) {
                if(itsAGame.board[i][j].isFrozen) {
                    System.out.print("*");
                }
                System.out.print("  |  ");
            }
            System.out.println();
        }
    }

    public static void printWhereIceIsPresent(Game itsAGame){
        for(int i = 0; i < itsAGame.board.length; i++){
            for(int j = 0; j < itsAGame.board[i].length; j++) {
                System.out.print(itsAGame.board[i][j].name + "       ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        //board
        FloorTile[][] boardTemp = {{new FloorTile("path", true, false, true, true, false, "L Shape"), new FloorTile("path", false, false, true, false, true, "- Shape"), new FloorTile("path", true, false, false, true, true, "re L Shape")},
                {new FloorTile("path", true, false, true, true, true, "T Shape"), new FloorTile("path", false, true, true, true, true, "+ Shape"), new FloorTile("path", true, true, false, true, true, "T  Shape")},
                {new FloorTile("path", true, true, true, false, false, "L Shape"), new FloorTile("path", false, false, false, true, false, "I Shape"), new FloorTile("path", true, true, false, false, true, "L Shape")},
        };
        //creating the players and the game it self
        ArrayList<Tile> silkbag = new ArrayList<Tile>();
        silkbag.add(new FloorTile("path", true, false, true, true, true, "T Shape"));
        silkbag.add(new DoubleMove("path"));
        PlayerProfile bob = new PlayerProfile(0, 1, "bob", 1);
        PlayerProfile sam = new PlayerProfile(1, 0, "sam", 1);
        Player player1 = new Player("path", bob, boardTemp[1][1]);
        Player player2 = new Player("path", sam, boardTemp[2][2]);
        Player[] playersArr = {player1, player2};
        Game itsAGame = new Game(playersArr, boardTemp, boardTemp[1][1], silkbag, 0, 2);


        //testing player movement
        //printPlace(itsAGame);
        //System.out.println(itsAGame.movePlayer(itsAGame.players[1], false, false, false, true));
        //printPlace(itsAGame);

        //testing inserting new tile
        //printBoard(itsAGame);
        //FloorTile tempas = new FloorTile("path", true, false, true, true, true, "the new Tile");
        //System.out.println(itsAGame.slideTile(2,2, tempas));
        //printBoard(itsAGame);

        //testing backTrack action tile
        /*
        printPlace(itsAGame);
        System.out.println(itsAGame.movePlayer(itsAGame.players[0], true, false, false, false));
        System.out.println(itsAGame.movePlayer(itsAGame.players[0], true, false, false, false));
        System.out.println(itsAGame.movePlayer(itsAGame.players[0], false, false, true, false));
        printPlace(itsAGame);
        Backtrack back = new Backtrack("path");
        back.ActionTile(itsAGame.players[0]);
        System.out.println("going back");
        printPlace(itsAGame);
         */

        //testing action tile DoubleMove
        /*
        System.out.println(itsAGame.getPlayer(0).getMovesPerTurn());
        DoubleMove temp = new DoubleMove("path");
        temp.ActionTile(itsAGame.getPlayer(0));
        System.out.println(itsAGame.getPlayer(0).getMovesPerTurn());
         */

        //testing action tile fire
        /*
        Fire temp = new Fire("path");
        temp.ActionTile(itsAGame.getPlayer(0));
        //and now try changing the player location
        System.out.println(itsAGame.getTileFromTheBoard(0,0).isOnFire);
         */

        //testing ice action tile
        /*
        printBoard(itsAGame);
        Ice temp = new Ice("Path");
        System.out.println("now freezing it");
        temp.ActionTile(itsAGame.getPlayer(0));
        System.out.println("now freezing it");
        printBoard(itsAGame);
        System.out.println("try to move a column that I could before");
        FloorTile tempas = new FloorTile("path", true, false, true, true, true, "the new Tile");
        System.out.println(itsAGame.slideTile(0,1, tempas));
         */

        //testing if a player is on a goal tile
        //change the players position to the same one as the goals one
        //System.out.println(itsAGame.hasWonTheGame());


    }

}
