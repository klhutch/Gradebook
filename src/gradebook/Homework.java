package gradebook;

import java.util.HashMap;

public class Homework extends Assignment {
    
    Homework(String name, Integer weight, HashMap<String, Integer> grades, 
            Double totalPoints) {
        super(name, weight, grades, totalPoints);
    }

}
