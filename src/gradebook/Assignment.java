package gradebook;

import java.util.HashMap;

public abstract class Assignment {

    final Integer name;
    final HashMap<String, Integer> grades;
    final Integer totalPoints;
    
    Assignment() {
        this.name = null;
        this.grades = null;
        this.totalPoints = null;
    }
}
