package com.betting;

public class Bet {

    private Game betGame;
    private Team team;
    private double betHandicap;
    private double odds;
    private Money money;
    private Money win;

    public Bet(Game betGame, boolean betHomeTeam, double betHandicap, double odds, double money) {
        this.betGame = betGame;
        this.betHandicap = betHandicap;
        this.odds = odds;
        this.money = new Money(money);
        if(betHomeTeam){
            team = betGame.getHomeTeam();
        }
        else{
            team = betGame.getAwayTeam();
        }
        this.win = new Money(0);
        betGame.getBets().add(this);
        betGame.getHomeTeam().getTeamBets().add(this);
        betGame.getAwayTeam().getTeamBets().add(this);
    }

    public Bet(Name team, double betHandicap, double odds, double money, int gameday) {
        Game[] games = Database.getInstance().getGamedays().get(gameday-8).getGames();
        this.team = Database.getInstance().getTeams().get(team.ordinal());
        for(Game g : games){
            if(g.getHomeTeam() == this.team || g.getAwayTeam() == this.team){
                betGame = g;
            }
        }
        this.betHandicap = betHandicap;
        this.odds = odds;
        this.money = new Money(money);
        this.win = new Money(0);
        betGame.getBets().add(this);
        betGame.getHomeTeam().getTeamBets().add(this);
        betGame.getAwayTeam().getTeamBets().add(this);
    }

    public Money finishBet(){
        win.setCent(0);
        if(betGame.getHomeTeam() == team){
            if((betGame.getHomeGoals() + betHandicap - 0.25) > betGame.getAwayGoals()){
                win.setEuro(odds*money.getEuro()*.95);
                return win;
            }
            if((betGame.getHomeGoals() + betHandicap) > betGame.getAwayGoals()){
                win.setEuro((odds+1)*0.5*money.getEuro()*0.95);
                return win;
            }
            if((betGame.getHomeGoals() + betHandicap + 0.25) > betGame.getAwayGoals()){
                win.setEuro(money.getEuro()*0.95);
                return win;
            }
            if((betGame.getHomeGoals() + betHandicap + 0.5) > betGame.getAwayGoals()) {
                win.setEuro(money.getEuro() * 0.5 * 0.95);
                return win;
            }
        }
        else if(betGame.getAwayTeam() == team){
            if((betGame.getAwayGoals() + betHandicap - 0.25) > betGame.getHomeGoals()){
                win.setEuro(odds*money.getEuro()*.95);
                return win;
            }
            if((betGame.getAwayGoals() + betHandicap) > betGame.getHomeGoals()){
                win.setEuro((odds+1)*0.5*money.getEuro()*0.95);
                return win;
            }
            if((betGame.getAwayGoals() + betHandicap + 0.25) > betGame.getHomeGoals()){
                win.setEuro(money.getEuro()*0.95);
                return win;
            }
            if((betGame.getAwayGoals() + betHandicap + 0.5) > betGame.getHomeGoals()) {
                win.setEuro(money.getEuro() * 0.5 * 0.95);
                return win;
            }
        }
        return win;
    }

    public Game getBetGame() {
        return betGame;
    }

    public void setBetGame(Game betGame) {
        this.betGame = betGame;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getBetHandicap() {
        return betHandicap;
    }

    public void setBetHandicap(double betHandicap) {
        this.betHandicap = betHandicap;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Money getWin() {
        return win;
    }

    public void setWin(Money win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "team=" + team.getName() +
                ", betHandicap=" + betHandicap +
                ", odds=" + odds +
                ", money=" + money +
                ", win=" + win +
                '}';
    }
}
