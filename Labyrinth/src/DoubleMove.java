/**
 * adds extra move
 */
public class DoubleMove extends ActionTile {


    public DoubleMove(String imgPath) {
        super(imgPath);

    }

    /**
     * adds extra move to the player
     * @param player to what player add the extra move
     */
    @Override
    public void ActionTile(Player player) {
        player.setMovesPerTurn(player.getMovesPerTurn()+1);
    }
}
