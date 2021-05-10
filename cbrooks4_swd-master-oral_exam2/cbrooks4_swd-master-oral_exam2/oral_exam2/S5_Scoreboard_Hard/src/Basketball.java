import java.util.HashMap;
import java.util.Map;

public class Basketball extends Sport{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  FIELDS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final Map<String, Integer> scoreTypes = new HashMap<>();
    /**
     * Constructor which sets home team, away team, and time type
     *
     * @param home name of the home team
     * @param away name of the away team
     */
    public Basketball(String home, String away) {
        super(home, away, "half");
        scoreTypes.put("2-pointer", 2);
        scoreTypes.put("3-pointer", 3);
        scoreTypes.put("Penalty-1", 1);
        scoreTypes.put("Penalty-2", 2);
        setMaxTime(2); // 2 halves in a basketball game
    }

    @Override
    public Map<String, Integer> getScoringMethods() {
        return scoreTypes;
    }
}
