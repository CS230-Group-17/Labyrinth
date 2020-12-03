package sample;

import javafx.scene.image.Image;

/**
 * adds extra move
 * @author Marijus Gudiskis 1901701
 */
public class DoubleMove extends ActionTile {


    public DoubleMove(String imgPath,  Image tileImage) {
        super(imgPath, tileImage);

    }

    /**
     * adds extra move to the player
     * @param player to what player add the extra move
     */
    @Override
    public boolean ActionTile(Player player) {
        player.setMovesPerTurn(player.getMovesPerTurn()+1);
        return true;
    }
}
