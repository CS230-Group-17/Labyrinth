/**
 * tile which hold all the needed information to be on the board
 * @author Marijus Gudiskis 1901701
 */
public class FloorTile extends Tile {
    //variables
    public int isFrozenForTheNextNTurns = -1;
    public int isOnFireForTheNextNTurns = -1;
    private boolean fixedTile;
    public boolean isOnFire = false;
    public boolean isFrozen = false;
    public String name;
    public boolean north;
    public boolean east;
    public boolean south;
    public boolean west;

    public FloorTile(String imgPath, boolean fixedTile, boolean north, boolean east,
                     boolean south, boolean west, String name) {
        super(imgPath);
        this.fixedTile = fixedTile;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.name = name;
    }

    public boolean isFixedTile() {
        return fixedTile;
    }
    
    public boolean getNorth() {
    	return north;
    }
    
    public boolean getSouth() {
    	return south;
    }

    public boolean getEast() {
    	return east;
    }

    public boolean getWest() {	
    	return west;
    }
    
}
