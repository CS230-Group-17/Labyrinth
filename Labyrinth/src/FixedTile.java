
public class FixedTile extends FloorTile{
    //variables
    private int x = 0;
    private int y = 0;
    
    public FloorTile(String imgPath, boolean fixedTile, boolean north, boolean east, 
    		boolean south, boolean west, String name, int x, int y) {
    	super(imgPath);
    	super(fixedTile);
    	super(north);
    	super(east);
    	super(south);
    	super(west);
    	super(name);
    	this.x = x;
    	this.y = y;
    }
    
    public int getX() {
    	return this.x;
    }
    
    
    public int getY() {
    	return this.y;
    }
}
