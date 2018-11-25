package com.betting.database;

import com.betting.Database;
import com.betting.entity.Bet;
import com.betting.entity.Game;
import com.betting.entity.Gameday;
import com.betting.entity.Team;

import java.util.ArrayList;
import java.util.List;

public class Wrapper {
    private List<TeamWrapper> teams;
    private List<GameWrapper> games;
    private List<BetWrapper> bets;
    private static Wrapper instance;

    private Wrapper(){
        teams = new ArrayList<>();
        games = new ArrayList<>();
        bets = new ArrayList<>();

    }

    public List<TeamWrapper> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamWrapper> teams) {
        this.teams = teams;
    }

    public List<GameWrapper> getGames() {
        return games;
    }

    public void setGames(List<GameWrapper> games) {
        this.games = games;
    }

    public List<BetWrapper> getBets() {
        return bets;
    }

    public void setBets(List<BetWrapper> bets) {
        this.bets = bets;
    }

    public static void setInstance(Wrapper instance) {
        Wrapper.instance = instance;
    }

    public static Wrapper getInstance() {
        if(instance == null){
            instance = new Wrapper();
        }
        return instance;
    }

    public void writeWrapper(){
        for(Team team : Database.getInstance().getTeams()){
            Wrapper.getInstance().getTeams().add(new TeamWrapper(team));
        }
        for(Gameday gameday : Database.getInstance().getGamedays()){
            for(Game game : gameday.getGames()){
                Wrapper.getInstance().getGames().add(new GameWrapper(game));
            }
        }
        for(Bet bet : Database.getInstance().getBets()){
            Wrapper.getInstance().getBets().add(new BetWrapper(bet));
        }
    }

    public TeamWrapper getTeamWrapper(Team team){
        for(TeamWrapper teamWrapper : Wrapper.getInstance().getTeams()){
            if(team.getTeamId() == teamWrapper.getTeamId()){
                return teamWrapper;
            }
        }
        return null;
    }

    public GameWrapper getGameWrapper(Game game){
        for(GameWrapper gameWrapper : Wrapper.getInstance().getGames()){
            if(game.getGameId() == gameWrapper.getGameId()){
                return gameWrapper;
            }
        }
        return null;
    }

    public BetWrapper getBetWrapper(Bet bet){
        for(BetWrapper betWrapper : Wrapper.getInstance().getBets()){
            if(bet.getBetId() == betWrapper.getBetId()){
                return betWrapper;
            }
        }
        return null;
    }

}



