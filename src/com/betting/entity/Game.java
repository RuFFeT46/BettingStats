package com.betting.entity;

import com.betting.Database;
import com.betting.database.GameWrapper;
import com.betting.database.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int gameId;
    private Gameday gameday;
    private Team homeTeam;
    private Team awayTeam;
    private double homeHandicap;
    private int homeGoals;
    private int awayGoals;
    private double xGHome;
    private double xGAway;
    private boolean finished;
    private List<Bet> bets;

    public Game(Team homeTeam, Team awayTeam, double homeHandicap) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeHandicap = homeHandicap;
        this.bets = new ArrayList<>();
        homeTeam.getTeamGames().add(this);
        awayTeam.getTeamGames().add(this);
        this.finished = false;
        //Wrapper.getInstance().getGames().add(new GameWrapper(this));
    }

    public Game(GameWrapper gameWrapper){
        this.gameId = gameWrapper.getGameId();
        boolean gamedayNotCreated = true;
        for(Gameday gameday : Database.getInstance().getGamedays()){
            if(gameday.getId() == gameWrapper.getGameday()){
                this.gameday = gameday;
                gameday.getGames().add(this);
                gamedayNotCreated = false;
                break;
            }
        }
        if(gamedayNotCreated){
            Database.getInstance().getGamedays().add(new Gameday(gameWrapper.getGameday(), this));

        }
        this.homeTeam = Database.getInstance().getTeamObject(gameWrapper.getHomeTeam());
        this.awayTeam = Database.getInstance().getTeamObject(gameWrapper.getAwayTeam());
        homeTeam.getTeamGames().add(this);
        awayTeam.getTeamGames().add(this);
        this.homeHandicap = gameWrapper.getHomeHandicap();
        this.homeGoals = gameWrapper.getHomeGoals();
        this.awayGoals = gameWrapper.getAwayGoals();
        this.xGHome = gameWrapper.getxGHome();
        this.xGAway = gameWrapper.getxGAway();
        this.finished = gameWrapper.isFinished();
        bets = new ArrayList<>();
    }

    public void finishGame(int homeGoals, int awayGoals, double xGHome, double xGAway){
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.xGHome = xGHome;
        this.xGAway = xGAway;
        this.finished = true;
        for (Bet e : bets) {
            e.finishBet();
        }
        Wrapper.getInstance().getGameWrapper(this).finishGame(this);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Gameday getGameday() {
        return gameday;
    }

    public void setGameday(Gameday gameday) {
        this.gameday = gameday;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public double getHomeHandicap() {
        return homeHandicap;
    }

    public void setHomeHandicap(double homeHandicap) {
        this.homeHandicap = homeHandicap;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public double getxGHome() {
        return xGHome;
    }

    public void setxGHome(double xGHome) {
        this.xGHome = xGHome;
    }

    public double getxGAway() {
        return xGAway;
    }

    public void setxGAway(double xGAway) {
        this.xGAway = xGAway;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Game{" +
                "homeTeam=" + homeTeam.getName() +
                ", awayTeam=" + awayTeam.getName() +
                ", homeHandicap=" + homeHandicap +
                ", homeGoals=" + homeGoals +
                ", awayGoals=" + awayGoals +
                ",\nbets=" + bets +
                '}';
    }

    public String printGameInfo(){
        for(Game g : homeTeam.getTeamGames()){
            if(g.isFinished()) {
                System.out.println("Spieltag " + g.getGameday().getId() + ": " + g.homeTeam.getName() + " - " + g.homeGoals + "+ " + g.getHomeHandicap() + " vs " +
                        g.awayTeam.getName() + " - " + g.awayGoals + "+ " + g.getHomeHandicap() * -1);
            }
        }
        for(Game g : awayTeam.getTeamGames()){
            if(g.isFinished()) {
                System.out.println("Spieltag " + g.getGameday().getId() + ": " + g.homeTeam.getName() + " - " + g.homeGoals + "+ " + g.getHomeHandicap() + " vs " +
                        g.awayTeam.getName() + " - " + g.awayGoals + "+ " + g.getHomeHandicap() * -1);
            }
        }
        return this.homeTeam.getName() + " vs. " + this.getAwayTeam().getName() + "\n"
                + "Gewinne durch Wetten auf " + this.homeTeam.getName() + ": " + homeTeam.getWinnings(false) + "\t\t"
                + "Gewinne durch Wetten auf " + this.awayTeam.getName() + ": " + awayTeam.getWinnings(false) + "\n"
                + "Gewinne durch Wetten mit " + this.homeTeam.getName() + ": " + homeTeam.getWinnings(true) + "\t\t"
                + "Gewinne durch Wetten mit " + this.awayTeam.getName() + ": " + awayTeam.getWinnings(true) + "\n"
                + "Handicap Gesamt mit " + this.homeTeam.getName() + ": " + homeTeam.teamAgainstHandi(true, true) + "\t\t"
                + "Handicap Gesamt mit " + this.awayTeam.getName() + ": " + awayTeam.teamAgainstHandi(true, true) + "\n"
                + "Handicap Heim mit " + this.homeTeam.getName() + ": " + homeTeam.teamAgainstHandi(true, false) + "\t\t"
                + "Handicap Auswärts mit " + this.awayTeam.getName() + ": " + awayTeam.teamAgainstHandi(false, true) + "\n"
                + "Handicap xG Gesamt mit " + this.homeTeam.getName() + ": " + homeTeam.teamAgainstxGHandi(true, true) + "\t\t"
                + "Handicap xG Gesamt mit " + this.awayTeam.getName() + ": " + awayTeam.teamAgainstxGHandi(true, true) + "\n"
                + "Handicap xG Heim mit " + this.homeTeam.getName() + ": " + homeTeam.teamAgainstxGHandi(true, false) + "\t\t"
                + "Handicap xG Auswärts mit " + this.awayTeam.getName() + ": " + awayTeam.teamAgainstxGHandi(false, true) + "\n"
                + "Vergleichbare Situation von " + this.homeTeam.getName() + ": " + this.getHomeTeam().winrateWithHandi(this.homeHandicap, true, true) + "\t"
                + "Vergleichbare Situation von " + this.awayTeam.getName() + ": " + this.getAwayTeam().winrateWithHandi(this.homeHandicap*-1, true, true) + "\n"
                + "Vergleichbare Situation Home von " + this.homeTeam.getName() + ": " + this.getHomeTeam().winrateWithHandi(this.homeHandicap, true, false) + "\t"
                + "Vergleichbare Situation Away von " + this.awayTeam.getName() + ": " + this.getAwayTeam().winrateWithHandi(this.homeHandicap*-1, false, true) + "\n"
                + "Handicap Gesamt +0,75 mit " + this.homeTeam.getName() + ": " + homeTeam.winrateAgainstDiffOdds(true, true, 0.75) + "\t\t"
                + "Handicap Gesamt +0.75 mit " + this.awayTeam.getName() + ": " + awayTeam.winrateAgainstDiffOdds(true, true, 0.75) + "\n"
                + "Handicap Heim +0.75 mit " + this.homeTeam.getName() + ": " + homeTeam.winrateAgainstDiffOdds(true, false, 0.75) + "\t\t"
                + "Handicap Auswärts +0.75 mit " + this.awayTeam.getName() + ": " + awayTeam.winrateAgainstDiffOdds(false, true, 0.75) + "\n"
                + "Handicap Gesamt -0.75 mit " + this.homeTeam.getName() + ": " + homeTeam.winrateAgainstDiffOdds(true, true, -0.75) + "\t\t"
                + "Handicap Gesamt -0.75 mit " + this.awayTeam.getName() + ": " + awayTeam.winrateAgainstDiffOdds(true, true, -0.75) + "\n"
                + "Handicap Heim -0.75 mit " + this.homeTeam.getName() + ": " + homeTeam.winrateAgainstDiffOdds(true, false, -0.75) + "\t\t"
                + "Handicap Auswärts -0.75 mit " + this.awayTeam.getName() + ": " + awayTeam.winrateAgainstDiffOdds(false, true, -0.75) + "\n"
                + homeTeam.getName() + "\n"
                + homeTeam.winrateOdds(1.25, 1.7) + "\n"
                + homeTeam.winrateOdds(1.7, 1.95) + "\n"
                + homeTeam.winrateOdds(1.95, 2.5) + "\n"
                + awayTeam.getName() + "\n"
                + awayTeam.winrateOdds(1.25, 1.7) + "\n"
                + awayTeam.winrateOdds(1.7, 1.95) + "\n"
                + awayTeam.winrateOdds(1.95, 2.5) + "\n"

                ;
    }
}
