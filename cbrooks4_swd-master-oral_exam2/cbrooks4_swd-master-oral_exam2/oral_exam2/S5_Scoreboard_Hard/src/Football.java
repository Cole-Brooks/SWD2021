import java.util.HashMap;
import java.util.Map;

public class Football extends Sport{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  FIELDS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, Integer> scoreTypes = new HashMap<>();

    /**
     * Constructor which sets home team, away team, and time type
     *
     * @param home  name of the home team
     * @param away name of the away team
     */
    public Football(String home, String away) {
        super(home, away, "quarter");
        // initialize the score types
        scoreTypes.put("Touchdown", 6);
        scoreTypes.put("Extra Point", 1);
        scoreTypes.put("2 Point Conversion", 2);
        scoreTypes.put("Field Goal", 3);
        scoreTypes.put("Safety", 2);
        setMaxTime(4); // 4 quarters in a football game
    }

    @Override
    public Map<String, Integer> getScoringMethods() {
        return scoreTypes;
    }

}
