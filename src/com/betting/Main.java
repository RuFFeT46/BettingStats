package com.betting;


public class Main {

    public static String waitForInput(){
        String input = new java.util.Scanner(System.in).nextLine();
        if(input.equals("exit")){
            System.exit(0);
        }
        return input;
    }

    public static void menu(){
        System.out.println("Menü: \n" +
                "[1] NextGameDay - [2] GameInfo - [3] TeamInfoMenu - [4] BettingInfo - [exit] Exit");
        String input = waitForInput();
        if(input.equals("1")){
            gameDayInfoMenu(Database.getInstance().getGamedays().size()-1+8);
        }
        else if(input.equals("2")){
            gamedaysOverview();
        }
        else if(input.equals("3")){
            teamInfoMenu();
        }
        else if(input.equals("4")){
            bettingInfo();
        }
        else {
            System.out.println("Falsche Eingabe");
            menu();
        }
    }

    public static void teamInfoMenu(){
        int i = 0;
        for(Team t: Database.getInstance().getTeams()){
            System.out.println("[" + i + "] - " + t.getName());
            i++;
        }
        String input = waitForInput();
        if(input.equals("back")){
            menu();
        }
        int inputInt = Integer.parseInt(input);
        System.out.println(Database.getInstance().getTeams().get(inputInt).getTeamInfo());
        teamInfoMenu();
    }



    public static void gamedaysOverview(){
        for(Gameday gd : Database.getInstance().getGamedays()){
            System.out.println("[" + gd.getId() + "] - Spieltag " + gd.getId());
        }

        String input = waitForInput();
        if(input.equals("back")){
            menu();
        }

        int inputInt = Integer.parseInt(input);
        gameDayInfoMenu(inputInt);
    }

    public static void gameDayInfoMenu(int day){
        int i = 0;
        System.out.println("Spieltag " + day);
        for(Game g : Database.getInstance().getGamedays().get(day-8).getGames()){
            System.out.println("[" + i + "] - " + g.getHomeTeam().getName() + " vs. " + g.getAwayTeam().getName());
            i++;
        }
        String input = waitForInput();
        if(input.equals("back")){
            gamedaysOverview();
        }

        int inputInt = Integer.parseInt(input);
        System.out.println(Database.getInstance().getGamedays().get(day-8).getGames()[inputInt].getGameInfo());
        gameDayInfoMenu(day);
    }

    public static void bettingInfo(){
        System.out.println(Database.getInstance().getBettingInfo(1.0, 20.0));
        System.out.println(Database.getInstance().getBettingInfo(1.25, 1.7));
        System.out.println(Database.getInstance().getBettingInfo(1.7, 1.95));
        System.out.println(Database.getInstance().getBettingInfo(1.95, 2.3));
        System.out.println(Database.getInstance().getBettingInfo(2.3, 5.0) + "\n");
        menu();
    }

    public static void main(String[] args) {
        Database.getInstance();
        Database.getInstance().createGamedays();
        Database.getInstance().betMatchups();
        Database.getInstance().finishMatchups();
        Database.getInstance().betMatchups2();
        Database.getInstance().finishMatchups2();
        Database.getInstance().betMatchups3();
        Database.getInstance().finishMatchups3();
        Database.getInstance().betMatchups4();
        Database.getInstance().finishMatchups4();


        menu();

        //System.out.println(teams[0]);
        //System.out.println(Database.getInstance().getGames().get(6).getGameInfo());
        //System.out.println(Database.getInstance().getTeams().get(3).getTeamInfo());






    }
}
