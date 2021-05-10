import java.util.HashMap;
import java.util.Map;

public class Baseball extends Sport{
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
    public Baseball(String home, String away) {
        super(home, away, "inning");
        scoreTypes.put("Run", 1);
        setMaxTime(9); // 9 innings in a baseball game
    }

    @Override
    public Map<String, Integer> getScoringMethods(){
        return scoreTypes;
    }

}
