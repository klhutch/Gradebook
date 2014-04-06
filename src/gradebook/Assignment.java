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
    
    /** Constructor Assignment
     * 
     * @param name - the name of the Assignment
     * @param weight - the weight of the Assignment
     * @param totalPoints - the total number of points an assignement is worth
     */
    Assignment(String name, Integer weight, Double totalPoints) {
        this.name = name;
        this.weight = weight;
        this.grades = new HashMap<String, Integer>();
        this.totalPoints = totalPoints;
    }
    
    @Override
    /** method equals
     * to determine the equivalence of two objects, one of which is
     * an assignment
     * 
     * @param obj - the Object to compare 'this' to
     * @return boolean - "Is 'this' equivalent to obj?"
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if ( !(obj.getClass().equals(this.getClass()))) {
            return false;
        }
        
        Assignment comp = (Assignment) obj;
        
        if ( !(this.name.equals(comp.name) && (this.weight == comp.weight) && 
                (this.grades.equals(comp.grades) &&
                        (this.totalPoints.equals(comp.totalPoints))))) {
            return false;
        }
        
        return true;
    }
    
    
    
}