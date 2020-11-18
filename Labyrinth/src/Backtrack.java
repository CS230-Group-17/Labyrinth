/**
 * the chosen player goes back to a FloorTile where it was 2 moves ago if it is possible
 */
public class Backtrack extends ActionTile {

    public Backtrack(String imgPath) {
        super(imgPath);
    }

    /**
     * selected player goes back to its past position if it is possible, max 2 turns back
     * @param player the player who is making other players go back
     */
    @Override
    public void ActionTile(Player player) {
        Player effectedPlayer = Game.getEffectedPlayers(player);

        //find if the previous location tile still exist on the board
        boolean tileFound = true;//if tile not found, do not proceed with the next tile search
        for(int j = 0; j < 2 && tileFound; j++) {

            tileFound = false;
            FloorTile temp = effectedPlayer.getBackTiles();//for what tile we are searching

            for (int i = 0; i < Game.board.size(); i++) {
                if(Game.board.get(i).contains(temp)) {
                    effectedPlayer.setPlayerLocation(temp);
                    tileFound = true;
                }
            }

        }

    }
}
