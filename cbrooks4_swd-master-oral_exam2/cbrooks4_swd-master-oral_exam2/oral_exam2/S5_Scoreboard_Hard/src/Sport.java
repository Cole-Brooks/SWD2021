import java.util.Map;

/**
 * Superclass for our sports subclass
 */
public abstract class Sport implements SportsMethods {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  FIELDS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /** Name of the home team */
    private String hTeam;
    /** Name of the away team */
    private String aTeam;
    /** type of time keeping (quarter, half, etc...) */
    private String timeType;
    /** current time */
    private int time = 1;
    /** boolean representing state of the game. True if game is over, else false*/
    private boolean gameOver = false;
    /** Home teams score */
    private int homeScore;
    /** Away teams score */
    private int awayScore;
    /** max time of the sport */
    private int maxTime;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructor which sets home team, away team, and time type
     * @param home the name of the home team
     * @param away the name of the away team
     * @param timeType the time type
     */
    public Sport(String home, String away, String timeType){
        setHomeTeam(home);
        setAwayTeam(away);
        setTimeType(timeType);
    }

    /**
     * getAwayTeam: gets the away team
     * @return the name of the away team
     */
    public String getAwayTeam(){
        return aTeam;
    }

    /**
     * getHomeTeam: gets the home team
     * @return the name of the home team
     */
    public String getHomeTeam(){
        return hTeam;
    }

    /**
     * getTimeType: gets the time type
     * @return the time type
     */
    public String getTimeType(){
        return this.timeType;
    }

    /**
     * setAwayTeam: sets the away team
     * @param aTeam the name of the away team
     */
    public void setAwayTeam(String aTeam) {
        this.aTeam = aTeam;
    }

    /**
     * setHomeTeam: sets the home team
     * @param hTeam the name of the home team
     */
    public void setHomeTeam(String hTeam) {
        this.hTeam = hTeam;
    }

    /**
     * setTimeType: sets the time type
     * @param timeType  the time type
     */
    public void setTimeType(String timeType){
        this.timeType = timeType;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * setTime: sets the current time
     * @param newTime the new time
     */
    public void setTime(int newTime){
        time = newTime;
    }

    /**
     * setMaxTime: sets the time limit in timeTypes for this sport
     * @param maxTime   the max time a sport can last
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    // Sports methods overrides
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getHomeScore(){
        return this.homeScore;
    }

    @Override
    public int getAwayScore(){
        return this.awayScore;
    }

    @Override
    public void setHomeScore(int newScore){
        homeScore = newScore;
    }

    @Override
    public void setAwayScore(int newScore){
        awayScore = newScore;
    }

    @Override
    public String winner(){
        if(homeScore > awayScore){
            return getHomeTeam();
        }
        else if(awayScore > homeScore){
            return getAwayTeam();
        }
        else{
            // we have a tie!
            return "Draw";
        }
    }

    @Override
    public int getCurrentTime() {
        return time;
    }
    @Override
    public void incrementCurrentTime(){
        setTime(getCurrentTime() + 1);
        if(getCurrentTime() == maxTime + 1){
            setGameOver(true);
        }
    }
}
