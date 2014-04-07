package gradebook;

import java.util.HashMap;

/**Class Participation
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
class Participation extends Assignment {
    
    /** Constructor Participation
     * 
     * @param name - the name of the participation assignment
     * @param weight - the weight of participation
     * @param totalPoints - the number of points participation is worth
     */
    Participation(String name, Integer weight, Double totalPoints) {
        super(name, weight, totalPoints);
    }
}