package sample;


import javafx.scene.image.Image;

/**
 * tile which hold all the needed information to be on the board
 * @author Marijus Gudiskis 1901701
 */


public class FloorTile extends Tile {
	private final boolean[] whichDirectionsEnterable;
	private int frozenUntilNthTurn;
	private int onFireUntilNthTurn;//instead handled in game?
	private Image image;

	FloorTile(boolean north, boolean east, boolean south, boolean west) {
		whichDirectionsEnterable = new boolean[] {north, east, south, west};

		int count = 0;

		for (boolean dir : whichDirectionsEnterable) {
			count += (dir ? 1 : 0);
		}

		calcFloorTileImage(count);

	}
	
	private void calcFloorTileImage(int amountOfSidesEnterable) {
		String imgPath = "";
		switch (amountOfSidesEnterable) {
		case 2:
			if (isNorth()) {
				if (isSouth()) {
					//imgPath = vertical straight piece;
				}else if (isEast()) {
					//imgPath = top right corner piece;
				} else {
					//imgPath = top left corner piece;
				}
			}else if(isEast()) {
				if(isWest()) {
					//imgPath = horizontal straight piece;
				} else {
					//imgPath = bottom right corner piece;
				}
			} else {
				//imgPath = bottom left corner piece;
			}
			break;
		case 3:
			if (isNorth()) {
				if (isEast()){
					if (isSouth()) {
						//imgPath = right T piece;
					} else {
						//imgPath = top T piece;
					}
				} else {
					//imgPath = left T piece;
				}
			} else {
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
	public boolean isTileFixed() {
		return (frozenUntilNthTurn > Game.getTurn());
	}
	public boolean isOnFire() {
		return (onFireUntilNthTurn > Game.getTurn());
	}

	public boolean isFixedTile() {
		return fixedTile;
	}
}
