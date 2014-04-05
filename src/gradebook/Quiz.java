package gradebook;

import java.util.HashMap;

/**Class Quiz
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
class Quiz extends Assignment{
    
    Quiz(String name, Integer weight, HashMap<String, Integer> grades, 
            Double totalPoints) {
        super(name, weight, grades, totalPoints);
    }
}
