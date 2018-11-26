package com.betting.entity;

import com.betting.Database;
import com.betting.database.*;


import java.util.ArrayList;
import java.util.List;

public class Gameday {
    private int id;
    private List<Game> games;

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
        games = new ArrayList<>();
        games.add(new Game(Database.getInstance().getTeams().get(g1home.ordinal()), Database.getInstance().getTeams().get(g1away.ordinal()), g1handi));
        games.add(new Game(Database.getInstance().getTeams().get(g2home.ordinal()), Database.getInstance().getTeams().get(g2away.ordinal()), g2handi));
        games.add(new Game(Database.getInstance().getTeams().get(g3home.ordinal()), Database.getInstance().getTeams().get(g3away.ordinal()), g3handi));
        games.add(new Game(Database.getInstance().getTeams().get(g4home.ordinal()), Database.getInstance().getTeams().get(g4away.ordinal()), g4handi));
        games.add(new Game(Database.getInstance().getTeams().get(g5home.ordinal()), Database.getInstance().getTeams().get(g5away.ordinal()), g5handi));
        games.add(new Game(Database.getInstance().getTeams().get(g6home.ordinal()), Database.getInstance().getTeams().get(g6away.ordinal()), g6handi));
        games.add(new Game(Database.getInstance().getTeams().get(g7home.ordinal()), Database.getInstance().getTeams().get(g7away.ordinal()), g7handi));
        games.add(new Game(Database.getInstance().getTeams().get(g8home.ordinal()), Database.getInstance().getTeams().get(g8away.ordinal()), g8handi));
        games.add(new Game(Database.getInstance().getTeams().get(g9home.ordinal()), Database.getInstance().getTeams().get(g9away.ordinal()), g9handi));

        games.get(0).setGameday(this);
        games.get(1).setGameday(this);
        games.get(2).setGameday(this);
        games.get(3).setGameday(this);
        games.get(4).setGameday(this);
        games.get(5).setGameday(this);
        games.get(6).setGameday(this);
        games.get(7).setGameday(this);
        games.get(8).setGameday(this);

        games.get(0).setGameId((id-1)*9+1);
        games.get(1).setGameId((id-1)*9+2);
        games.get(2).setGameId((id-1)*9+3);
        games.get(3).setGameId((id-1)*9+4);
        games.get(4).setGameId((id-1)*9+5);
        games.get(5).setGameId((id-1)*9+6);
        games.get(6).setGameId((id-1)*9+7);
        games.get(7).setGameId((id-1)*9+8);
        games.get(8).setGameId((id-1)*9+9);

        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(0)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(1)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(2)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(3)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(4)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(5)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(6)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(7)));
        Wrapper.getInstance().getGames().add(new GameWrapper(games.get(8)));
    }

    public Gameday(int id, Game game1){
        this.id = id;
        games = new ArrayList<>();
        games.add(game1);
        game1.setGameday(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String printGamedayInfo(){
        int win = 0;
        int lose = 0;
        Money winnings = new Money(0);
        Money einsatz = new Money(0);

        for(Game game: games){
            if(game.isFinished()){
                for (Bet bet : game.getBets()){
                    if(bet.getWin().getCent() > bet.getMoney().getCent()){
                        win++;
                    }
                    else {
                        lose++;
                    }
                    einsatz.setCent(einsatz.getCent() + bet.getMoney().getCent());
                    winnings.setCent(winnings.getCent() - bet.getMoney().getCent() + bet.getWin().getCent());
                }
            }
        }
        int winrate = 0;
        if(win+lose != 0) {
            winrate = (int) (((double) win / (win + lose)) * 100);
        }
        int moneyrate = 0;
        if(einsatz.getCent() > 0){
            moneyrate = (int) ((winnings.getEuro()/einsatz.getEuro())*100);
        }
        return "Gameday: " + this.getId() + " - " + win + " : " + lose + " - " + winrate + "%"
                + " - Winnings: " + winnings + " bei Einsatz von " + einsatz + " (" + moneyrate + "%)"
                ;
    }
}
