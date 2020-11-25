import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import javafx.scene.image.Image;

public class Player
{
	// private FloorTile location; "Remove once floor tile class is done."
	private String imgPath;
	private PlayerProfile playerProfile;
	private Player[] prevBtPlayers = new Player[4];
	private ArrayList<ActionTile> hand = new ArrayList<ActionTile>();
	private ArrayList<FloorTile> backTiles= new ArrayList<FloorTile>();
	private int movesPerTurn = 1;
	public FloorTile playerLocation;
	
	public Player(String imgPath, PlayerProfile playerProfile, FloorTile location)
	{
		this.imgPath = imgPath;
		this.playerProfile = playerProfile;
		this.playerLocation = location;
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

	public ArrayList<ActionTile> getHand()
	{
	    return hand;
	}
	
	public Image getImage()
	{
		try
		{
			FileInputStream inputStream = new FileInputStream(imgPath);
			Image image = new Image(inputStream);
			return image;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Can not find the image file.");
			System.exit(0);
		}
		return null;
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
	 * adds a tile on which the player was standing on
	 * @param pastTile the past tile it was standing on
	 */
	 public void updateGetBackTiles(FloorTile pastTile) {
		 backTiles.add(0, pastTile);
		 if(backTiles.size() > 2) {
			backTiles.remove(2);
		}
	 }

	/**
	 * returns the soonest tile it was standing on
	 * @return the soonest tile
	 */
	 public FloorTile getBackTiles() {
		FloorTile temp = backTiles.get(0);
		backTiles.remove(0);
		return temp;
	 }

	 public void setPosition(FloorTile newPos) {
		 this.playerLocation = newPos;
	 }

	 public FloorTile getPosition() {
		return playerLocation;
	 }
}
