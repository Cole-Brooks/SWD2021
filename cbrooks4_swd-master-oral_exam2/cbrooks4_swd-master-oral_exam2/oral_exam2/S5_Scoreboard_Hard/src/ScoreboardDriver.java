import java.util.Map;
import java.util.Scanner;

public class ScoreboardDriver{
    public static void main(String args[]){
        // sport object we're going to be dealing with
        Sport sport;

        // variable used to select the sport
        int sportSelect = -1;

        // User interfacing - Prompt user to select sport
        System.out.println("Select the type of game from the list below:");
        String[] sports = {"Baseball", "Basketball", "Football", "Hockey"};

        for(int i = 0; i < sports.length; i++){
            System.out.println(i+1 + ". " + sports[i]);
        }

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Choice:");
        sportSelect = scnr.nextInt();

        while(sportSelect > 4 || sportSelect < 1){
            // user entered something bad... Prompt for a new int
            System.out.println("Invalid input... Try again!");
            sportSelect = scnr.nextInt();
        }

        // User interfacing - Prompt user to name both teams
        System.out.println("Enter the name of the home team:");
        String homeTeam = scnr.next();

        System.out.println("Enter the name of the away team:");
        String awayTeam = scnr.next();

        // Create the sport:
        switch(sportSelect){
            case 1:
                // user selected baseball
                sport = new Baseball(homeTeam, awayTeam);
                break;
            case 2:
                // user selected basketball
                sport = new Basketball(homeTeam, awayTeam);
                break;
            case 3:
                // user selected football
                sport = new Football(homeTeam, awayTeam);
                break;
            case 4:
                // user selected hockey
                sport = new Hockey(homeTeam, awayTeam);
                break;
            default:
                // no idea how we got here, but we're just going to default to baseball
                sport = new Baseball(homeTeam, awayTeam);


        }

        // map of possible scores
        Map<String, Integer> scoreEvents = sport.getScoringMethods();
        // list of score strings
        String[] scoreString = scoreEvents.keySet().toArray(new String[0]);

        // Run the game
        while(!sport.isGameOver()){
            // print current state of game
            System.out.println(sport.getHomeTeam() + " - " + sport.getHomeScore());
            System.out.println(sport.getAwayTeam() + " - " + sport.getAwayScore());
            System.out.println("Current " + sport.getTimeType() + ": " + sport.getCurrentTime() + "\n");


            // Construct and print possible events
            // scoring events:
            int event = 1;
            // home scores
            for(String key: scoreEvents.keySet()){
                System.out.println(event + ". " + sport.getHomeTeam() + " " + key);
                event++;
            }
            // away scores
            for(String key: scoreEvents.keySet()){
                System.out.println(event + ". " + sport.getAwayTeam() + " " + key);
                event++;
            }
            // increment time event
            System.out.println(event + ". End " + sport.getTimeType());
            int sel = -1;
            System.out.println("Enter Choice:");
            sel = scnr.nextInt();

            if(sel>0 && sel<scoreEvents.size() + 1){
                // selected a home team score
                sport.setHomeScore(sport.getHomeScore() + scoreEvents.get(scoreString[sel-1]));
            }
            else if(sel >scoreEvents.size() && sel < scoreEvents.size()*2 + 1){
                // selected an away team score
                sport.setAwayScore(sport.getAwayScore() + scoreEvents.get(scoreString[(sel-scoreEvents.size())-1]));
            }
            else if(sel == (scoreEvents.size()*2)+1){
                // selected to increase the time period
                sport.incrementCurrentTime();
            }
        }
        // game is over. Display the winner:
        String winner = sport.winner();
        if(winner.equals("Draw")){
            System.out.println("* * * * * * * * Tie Game! * * * * * * * *");
        }
        else{
            System.out.println("* * * * * * * * " + winner + " Win! * * * * * * * *");
        }
    }
}