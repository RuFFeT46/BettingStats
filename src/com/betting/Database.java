package com.betting;

import java.util.ArrayList;

public class Database {
    private ArrayList<Team> teams;
    private ArrayList<Gameday> gamedays;
    private ArrayList<Bet> bets;
    private static Database instance;

    private Database(){
        teams = new ArrayList<>();
        teams.add(new Team("Borussia Dortmund"));
        teams.add(new Team("RB Leipzig"));
        teams.add(new Team("Bor. Moenchengladbach"));
        teams.add(new Team("Werder Bremen"));
        teams.add(new Team("Hertha BSC"));
        teams.add(new Team("Bayern Muenchen"));
        teams.add(new Team("Eintracht Frankfurt"));
        teams.add(new Team("1.FSV Mainz 05"));
        teams.add(new Team("VfL Wolfsburg"));
        teams.add(new Team("FC Augsburg"));
        teams.add(new Team("SC Freiburg"));
        teams.add(new Team("1.FC Nürnberg"));
        teams.add(new Team("TSG Hoffenheim"));
        teams.add(new Team("Bayer 04 Leverkusen"));
        teams.add(new Team("Schalke 04"));
        teams.add(new Team("Hannover 96"));
        teams.add(new Team("Fortuna Duesseldorf"));
        teams.add(new Team("VfB Stuttgart"));
        gamedays = new ArrayList<>();
        bets = new ArrayList<>();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Gameday> getGamedays() {
        return gamedays;
    }

    public void setGamedays(ArrayList<Gameday> gamedays) {
        this.gamedays = gamedays;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public static Database getInstance() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public String getBettingInfo(double low, double high){
        int win = 0;
        int lose = 0;
        Money einsatz = new Money(0);
        Money winnings = new Money(0);

        for(Bet b: bets){
            if(b.getBetGame().isFinished()){
                if(b.getOdds() >= low && b.getOdds() < high){
                    if(b.getWin().getCent() > b.getMoney().getCent()){
                        win++;
                    }
                    else {
                        lose++;
                    }
                    einsatz.setCent(einsatz.getCent() + b.getMoney().getCent());
                    winnings.setCent(winnings.getCent() + -b.getMoney().getCent() + b.getWin().getCent());
                }
            }
        }
        int winrate = 0;
        if(win+lose != 0) {
            winrate = (int) (((double) win / (win + lose)) * 100);
        }
        int moneyrate = 0;
        if(einsatz.getCent() > 0){
            moneyrate = (int)((winnings.getEuro()/ einsatz.getEuro())*100);
        }

        return "Winrate - bei Wetten zwischen " + low + " und " + high + " - "
                + win + " : " + lose + " - " + winrate + "%"
                + " - Winnings: " + winnings + " bei Einsatz von " + einsatz + " (" + moneyrate + "%)"
                ;

    }


    public void betMatchups(){
        bets.add(new Bet(gamedays.get(0).getGames()[1], true, -1.0, 1.91, 1.5));
        bets.add(new Bet(gamedays.get(0).getGames()[5], false, -0.5, 1.89, 1.5));
        bets.add(new Bet(gamedays.get(0).getGames()[6], false, +0.5, 1.88, 1.5));
    }

    public void finishMatchups(){
        gamedays.get(0).getGames()[0].finishGame(7,1, 3.41, 0.62);
        gamedays.get(0).getGames()[1].finishGame(2,2, 1.95, 1.07);
        gamedays.get(0).getGames()[2].finishGame(0,4, 1.10, 0.94);
        gamedays.get(0).getGames()[3].finishGame(0,0, 0.31, 1.12);
        gamedays.get(0).getGames()[4].finishGame(1,3, 0.78, 2.76);
        gamedays.get(0).getGames()[5].finishGame(1,3, 1.08, 2.81);
        gamedays.get(0).getGames()[6].finishGame(0,2, 1.23, 0.72);
        gamedays.get(0).getGames()[7].finishGame(1,1, 1.00, 0.43);
        gamedays.get(0).getGames()[8].finishGame(4,0, 2.89, 0.71);

    }

    public void betMatchups2(){
        bets.add(new Bet(gamedays.get(1).getGames()[0], false, -0.5, 2.040, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[2], false, +0.0, 2.020, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[4], false, -0.25, 2.070, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[5], true, -1.0, 1.900, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[6], false, -0.25, 1.870, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[7], true, -0.5, 1.840, 1.0));
        bets.add(new Bet(gamedays.get(1).getGames()[8], true, -0.25, 2.010, 1.0));
    }

    public void finishMatchups2(){
        gamedays.get(1).getGames()[0].finishGame(3, 1, 1.32, 1.17);
        gamedays.get(1).getGames()[1].finishGame(2, 2, 3.51, 1.98);
        gamedays.get(1).getGames()[2].finishGame(1, 2, 0.62, 1.41);
        gamedays.get(1).getGames()[3].finishGame(1, 2, 0.59, 2.06);
        gamedays.get(1).getGames()[4].finishGame(0, 3, 0.30, 1.74);
        gamedays.get(1).getGames()[5].finishGame(4, 0, 2.86, 0.53);
        gamedays.get(1).getGames()[6].finishGame(1, 1, 1.14, 1.00);
        gamedays.get(1).getGames()[7].finishGame(0, 0, 0.74, 0.75);
        gamedays.get(1).getGames()[8].finishGame(2, 6, 1.51, 3.11);

    }

    public void betMatchups3(){
        bets.add(new Bet(Name.BRE, +0.5, 1.400, 1.00,10));
        bets.add(new Bet(Name.BMG, -1.5, 2.060, 1.00,10));
        bets.add(new Bet(Name.BSC, +1.0, 1.325, 1.00,10));
        bets.add(new Bet(Name.BVB, -0.5, 1.930, 1.00,10));
        bets.add(new Bet(Name.FCA, -1.0, 2.040, 1.00,10));
        bets.add(new Bet(Name.TSG, +0.25, 1.940, 1.00,10));
        bets.add(new Bet(Name.SCF, +2.5, 1.870, 1.00, 10));
        bets.add(new Bet(Name.S04, -0.25, 1.400, 1.00, 10));
        bets.add(new Bet(Name.SGE, +0.75, 1.325, 1.00,10));
    }

    public void finishMatchups3(){
        gamedays.get(2).getGames()[0].finishGame(0, 3, 1.39, 2.97);
        gamedays.get(2).getGames()[1].finishGame(1, 1, 1.85, 0.58);
        gamedays.get(2).getGames()[2].finishGame(3, 1, 2.89, 1.18);
        gamedays.get(2).getGames()[3].finishGame(1, 4, 1.89, 1.93);
        gamedays.get(2).getGames()[4].finishGame(2, 2, 2.61, 1.38);
        gamedays.get(2).getGames()[5].finishGame(0, 1, 0.44, 1.50);
        gamedays.get(2).getGames()[6].finishGame(0, 3, 1.45, 3.73);
        gamedays.get(2).getGames()[7].finishGame(3, 0, 3.29, 0.72);
        gamedays.get(2).getGames()[8].finishGame(2, 1, 1.55, 1.25);
    }

    public void betMatchups4(){
        bets.add(new Bet(Name.WOB, -0.75, 2.850, 1.00,11));
        bets.add(new Bet(Name.BMG, +0.25, 1.820, 1.00,11));
        bets.add(new Bet(Name.SCF, +0.5, 1.300, 1.00,11));
        bets.add(new Bet(Name.BSC, -1.0, 3.100, 1.00,11));
        bets.add(new Bet(Name.FCN, +0.0 , 1.810, 1.00,11));
        bets.add(new Bet(Name.BVB, +0.25, 2.040, 1.00,11));
        bets.add(new Bet(Name.RBL, -0.5, 1.870, 1.00, 11));
    }

    public void finishMatchups4(){
        gamedays.get(3).getGames()[0].finishGame(2, 1, 2.00, 2.82);
        gamedays.get(3).getGames()[1].finishGame(2, 1, 1.82, 1.18);
        gamedays.get(3).getGames()[2].finishGame(1, 3, 1.47, 2.89);
        gamedays.get(3).getGames()[3].finishGame(1, 3, 1.83, 0.72);
        gamedays.get(3).getGames()[4].finishGame(4, 1, 2.60, 0.80);
        gamedays.get(3).getGames()[5].finishGame(0, 2, 0.48, 1.61);
        gamedays.get(3).getGames()[6].finishGame(3, 2, 2.56, 1.86);
        gamedays.get(3).getGames()[7].finishGame(3, 0, 2.56, 0.25);
        gamedays.get(3).getGames()[8].finishGame(3, 0, 2.62, 0.82);
    }



    public void createGamedays(){
        gamedays.add(new Gameday(8,
                Name.SGE, Name.DUE, -0.75,
                Name.B04, Name.H96, -1.0,
                Name.VFB, Name.BVB, +0.5,
                Name.FCA, Name.RBL, +0.25,
                Name.WOB, Name.FCB, +1.5,
                Name.FCN, Name.TSG, +0.5,
                Name.S04, Name.BRE, -0.5,
                Name.BSC, Name.SCF, -0.5,
                Name.BMG, Name.M05, -1.0));
        gamedays.add(new Gameday(9,
                Name.SCF, Name.BMG, +0.5,
                Name.BVB, Name.BSC, -1.25,
                Name.H96, Name.FCA, +0.0,
                Name.M05, Name.FCB, +1.75,
                Name.DUE, Name.WOB, +0.25,
                Name.TSG, Name.VFB, -1.0,
                Name.FCN, Name.SGE, +0.25,
                Name.RBL, Name.S04, -0.5,
                Name.BRE, Name.B04, -0.25));
        gamedays.add(new Gameday(10,
                Name.VFB, Name.SGE, +0.0,
                Name.FCB, Name.SCF, -2.75,
                Name.S04, Name.H96, -1.0,
                Name.B04, Name.TSG, -0.25,
                Name.FCA, Name.FCN, -1.0,
                Name.WOB, Name.BVB, +0.5,
                Name.BSC, Name.RBL, +0.25,
                Name.BMG, Name.DUE, -1.5,
                Name.M05, Name.BRE, +0.25));
        gamedays.add(new Gameday(11,
                Name.H96, Name.WOB, +0.0,
                Name.TSG, Name.FCA, -1.0,
                Name.BRE, Name.BMG, -0.25,
                Name.SCF, Name.M05, -0.25,
                Name.DUE, Name.BSC, +0.25,
                Name.FCN, Name.VFB, +0.0,
                Name.BVB, Name.FCB, +0.25,
                Name.RBL, Name.B04, -0.5,
                Name.SGE, Name.S04, -0.25));
    }

    /*public void createMatchups3(){
        games.add(new Game(teams.get(17), teams.get(6), 0.0));
        games.add(new Game(teams.get(5), teams.get(10), -2.75));
        games.add(new Game(teams.get(14), teams.get(15), -1.0));
        games.add(new Game(teams.get(13), teams.get(12), -0.25));
        games.add(new Game(teams.get(9), teams.get(11), -1.0));
        games.add(new Game(teams.get(8), teams.get(0), +0.5));
        games.add(new Game(teams.get(4), teams.get(1), +0.25));
        games.add(new Game(teams.get(2), teams.get(16), -1.5));
        games.add(new Game(teams.get(7), teams.get(3), +0.25));
    }

    public void createMatchups2(){
        games.add(new Game(teams.get(10), teams.get(2), +0.5));
        games.add(new Game(teams.get(0), teams.get(4), -1.25));
        games.add(new Game(teams.get(15), teams.get(9), +0.0));
        games.add(new Game(teams.get(7), teams.get(5), +1.75));
        games.add(new Game(teams.get(16), teams.get(8), +0.25));
        games.add(new Game(teams.get(12), teams.get(17), -1.0));
        games.add(new Game(teams.get(11), teams.get(6), +0.25));
        games.add(new Game(teams.get(1), teams.get(14), -0.5));
        games.add(new Game(teams.get(3), teams.get(13), -0.25));

    }
    public void createMatchups(){
        games.add(new Game(teams.get(Name.SGE.ordinal()), teams.get(Name.DUE.ordinal()), -0.75));
        games.add(new Game(teams.get(Name.B04.ordinal()), teams.get(Name.H96.ordinal()), -1.0));
        games.add(new Game(teams.get(Name.VFB.ordinal()), teams.get(Name.BVB.ordinal()), +0.5));
        games.add(new Game(teams.get(9), teams.get(1), +0.25));
        games.add(new Game(teams.get(8), teams.get(5), +1.5));
        games.add(new Game(teams.get(11), teams.get(12), +0.5));
        games.add(new Game(teams.get(14), teams.get(3), -0.5));
        games.add(new Game(teams.get(4), teams.get(10), -0.5));
        games.add(new Game(teams.get(2), teams.get(7), -1.0));

    }*/

}
