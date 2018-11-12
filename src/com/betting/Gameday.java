package com.betting;

public class Gameday {
    private int id;
    private Game[] games;

    public Gameday(int id,
                Name g1home, Name g1away, double g1handi,
                Name g2home, Name g2away, double g2handi,
                Name g3home, Name g3away, double g3handi,
                Name g4home, Name g4away, double g4handi,
                Name g5home, Name g5away, double g5handi,
                Name g6home, Name g6away, double g6handi,
                Name g7home, Name g7away, double g7handi,
                Name g8home, Name g8away, double g8handi,
                Name g9home, Name g9away, double g9handi){
        this.id = id;
        games = new Game[9];
        games[0] = new Game(Database.getInstance().getTeams().get(g1home.ordinal()), Database.getInstance().getTeams().get(g1away.ordinal()), g1handi);
        games[1] = new Game(Database.getInstance().getTeams().get(g2home.ordinal()), Database.getInstance().getTeams().get(g2away.ordinal()), g2handi);
        games[2] = new Game(Database.getInstance().getTeams().get(g3home.ordinal()), Database.getInstance().getTeams().get(g3away.ordinal()), g3handi);
        games[3] = new Game(Database.getInstance().getTeams().get(g4home.ordinal()), Database.getInstance().getTeams().get(g4away.ordinal()), g4handi);
        games[4] = new Game(Database.getInstance().getTeams().get(g5home.ordinal()), Database.getInstance().getTeams().get(g5away.ordinal()), g5handi);
        games[5] = new Game(Database.getInstance().getTeams().get(g6home.ordinal()), Database.getInstance().getTeams().get(g6away.ordinal()), g6handi);
        games[6] = new Game(Database.getInstance().getTeams().get(g7home.ordinal()), Database.getInstance().getTeams().get(g7away.ordinal()), g7handi);
        games[7] = new Game(Database.getInstance().getTeams().get(g8home.ordinal()), Database.getInstance().getTeams().get(g8away.ordinal()), g8handi);
        games[8] = new Game(Database.getInstance().getTeams().get(g9home.ordinal()), Database.getInstance().getTeams().get(g9away.ordinal()), g9handi);

        games[0].setGameday(this);
        games[1].setGameday(this);
        games[2].setGameday(this);
        games[3].setGameday(this);
        games[4].setGameday(this);
        games[5].setGameday(this);
        games[6].setGameday(this);
        games[7].setGameday(this);
        games[8].setGameday(this);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }
}
