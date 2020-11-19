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
	// insert backTiles here
	private int movesPerTurn;
	
	public Player(//FloorTile location, "Remove once floor tile class is done."
			String imgPath, PlayerProfile playerProfile)
	{
		this.imgPath = imgPath;
		this.playerProfile = playerProfile;
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
	
	/*
	 * uncomment when floor tile class is done
	 * 
	 * private void updateGetBackTiles(FloorTile newTile)
	 * {
	 *	backTiles.add(newTile);
	 * }
	*/
}
