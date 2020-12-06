package sample;

public class PlayerProfile {
    private int losses;
    private int wins;
    private final String name;
    private int gamesPlayed;

    public PlayerProfile(int losses, int wins, String name, int gamesPlayed) {
        this.losses = losses;
        this.wins = wins;
        this.name = name;
        this.gamesPlayed = gamesPlayed;
    }

    public int getLosses() {
        return losses;
    }

    public void incLosses() {
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public void incWins() {
        wins++;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incGamesPlayed() {
        gamesPlayed++;
    }
}
