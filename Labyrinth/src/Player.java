package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player
{
	// private FloorTile location; "Remove once floor tile class is done."
	private String imgPath;
	private Image playerImage;
	private PlayerProfile playerProfile;
	private Player[] prevBtPlayers = new Player[4];
	private ArrayList<ActionTile> hand = new ArrayList<ActionTile>();
	private ArrayList<FloorTile> backTiles= new ArrayList<FloorTile>();
	private int movesPerTurn = 1;
	private FloorTile playerLocation;
	
	
	public Player(String imgPath, PlayerProfile playerProfile, FloorTile location, Image playerImage) {
		this.imgPath = imgPath;
		this.playerProfile = playerProfile;
		this.playerLocation = location;
		this.playerImage = playerImage;

		updateGetBackTiles(location);
	}

	public Image getPlayerImage(){
		return playerImage;
	}

	public PlayerProfile getPlayerProfile() {
		return playerProfile;
	}

	public Player[] getPrevBtPlayers()
	{
		return prevBtPlayers;
	}
	
	public void addPrevBtPlayers(Player btdPlayer)
	{
		for (int i = 0; i < prevBtPlayers.length-1; i++)
		{
			if (prevBtPlayers[i] == null)
			{
				prevBtPlayers[i] = btdPlayer;
				break;
			}
		}
	}

	public ActionTile getHand(int index)
	{
	    return hand.get(index);
	}

	public void addHand(ActionTile newTile) {
		hand.add(newTile);
	}

	public int sizeOfHand(){
		return hand.size();
	}
	public void removeHandTile(ActionTile tile) {
		hand.remove(tile);
	}

	public String getImage()
	{
		return imgPath;
	}
	
	public int getMovesPerTurn()
	{
		return movesPerTurn;
	}
	
	public void setMovesPerTurn(int num)
	{
		if (num > 2)
		{
			num = 2;
		}
		else if (num < 0)
		{
			num = 0;
		}
		
		movesPerTurn = num;
	}


	/**
	 * Adds a tile on which the player was standing on
	 * @param pastTile the past tile it was standing on
	 */
	 public void updateGetBackTiles(FloorTile pastTile) {
		 backTiles.add(0, pastTile);
		 if(backTiles.size() > 2) {
			backTiles.remove(2);
		}
	 }

	/**
	 * Returns the soonest tile it was standing on
	 * @return the soonest tile
	 */
	 public FloorTile getBackTiles() {
	 	if(backTiles.size() == 0){
	 		return null;
		}else {
			FloorTile temp = backTiles.get(0);
			backTiles.remove(0);
			return temp;
		}
	 }
	 
	 /**
	  * Sets the position of the player piece
	  * @param newPos
	  */
	 public void setPosition(FloorTile newPos) {
		 this.playerLocation = newPos;
	 }

	 public FloorTile getPosition() {
		return playerLocation;
	 }
}
