package com.betting.database;

import com.betting.entity.Game;
import org.hibernate.Session;

import javax.persistence.*;

@Entity
@Table(name="game")
public class GameWrapper {

    private int gameId;
    private int gameday;
    private TeamWrapper homeTeam;
    private TeamWrapper awayTeam;
    private double homeHandicap;
    private int homeGoals;
    private int awayGoals;
    private double xGHome;
    private double xGAway;
    private boolean finished;

    public GameWrapper(){

    }

    public GameWrapper(Game game){
        this.gameId = game.getGameId();
        this.gameday = game.getGameday().getId();
        this.homeTeam = Wrapper.getInstance().getTeamWrapper(game.getHomeTeam());
        this.awayTeam = Wrapper.getInstance().getTeamWrapper(game.getAwayTeam());
        this.homeHandicap = game.getHomeHandicap();
        this.homeGoals = game.getHomeGoals();
        this.awayGoals = game.getAwayGoals();
        this.xGHome = game.getxGHome();
        this.xGAway = game.getxGAway();
        this.finished = game.isFinished();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();

    }

    public void finishGame(Game game){
        this.homeGoals = game.getHomeGoals();
        this.awayGoals = game.getAwayGoals();
        this.xGHome = game.getxGHome();
        this.xGAway = game.getxGAway();
        this.finished = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(this);
        session.getTransaction().commit();

    }

    @Id
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Column
    public int getGameday() {
        return gameday;
    }

    public void setGameday(int gameday) {
        this.gameday = gameday;
    }

    @ManyToOne
    @JoinColumn(name = "homeTeam")
    public TeamWrapper getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamWrapper homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "awayTeam")
    public TeamWrapper getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamWrapper awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(name = "homeHandicap")
    public double getHomeHandicap() {
        return homeHandicap;
    }


    public void setHomeHandicap(double homeHandicap) {
        this.homeHandicap = homeHandicap;
    }

    @Column(name = "homeGoals")
    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }
    @Column(name = "awayGoals")
    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Column(name = "xGHome")
    public double getxGHome() {
        return xGHome;
    }

    public void setxGHome(double xGHome) {
        this.xGHome = xGHome;
    }

    @Column(name = "xGAway")
    public double getxGAway() {
        return xGAway;
    }

    public void setxGAway(double xGAway) {
        this.xGAway = xGAway;
    }

    @Column(name = "finished")
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
