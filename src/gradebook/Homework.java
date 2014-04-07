package gradebook;

import java.util.HashMap;

/**Class Homework
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
class Homework extends Assignment {
    
    /** Constructor Homework
     * 
     * @param name - the name of the homework assignment
     * @param weight - the weight of the homework assignment
     * @param totalPoints - the number of points the homework is worth
     */
    Homework(String name, Integer weight, Double totalPoints) {
        super(name, weight, totalPoints);
    }

}
