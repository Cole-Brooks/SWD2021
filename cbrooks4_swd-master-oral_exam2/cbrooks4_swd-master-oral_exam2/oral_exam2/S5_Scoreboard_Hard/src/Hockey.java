import java.util.HashMap;
import java.util.Map;

public class Hockey extends Sport{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  FIELDS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, Integer> scoreTypes = new HashMap<>();
    /**
     * Constructor which sets home team, away team, and time type
     *
     * @param home name of the home team
     * @param away name of the away team
     */
    public Hockey(String home, String away) {
        super(home, away, "period");
        scoreTypes.put("Goal", 1);
        setMaxTime(3); // 3 periods in a hockey match
    }

    @Override
    public Map<String, Integer> getScoringMethods() {
        return scoreTypes;
    }

}
