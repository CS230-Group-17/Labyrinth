import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;  
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/* Class that hanldes the reading and writing to files associated to:
 * - The Game (readNew, save, load)
 * - The Player Profiles (read, delete, create, updateAll)
 * @Author Alex Warren 851864, Oliver Hart 990839 
 */

public class File {
	
	/**
	 * Method to read new game
	 * @param selectedMap - the map specified to load 
	 * @return an instance of the Game class instatiated from the selectedMap argument
	 */
	
	
	private String localisedDirStruct_PlayerProfile = "";
	private String localisedDirStruct_Game = "";
	private String localisedDirStruct_MAPS = "";
	
	public Game readNewGame(String selectedMap) {
		//game arguments / game data
		ArrayList<Player> Players = new ArrayList<Player>();
		FloorTile goalTile = new FloorTile(true, true, true, true, true);
		ArrayList<Tile> silkBag = new ArrayList<Tile>();
		ArrayList<FixedTile> fixedTiles = new ArrayList<FixedTile>();
		Tile[][] board = new Tile[][]();
		ArrayList<int[]> playerLocations = new ArrayList<int[]>();

		//board data
		double xSize, ySize;
		ArrayList<Tile> fixedTiles = new ArrayList<Tile>();
		int numberOfStraights = 0;
		int numberOfCorners = 0;
		int numberOfTShapes = 0;
		int numberOfFireTiles = 0;
		int numberOfIceTiles = 0;
		int numberOfBacktrackTiles = 0;
		int numberOfDoubleMoveTiles = 0;

		
		//scanner file start
		File mapData = new File(localisedDirStruct_MAPS + selectedMap);
		Scanner mapScanner = new Scanner(mapData);
		
		
		//read size of board (x,y)
		String boardSizeData = mapScanner.nextLine();
		String[] xySizeData = boardSizeData.split(",");
		xSize = Double.parseDouble(xySizeData[0]);
		ySize = Double.parseDouble(xySizeData[1]);
		
		
		/*read fixed tiles (Int[x], Int[y], Boolean [direction_north], 
		 * 					Boolean [direction_east], Boolean [direction_south], 
		 * 					Boolean [direction_west],	 Boolean [isFixed])
		 */
		String fixedTilesData = mapScanner.nextLine();
		String[] fixedTilesStringData = fixedTilesData.split("$");
		
		for (String curFixedTile: fixedTilesStringData) {
			String[] curArgs = curFixedTile.split(",");
			FixedTile FixedTileObj = new FixedTile(Boolean.parseBoolean(curArgs[2]), Boolean.parseBoolean(curArgs[3]), Boolean.parseBoolean(curArgs[4]), Boolean.parseBoolean(curArgs[5]),
										 		   Boolean.parseBoolean(curArgs[6]), Integer.parseInt(curArgs[0]), Integer.parseInt(curArgs[1]));
					
			fixedTiles.add(FixedTileObj);
		}
		
		
		//read player spawn locations
		String playerSpawnLocationsData = mapScanner.nextLine();
		String[] playerSpawnLocations = playerSpawnLocationsData.split("$");
		
		for (String playerSpawn: playerSpawnLocations) {
			String[] curArgs = playerSpawn.split(",");
			
			playerLocations.add(Integer.parseInt(curArgs[0]),
								Integer.parseInt(curArgs[1]));
		}
		
		//read silk bag contents
		String silkBagData = mapScanner.nextLine();
		String[] silkBagContents = boardSizeData.split(",");
		numberOfStraights = Integer.parseInt(silkBagContents[0]);
		numberOfCorners = Integer.parseInt(silkBagContents[1]);
		numberOfTShapes = Integer.parseInt(silkBagContents[2]);
		numberOfFireTiles = Integer.parseInt(silkBagContents[3]);
		numberOfIceTiles = Integer.parseInt(silkBagContents[4]);
		numberOfBacktrackTiles = Integer.parseInt(silkBagContents[5]);
		numberOfDoubleMoveTiles = Integer.parseInt(silkBagContents[6]);
		
		mapScanner.close();
		
		//create silk bag
		//straight
		for (int i = 0; i < numberOfStraights; i++) {
			silkBag.add(new FloorTile(false, true, false, true, false));
		}
		
		//corner
		for (int i = 0; i < numberOfCorners; i++) {
			silkBag.add(new FloorTile(false, false, false, true, true));
		}

		//TShape
		for (int i = 0; i < numberOfTShapes; i++) {
			silkBag.add(new FloorTile(false, false, true, true, true));
		}

		//Fire
		for (int i = 0; i < numberOfFireTiles; i++) {
			silkBag.add(new ActionTile());
		}
		
		//Ice
		for (int i = 0; i < numberOfIceTiles; i++) {
			silkBag.add(new ActionTile());
		}
		
		//Backtrack
		for (int i = 0; i < numberOfBacktrackTiles; i++) {
			silkBag.add(new ActionTile());
		}
		
		//doubleMove
		for (int i = 0; i < numberOfDoubleMoveTiles; i++) {
			silkBag.add(new ActionTile());
		}
		
	
		//create board from silk bag
		//add tile to board then remove it from silk bag
		for (int i = 0; i < xSize; i++) {
			Tile[] currentRowOfBoard = new Tile[];
			for (int j = 0; i < xSize; j++) {
				int currentTile_index = i*xSize + j;
				Tile tileToBoard = silkBag.get(currentTile_index);
				if (tileToBoard != null) {
					currentRowOfBoard = addTileToEndOfRow(currentRowOfBoard, tileToBoard);
					silkBag_memory.set(currentTile_index, null);
				} 
			}
			board[i] = currentRowOfBoard;
		}
		
		return new Game(Players, board, goalTile, silkBag, 0, Players.size());
	}
	
    public static Tile[] addTileToEndOfRow(int arrayToAppend[], Tile element) 
    { 
        int i; 
 
        int outputArray[] = new int[n + 1]; 
        for (i = 0; i < n; i++) 
        	outputArray[i] = arrayToAppend[i]; 
 
        outputArray[arrayToAppend.length+1] = element; 
 
        return outputArray; 
    } 
 
	
	/**
	 * Method to save the game
	 * @param currentGame - The current game which will be saved
	 * @param saveName - The save name of the file
	 * @return a boolean value, stating whether the save method worked
	 */
	public boolean saveGame(Game currentGame, String saveName) {
		
		
        PrintWriter gameWriter = new PrintWriter(new File(localisedDirStruct_Game+saveName));
        
        //width and height of the board
        gameWriter.print( String.valueOf(currentGame.getWidthOfBoard()) + ", " + String.valueOf(currentGame.getHeightOfBoard()) );      

        //fixed tilesrn
		ArrayList<FixedTile> fixedTiles = currentGame.getFixedTiles(); // <u were here
		
		String fixedTilesString = "";
		for (FixedTile currentFixedTile : fixedTiles) {
			int xValue = currentFixedTile.getX();
			int yValue = currentFixedTile.getY();
			boolean direction_north = currentFixedTile.getNorth();
			boolean direction_east = currentFixedTile.getEast();
			boolean direction_south = currentFixedTile.getSouth();
			boolean direction_west = currentFixedTile.getWest();
			boolean isFixed = currentFixedTile.isFixedTile();
			fixedTilesString += (String.valueOf(xValue) + "," + String.valueOf(yValue) + "," + String.valueOf(direction_north)
			+ "," + String.valueOf(direction_east) + "," + String.valueOf(direction_south) + "," + String.valueOf(direction_west) + "," + String.valueOf(isFixed) + "$" );
		}
		
		gameWriter.println(fixedTilesString);
		//player spawns
		
		
		//board
		FloorTile[][] gameBoard = currentGame.getBoard();
		
		String boardString = "";
		for (FloorTile currentFloorTile : gameBoard) {
			boolean direction_north = currentFloorTile.getNoth();
			boolean direction_east = currentFloorTile.getEast();
			boolean direction_south = currentFloorTile.getSouth();
			boolean direction_west = currentFloorTile.getWest();
			boolean isFixed = currentFloorTile.isFixedTile();
			boardString += (String.valueOf(direction_north)+ "," + String.valueOf(direction_east) + "," 
			+ String.valueOf(direction_south) + "," + String.valueOf(direction_west) + "," + String.valueOf(isFixed) + "$");
		}
		
		gameWriter.println(boardString);
		
		
		//silkbag
		ArrayList<Tile> silkBag = currentGame.getSilkBag();
		
		int numOfStraight = 0;
		int numOfCorners = 0;
		int numOfTShape = 0;
		int numOfFireTile = 0;
		int numOfIceTile = 0;
		int numOfBacktrackTile = 0;
		int numOfDoubleMoveTiles = 0;
		
		for (Tile currentTile : silkBag) {
			if currentTile.getName() == "Straight" {
					numOfStraight++;
			} else if currentTile.getName() == "Corner" {
					numOfCorners++;
			} else if currentTile.getName() == "TShape" {
					numOfTShape++;
			} else if currentTile.getName() == "Fire" {
					numOfFireTile++;
			} else if currentTile.getName() == "Ice" {
					numOfIceTile++;
			} else if currentTile.getName() == "Backtrack" {
					numOfBacktrackTile++;
			} else if currentTile.getName() == "DoubleMove" {
					numOfDoubleMoveTiles++;
			}
		}
		
		gameWriter.println(String.valueOf(numOfStraight)+","+String.valueOf(numOfCorners)+","+String.valueOf(numOfTShape)+","+
				String.valueOf(numOfFireTile)+","+String.valueOf(numOfFireTile)+","+String.valueOf(numOfBacktrackTile)+","+String.valueOf(numOfDoubleMoveTiles));
		
		gameWriter.flush();  
		gameWriter.close();  
	}
	
	/**
	 * Method to load the game
	 * @param gameName - The name of the game to be loaded
	 * @return an instance of the Game class instatiated from the game relative to the gameName argument
	 */
	public Game loadGame(String gameName) {
		
	}
	
	/**
	 * Method to read a player profrile
	 * @param ProfileName - the player profile to read
	 * @return an instance of the PlayerProfile class instantiated from the data relative to the ProfileName argument
	 */
	public PlayerProfile readPlayerProfile(String ProfileName) {
		try {
			File playerProfileFile = new File(this.localisedDirStruct_PlayerProfile+ProfileName);
			Scanner scanner = new Scanner(playerProfileFile);
			String name = scanner.nextLine();
			int wins = Integer.parseInt(scanner.nextLine());
			int losses = Integer.parseInt(scanner.nextLine());
			int gamesPlayed = Integer.parseInt(scanner.nextLine());
			scanner.close();
			return new PlayerProfile(name, wins, losses, gamesPlayed);
			
		} catch (FileNotFoundException e) {
			System.out.println("Player profile specified does not exist.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to delete a player profile
	 * @param profileName - the player profile to be deleted
	 * @return a boolean value to show if the operation has been successful
	 */
	public Boolean deletePlayerProfile(String profileName) {
		File playerProfileFile = new File(this.localisedDirStruct_PlayerProfile + profileName); 
		playerProfileFile.delete();
	}
	
	/**
	 * Method to create a player profile
	 * @param name - the desired name of the plyaer profile
	 * @return An instance of PlayerProfile
	 */
	public PlayerProfile createPlayerProfile(String name) {
		File playerProfileFile = new File(this.localisedDirStruct_PlayerProfile + name); 
		
        PrintWriter playerProfileWriter = new PrintWriter(new File(this.localisedDirStruct_PlayerProfile + name));  

		playerProfileWriter.print(name);      
		playerProfileWriter.println(0);   
		playerProfileWriter.println(0);   
		playerProfileWriter.println(0);   
		playerProfileWriter.flush();  
		playerProfileWriter.close();  
		
		return new PlayerProfile(name, 0, 0, 0);
	}

	//redunant 
	/*
	 * 
	 * Method to update all playerProfiles
	 * @param players - An array of Players
	 * @return a boolean value to show if the operation has been successful
	public Boolean updateAllPlayerProfiles(Game currentGame) {
		Player[] players = currentGame.getPlayers();
		
		for (Player currentPlayer : players) {
			
			overwritePlayerProfile(currentPlayer);
			
		}
	}
	
	 * Method to overwite player
	 * @param currentPlayer
	public void overwritePlayerProfile(Player currentPlayer) {
		playerProfile currentPlayerProfile = currentPlayer.getPlayerProfile();
		
		String name = playerProfile.getName();
		int wins = playerProfile.getWins();
		int losses = playerProfile.getLosses();
		int gamesPlayed = playerProfile.getGamesPlayed();
		
		//unsure what to do
	}
	 */




}
