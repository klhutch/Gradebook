package gradebook;

import java.util.HashMap;

public class Quiz extends Assignment{
    
    Quiz(String name, Integer weight, HashMap<String, Integer> grades, 
            Double totalPoints) {
        super(name, weight, grades, totalPoints);
    }
}
