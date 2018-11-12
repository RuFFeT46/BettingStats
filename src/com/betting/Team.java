package com.betting;

import java.util.ArrayList;

public class Team {

    private String name;
    private ArrayList<Bet> teamBets;
    private ArrayList<Game> teamGames;

    public Team(String name) {
        this.name = name;
        this.teamBets = new ArrayList<>();
        this.teamGames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Bet> getTeamBets() {
        return teamBets;
    }

    public void setTeamBets(ArrayList<Bet> teamBets) {
        this.teamBets = teamBets;
    }

    public ArrayList<Game> getTeamGames() {
        return teamGames;
    }

    public void setTeamGames(ArrayList<Game> teamGames) {
        this.teamGames = teamGames;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ",\nteamGames=" + teamGames +
                '}';
    }

    public Money getWinnings(boolean allGames){
        Money winnings = new Money(0);
        for(Bet b : teamBets){
            if(allGames || b.getTeam() == this){
                winnings.setCent(winnings.getCent() + -b.getMoney().getCent() + b.getWin().getCent());
            }
        }
        return winnings;
    }

    public String teamAgainstHandi(boolean home, boolean away){
        int win = 0;
        int lose = 0;
        int tie = 0;
        for(Game g : teamGames){
            if(g.isFinished() && this == g.getHomeTeam() && home){
                if(g.getHomeGoals() + g.getHomeHandicap() > g.getAwayGoals()){
                    win++;
                }
                else if(g.getHomeGoals() + g.getHomeHandicap() < g.getAwayGoals()){
                    lose++;
                }
                else{
                    tie++;
                }
            }
            else if(g.isFinished() && this == g.getAwayTeam() && away){
                if(g.getAwayGoals() - g.getHomeHandicap() > g.getHomeGoals()){
                    win++;
                }
                else if(g.getAwayGoals() - g.getHomeHandicap() < g.getHomeGoals()){
                    lose++;
                }
                else{
                    tie++;
                }
            }
        }

        return win + " : " + lose + " : " + tie;
    }

    public String teamAgainstxGHandi(boolean home, boolean away){
        int win = 0;
        int lose = 0;
        for(Game g : teamGames){
            if(g.isFinished() && this == g.getHomeTeam() && home){
                if(g.getxGHome() + g.getHomeHandicap() > g.getxGAway()){
                    win++;
                }
                else if(g.getxGHome() + g.getHomeHandicap() < g.getxGAway()){
                    lose++;
                }
            }
            else if(g.isFinished() && this == g.getAwayTeam() && away){
                if(g.getxGAway() - g.getHomeHandicap() > g.getxGHome()){
                    win++;
                }
                else if(g.getxGAway() - g.getHomeHandicap() < g.getxGHome()){
                    lose++;
                }
            }
        }

        return win + " : " + lose;
    }

    public String winrateOdds(double low, double high){
        int win = 0;
        int lose = 0;
        Money einsatz = new Money(0);
        Money winnings = new Money(0);

        for(Bet b: teamBets){
            if(b.getBetGame().isFinished() && b.getTeam() == this){
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

        return "Winrate für Wetten mit Quoten von " + low + " bis " + high + ": "
                + win + " : " + lose + " - " + winrate + "%"
                + " - Winnings: " + winnings + " bei Einsatz von " + einsatz + " (" + moneyrate + "%)"
                ;

    }

    public String winrateWithHandi(double handi, boolean home, boolean away){
        int win = 0;
        int lose = 0;
        for(Game g : teamGames){
            if(g.isFinished() && this == g.getHomeTeam() && home){
                if(g.getHomeHandicap() > handi - 0.25 && g.getHomeHandicap() < handi + 0.25){
                    if(g.getHomeGoals() + g.getHomeHandicap() > g.getAwayGoals()){
                        win++;
                    }
                    else if(g.getHomeGoals() + g.getHomeHandicap() < g.getAwayGoals()){
                        lose++;
                    }
                }
            }
            if(g.isFinished() && this == g.getAwayTeam() && away){
                double awayHandicap = g.getHomeHandicap()*-1;
                if(awayHandicap >= handi - 0.25 && awayHandicap <= handi + 0.25){
                    if(g.getAwayGoals() + awayHandicap > g.getHomeGoals()){
                        win++;
                    }
                    else if(g.getAwayGoals() + awayHandicap < g.getHomeGoals()){
                        lose++;
                    }
                }
            }
        }
        return win + " : " + lose;
    }

    public String winrateAgainstDiffOdds(boolean home, boolean away, double diff){
        int win= 0;
        int lose= 0;
        for(Game g : teamGames){
            if(g.isFinished() && this == g.getHomeTeam() && home){
                if(g.getHomeGoals() + g.getHomeHandicap()+diff > g.getAwayGoals()){
                    win++;
                }
                else if(g.getHomeGoals() + g.getHomeHandicap()+diff < g.getAwayGoals()){
                    lose++;
                }
            }

            if(g.isFinished() && this == g.getAwayTeam() && away){
                double awayHandicap = g.getHomeHandicap()*-1;
                if(g.getAwayGoals() + awayHandicap+diff > g.getHomeGoals()){
                    win++;
                }
                else if(g.getAwayGoals() + awayHandicap+diff < g.getHomeGoals()){
                    lose++;
                }

            }
        }
        return win + " : " + lose;
    }

    public String getTeamInfo(){
        return "Teaminfo für " + name + "\n"
                + "Gewinne durch Wetten auf: " + this.getWinnings(false) + "\n"
                + "Gewinne durch Wetten mit: " + this.getWinnings(true) + "\n"
                + "Handicap Gesamt: " + this.teamAgainstHandi(true, true) + "\n"
                + "Handicap Heim: " + this.teamAgainstHandi(true, false) + "\n"
                + "Handicap Auswärts: " + this.teamAgainstHandi(false, true) + "\n"
                + "Handicap xG Gesamt: " + this.teamAgainstxGHandi(true, true) + "\n"
                + "Handicap xG Heim: " + this.teamAgainstxGHandi(true, false) + "\n"
                + "Handicap xG Auswärts: " + this.teamAgainstxGHandi(false, true) + "\n"
                + "Test: " + this.winrateWithHandi(0.5, true, true) + "\n"
                + winrateOdds(1.0, 1.3) + "\n"
                + winrateOdds(1.3, 1.7) + "\n"
                + winrateOdds(1.7, 2.0) + "\n"
                + winrateOdds(2.0, 2.5) + "\n"
                + winrateOdds(2.5, 5.0) + "\n"
                ;
    }
}
