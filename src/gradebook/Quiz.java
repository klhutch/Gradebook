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
    
    /** Constructor Quiz
     * 
     * @param name - the name of the quiz
     * @param weight - the weight of the quiz
     * @param totalPoints - the number of points the quiz is worth
     */
    Quiz(String name, Integer weight, Double totalPoints) {
        super(name, weight, totalPoints);
    }
}
