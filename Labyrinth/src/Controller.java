package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Controller {
	public static int xtile = -1;
	public static int ytile = -1;
    @FXML private GridPane actionCol;
    @FXML private GridPane boardCol;
    @FXML private ImageView currTile;
    @FXML private Label infoLable;
    @FXML private GridPane playerCol;
    @FXML private Label playerTurnLable;
    @FXML private Label turnLable;
    @FXML private Label turnsLable;
    private Button[][] currentTiles = new Button[Game.board.length][Game.board[0].length];
    private Button[] actionButton = new Button[3];
    private Button[] playerButtons = new Button[Game.numOfPlayers];
    private ImageView[] playersOnBoard = new ImageView[Game.numOfPlayers];
    private int curAcion = 0;
    private int selectedActionTile = -1;
    private int chosenPlayer = -1;
    private String fire = "C:\\Users\\marij\\Downloads\\fire.png";
    private String ice = "C:\\Users\\marij\\Downloads\\ice.png";
    private String fireandice = "C:\\Users\\marij\\Downloads\\fireandice.jpg";
    private String fixedTilePath = "C:\\Users\\marij\\Downloads\\blue.jpg";
    private Image iceandFireIm;
    private Image iceIm;
    private Image fireIm;
    private Tile curentTile;
    private Image fixedTile;
    private boolean insertOnce;
    private boolean usedAction;
    private boolean madeAMove;


    @FXML
    public void initialize() throws FileNotFoundException {
    	prepForUI();

        iceandFireIm = new Image(new FileInputStream(fireandice));
        iceIm = new Image(new FileInputStream(ice));
        fireIm = new Image(new FileInputStream(fire));
        fixedTile = new Image(new FileInputStream(fixedTilePath));

        displayPlayers();

        refreshTheBoard();
    }

    @FXML
    public void refreshTheBoard() {
    	boardCol.setMinSize(50,50);
        boardCol.getChildren().clear();
        //boardCol.setGridLinesVisible(true);

        int x = 0;
        int y = 0;
        for(int j = -1; j < Game.board[0].length+1; j++) {
        	for (int i = -1; i < Game.board.length+1; i++) {
        		if(i >= 0 && j >= 0 && j < Game.board[0].length && i < Game.board.length) {

        			ImageView imageView = new ImageView(Game.board[i][j].getImage());
                    imageView.setFitWidth(50);
                    imageView.setPreserveRatio(true);
                    imageView.setBlendMode(BlendMode.MULTIPLY);

                    Group blend;


                    Button button = new Button();
                    button.setStyle("-fx-padding: 0");
                    button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    if(Game.board[i][j].isFrozen && Game.board[i][j].isOnFire) {

                    	ImageView imageViewIceandFire = new ImageView(iceandFireIm);
                        imageViewIceandFire.setFitWidth(50);
                        imageViewIceandFire.setPreserveRatio(true);

                        blend = new Group(imageViewIceandFire, imageView);




                    }else if(Game.board[i][j].isFrozen) {

                        ImageView imageViewIce = new ImageView(iceIm);
                        imageViewIce.setFitWidth(50);
                        imageViewIce.setPreserveRatio(true);

                        blend = new Group(imageViewIce, imageView);


                    } else if(Game.board[i][j].isOnFire) {

                        ImageView imageViewFire = new ImageView(fireIm);
                        imageViewFire.setFitWidth(50);
                        imageViewFire.setPreserveRatio(true);
                        blend = new Group(imageViewFire, imageView);


                    }else {
                    	blend = new Group(imageView);
                    }
                    button.setGraphic(blend);

                    int finalI = i;
                    int finalJ = j;
                    button.setOnAction(value -> {
                    	xtile = finalJ;
                        ytile = finalI;
                        if(Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile).ActionTile(Game.players[Game.currentTurn % Game.numOfPlayers])) {
                            infoLable.setText("");
                            if(curAcion > 0) {
                                curAcion--;
                            }
                            Game.players[Game.currentTurn % Game.numOfPlayers].removeHandTile(Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile));
                            updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
                        }else {
                            infoLable.setText("there are players in that area");
                            usedAction = false;
                        }
                        selectedActionTile = -1;
                        tileLoop(true);

                        xtile = -1;
                        ytile = -1;
                        refreshTheBoard();

                    });
                    button.setDisable(true);
                    currentTiles[i][j] = button;
                    boardCol.add(button, x, y, 1, 1);


                    for(int k = 0; k < Game.players.length; k++) {
                    	if(Game.players[k].getPosition() == Game.board[i][j]) {
                    		ImageView imageViewPlay = new ImageView(Game.players[k].getPlayerImage());
                            imageViewPlay.setFitWidth(50);
                            imageViewPlay.setPreserveRatio(true);
                            imageViewPlay.toFront();
                            playersOnBoard[k] = imageViewPlay;
                            boardCol.add( imageViewPlay, x, y, 1, 1);
                        }
                    }

                }else if(!(x == 0 && y == 0) && !(x == 0 && y == Game.board.length+1) &&
                		(!(x == Game.board[0].length+1 && y == 0) && !(x == Game.board[0].length+1 && y == Game.board.length+1))) {
                    boolean willChange = true;
                    if((x == 0 || x == Game.board[0].length+1) && y < Game.board.length+1 && y > 0){

                        for(int k = 0; k < Game.board[0].length; k++) {

                            if(Game.board[y-1][k].isFrozen || Game.board[y-1][k].isFixedTile()) {
                                willChange = false;
                            }
                        }
                    }
                    else if((y == 0 || y == Game.board.length+1) && x < Game.board[0].length+1 && x > 0) {

                    	for(int k = 0; k < Game.board.length; k++) {

                            if(Game.board[k][x-1].isFrozen || Game.board[k][x-1].isFixedTile()) {
                                willChange = false;
                            }
                        }
                    }
                    if(willChange) {
                        if(!madeAMove && !usedAction && !(curentTile instanceof ActionTile)) {
                            insertOnce = true;
                        }

                        Button button1 = new Button("insert");

                        int finalY = y;
                        int finalX = x;
                        button1.setOnAction(value -> {
                            int yy = finalY;
                            int xx = finalX;
                            if(finalY == 0) {
                                //yy++;
                            } else if(yy == currentTiles.length+1) {
                                yy = yy - 2;
                            } else {
                                yy = yy - 1;
                            }
                            if(xx == 0){
                                //xx++;
                            } else if(xx == currentTiles[0].length+1) {
                                xx = xx - 2;
                            } else{
                                xx = xx - 1;
                            }
                            if(insertOnce && curentTile instanceof FloorTile) {
                                if (Game.slideTile(yy, xx, (FloorTile) curentTile)) {
                                    refreshTheBoard();
                                }
                                insertOnce = false;
                            }

                        });
                        boardCol.add(button1, x, y, 1, 1);
                    } else {
                        Button inv = new Button("");
                        inv.setVisible(false);
                        boardCol.add(inv, x, y, 1, 1);
                    }
                }
                y++;
            }
            y=0;
            x++;
            infoLable.setText("");
        }


        if(!Game.canPlayerMove(Game.players[Game.currentTurn % Game.numOfPlayers]) && !madeAMove) {
            madeAMove = true;
        }

    }

    @FXML
    private void nextTurn() {

        if((insertOnce && curentTile instanceof FloorTile) ) {
            infoLable.setText("You must insert the FloorTile that you have!");
        } else {
            if(!madeAMove) {
                infoLable.setText("You must make a move!");
            } else if(curentTile instanceof ActionTile) {
            	Game.players[Game.currentTurn % Game.numOfPlayers].addHand((ActionTile) curentTile);
                Game.currentTurn++;
                prepForUI();
                Game.removeIceAndFire();
                refreshTheBoard();
            } else {
                Game.currentTurn++;
                prepForUI();
                Game.removeIceAndFire();
                refreshTheBoard();
            }

        }
    }

    @FXML
    private void keyPressed(KeyEvent evt) {

        if(Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn() > 0 && !madeAMove && !insertOnce) {
            switch (evt.getCode()) {
                case UP:
                    if(Game.movePlayer(Game.players[Game.currentTurn % Game.numOfPlayers], false, false, false, true)) {
                        movmentUpdate();
                    }
                    break;
                case DOWN:
                    if(Game.movePlayer(Game.players[Game.currentTurn % Game.numOfPlayers], false, false, true, false)) {
                        movmentUpdate();
                    }
                    break;
                case LEFT:
                    if(Game.movePlayer(Game.players[Game.currentTurn % Game.numOfPlayers], false, true, false, false)) {
                        movmentUpdate();
                    }
                    break;
                case RIGHT:
                    if(Game.movePlayer(Game.players[Game.currentTurn % Game.numOfPlayers], true, false, false, false)) {
                        movmentUpdate();
                    }
                    break;
            }

        }

        if(insertOnce && Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn() > 0) {
            infoLable.setText("You must insert a tile before moving");
        }
    }

    private void movmentUpdate() {
    	Game.players[Game.currentTurn % Game.numOfPlayers].setMovesPerTurn(Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn() -1);
        turnsLable.setText("Moves left:" + Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn());
        if(Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn() == 0) {
            madeAMove = true;
        }
        refreshTheBoard();

    }

    private void prepForUI() {
        Game.players[Game.currentTurn % Game.numOfPlayers].setMovesPerTurn(1);


        infoLable.setText("");
        turnsLable.setText("Moves left:" + Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn());
        turnLable.setText("Turn: " + Game.currentTurn);
        playerTurnLable.setText("It's " + Game.players[Game.currentTurn % Game.numOfPlayers].getPlayerProfile().getName() + " turn");

        curentTile = Game.silkBag.get(0);
        currTile.setImage(curentTile.getImage());
        Game.silkBag.remove(0);
        currTile.setFitWidth(75);
        currTile.setPreserveRatio(true);

        insertOnce = false;
        usedAction = false;
        madeAMove = false;
        //insertOnce = true;
        curAcion = 0;


        updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
    }

    private void displayPlayers() {
        //playerCol
        for (int k = 0; k < Game.players.length; k++) {
            Button button = new Button();
            ImageView imageView = new ImageView(Game.players[k].getPlayerImage());
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);

            int finalK = k;
            button.setOnAction(value -> {
                chosenPlayer = finalK;
                playerLoop(true);
                runbackTrack();
                infoLable.setText("");
                Game.players[Game.currentTurn % Game.numOfPlayers].removeHandTile(Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile));
                if(curAcion > 0) {
                    curAcion--;
                }
                updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
                refreshTheBoard();
            });
            button.setStyle("-fx-padding: 0");
            button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            button.setDisable(true);
            playerCol.add(button, 0, k, 1, 1);
            playerButtons[k] = button;
        }
    }


    @FXML
    public void nextAct() {
        if(Game.players[Game.currentTurn % Game.numOfPlayers].sizeOfHand() > curAcion + 3) {
            curAcion++;
        }
        updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
    }

    @FXML
    public void prewAct() {
        if(curAcion > 0) {
            curAcion--;
        }
        updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
    }




    private void tileLoop(boolean statement) {
        for(int k = 0; k < Game.board.length; k++) {
            for (int j = 0; j < Game.board[0].length; j++) {
                currentTiles[k][j].setDisable(statement);
            }
        }
    }


    private void updateActionRow(int player, int hand) {
        actionCol.getChildren().clear();
        actionCol.setGridLinesVisible(true);

        for(int i = 0; i < Game.players[player].sizeOfHand() && i < 3; i++) {

            ImageView imageView = new ImageView(Game.players[player].getHand(i + hand).getImage());
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            Button button = new Button();
            button.setGraphic(imageView);
            int finalI = i;
            button.setOnAction(value -> {
                if(!usedAction && !madeAMove && !insertOnce) {
                    selectedActionTile = finalI + hand;
                    if (Game.players[player].getHand(finalI + hand) instanceof Fire ||
                            Game.players[player].getHand(finalI + hand) instanceof Ice) {
                        if (xtile == -1 || ytile == -1) {
                            infoLable.setText("select tile on which you will cast your action");
                            tileLoop(false);

                        }
                    } else if (Game.players[player].getHand(finalI + hand) instanceof Backtrack) {

                        if (playerLoop(false)) {
                            infoLable.setText("select the player");
                        } else {
                            infoLable.setText("you have used backTrack on everyone");
                        }
                    } else {
                        Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile).ActionTile(Game.players[Game.currentTurn % Game.numOfPlayers]);
                        Game.players[Game.currentTurn % Game.numOfPlayers].removeHandTile(Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile));

                        if (curAcion > 0) {
                            curAcion--;
                        }
                        updateActionRow(Game.currentTurn % Game.numOfPlayers, curAcion);
                        turnsLable.setText("Moves left:" + Game.players[Game.currentTurn % Game.numOfPlayers].getMovesPerTurn());
                    }
                    usedAction = true;
                } else if (usedAction) {
                    infoLable.setText("you have used action tile already");
                } else if (madeAMove) {
                    infoLable.setText("you cant use action tile after you moved");
                } else {
                    infoLable.setText("you must first insert a tile");
                }
            });
            actionButton[i] = button;
            actionCol.add(button, i, 0, 1, 1);
        }
    }

    private boolean playerLoop(boolean statement) {
        boolean canIperform = false;
        Player[] temp = Game.players[Game.currentTurn % Game.numOfPlayers].getPrevBtPlayers();
        for(int i = 0; i < Game.numOfPlayers; i++) {
            if(Game.currentTurn % Game.numOfPlayers != i){
                boolean doseNotExist = true;
                for(int j = 0; j < temp.length; j++) {
                    if(temp[j] != null){
                        if(Game.players[i] == temp[j]) {
                            doseNotExist = false;
                        }
                    }
                }
                if(doseNotExist) {
                    playerButtons[i].setDisable(statement);
                    canIperform = true;
                }
            }

        }
        return canIperform;
    }

    private void runbackTrack() {
        Game.players[Game.currentTurn % Game.numOfPlayers].getHand(selectedActionTile).ActionTile(Game.players[chosenPlayer]);
        Game.players[Game.currentTurn % Game.numOfPlayers].addPrevBtPlayers(Game.players[chosenPlayer]);
        chosenPlayer = -1;
    }


}
