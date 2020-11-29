/**
 * tile which hold all the needed information to be on the board
 * @author Luciano Martino 851509
 */
import javafx.scene.image.*;

public class FloorTile extends Tile{
	private int frozenUntilNthTurn;
	private int onFireUntilNthTurn;//instead handled in game?
	private final boolean[] whichDirectionsEnterable;
	private Image image;
	
	FloorTile(boolean north, boolean east, boolean south, boolean west){	
		whichDirectionsEnterable = new boolean[] {north, east, south, west};
		
		int count = 0;
		
		for(boolean dir : whichDirectionsEnterable) {
			count += (dir ? 1 : 0);
		}
		
		calcFloorTileImage(count);
		
	}
	private void calcFloorTileImage(int amountOfSidesEnterable) {
		String imgPath = "";
		switch (amountOfSidesEnterable) {
		case 2:
			if(isNorth()) {
				if(isSouth()) {
					//imgPath = vertical straight piece;
				}else if(isEast()){
					//imgPath = top right corner piece;
				}else {
					//imgPath = top left corner piece;
				}
			}else if(isEast()){
				if(isWest()){
					//imgPath = horizontal straight piece;
				}else {
					//imgPath = bottom right corner piece;
				}
			}else {
				//imgPath = bottom left corner piece;
			}
			break;
		case 3:
			if(isNorth()) {
				if(isEast()){
					if(isSouth()) {
						//imgPath = right T piece;
					}else {
						//imgPath = top T piece;
					}
				}else {
					//imgPath = left T piece;
				}
			}else {
				//imgPath = bottom T piece;
			}
			break;
		case 4:
			//imgPath = goal tile;
			break;
		default:
			//throw error
		}
		setImage(imgPath);
	}
	
	private void calcFloorTileImage(String floorTileType) {
		String imgPath = "";
		switch(floorTileType){
		case "I":
			if(isNorth()) {
				
			}else {
				
			}
			break;
		case "L":
			if(isNorth()) {
				if(isEast()) {
					
				}else {
					
				}
			} else if(isSouth()) {
				if(isWest()) {
					
				}else {
					
				}
			}
			break;
		case "T":
			if(isNorth()) {
				if(isEast()){
					if(isSouth()) {
						//imgPath = right T piece;
					}else {
						//imgPath = top T piece;
					}
				}else {
					//imgPath = left T piece;
				}
			}else {
				//imgPath = bottom T piece;
			}
			break;
		case "G":
			//imgPath = goal tile;
			break;
		default:
			//throw error
		}
		setImage(imgPath);
	}
	
	public boolean isTileFixed() {
		return (frozenUntilNthTurn > Game.getTurn());
	}
	public boolean isOnFire() {
		return (onFireUntilNthTurn > Game.getTurn());
	}
	public Image getImage() {
		return image;
	}
//	public void setImage(Image image) {
//		this.image = image;
//	}
	private void setImage(String path) {
		this.image = new Image(path);
	}
	public boolean isNorth() {
		return whichDirectionsEnterable[0];
	}
	public boolean isEast() {
		return whichDirectionsEnterable[1];
	}
	public boolean isSouth() {
		return whichDirectionsEnterable[2];
	}
	public boolean isWest() {
		return whichDirectionsEnterable[3];
	}
}
