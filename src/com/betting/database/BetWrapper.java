package com.betting.database;

import com.betting.entity.Bet;
import org.hibernate.Session;

import javax.persistence.*;

@Entity
@Table(name="bet")
public class BetWrapper {

    private int betId;
    private GameWrapper betGame;
    private TeamWrapper team;
    private double betHandicap;
    private double odds;
    private int money;
    private int win;

    public BetWrapper() {

    }

    public BetWrapper(Bet bet) {
        this.betId = bet.getBetId();
        this.betGame = Wrapper.getInstance().getGameWrapper(bet.getBetGame());
        this.team = Wrapper.getInstance().getTeamWrapper(bet.getTeam());
        this.betHandicap = bet.getBetHandicap();
        this.odds = bet.getOdds();
        this.money = bet.getMoney().getCent();
        this.win = bet.getWin().getCent();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
    }

    public void updateWinnigs(Bet bet){
        this.win = bet.getWin().getCent();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(this);
        session.getTransaction().commit();
    }

    @Id
    @Column(name = "bet_id")
    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }


    @ManyToOne
    @JoinColumn(name = "game")
    public GameWrapper getBetGame() {
        return betGame;
    }

    public void setBetGame(GameWrapper betGame) {
        this.betGame = betGame;
    }

    @ManyToOne
    @JoinColumn(name = "team")
    public TeamWrapper getTeam() {
        return team;
    }

    public void setTeam(TeamWrapper team) {
        this.team = team;
    }

    @Column(name = "betHandicap")
    public double getBetHandicap() {
        return betHandicap;
    }

    public void setBetHandicap(double betHandicap) {
        this.betHandicap = betHandicap;
    }

    @Column(name = "odds")
    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    @Column
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Column
    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}

