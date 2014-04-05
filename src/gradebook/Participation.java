package gradebook;

import java.util.HashMap;

public class Participation extends Assignment {
    
    Participation(String name, Integer weight, HashMap<String, Integer> grades, 
            Double totalPoints) {
        super(name, weight, grades, totalPoints);
    }
}
