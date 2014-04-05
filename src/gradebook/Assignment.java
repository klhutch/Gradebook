package gradebook;

import java.util.HashMap;

/**Class Assignment
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
abstract class Assignment {
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