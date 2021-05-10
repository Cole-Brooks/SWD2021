import java.util.Map;

public interface SportsMethods {
    /**
     * gets the home team
     * @return  the name of the home team
     */
    public String getHomeTeam();

    /**
     * gets the away team
     * @return  the name of the away team
     */
    public String getAwayTeam();

    /**
     * returns the game over boolean
     * @return  true if game over, else false
     */
    public boolean isGameOver();

    /**
     * gets the time
     * @return the time period
     */
    public int getCurrentTime();

    /**
     * gets the home teams score
     * @return score of the home team
     */
    public int getHomeScore();

    /**
     * gets the away teams score
     * @return away team score
     */
    public int getAwayScore();

    /**
     * sets the home teams score
     * @param newScore  new score
     */
    public void setHomeScore(int newScore);

    /**
     * sets the away teams score
     * @param newScore  new score
     */
    public void setAwayScore(int newScore);

    /**
     * returns the winner of the game
     * @return winner
     */
    public String winner();

    /**
     * returns all possible scoring types for the sport
     * @return Map including the names of scores mapped to their values
     */
    public Map<String, Integer> getScoringMethods();

    /**
     * increments the current time and sets the gameOver flag if necessary
     */
    public void incrementCurrentTime();
}
