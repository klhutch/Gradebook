package gradebook;

import java.util.HashMap;

public abstract class Assignment {
    String name;
    int weight;
    HashMap<String, Integer> grades;
    Double totalPoints;
    
    Assignment(String name, Integer weight, HashMap<String, Integer> grades,
            Double totalPoints) {
        this.name = name;
        this.weight = weight;
        this.grades = grades;
        this.totalPoints = totalPoints;
    }
}