package gradebook;

import java.util.HashMap;

class Lab extends Assignment {
    
    Lab(String name, Integer weight, HashMap<String, Integer> grades, 
            Double totalPoints) {
        super(name, weight, grades, totalPoints);
    }
}
