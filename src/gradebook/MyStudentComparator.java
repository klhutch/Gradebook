package gradebook;

import java.util.Comparator;
/**Class MyStudentComparator
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 8th, 2014
 *
 */
public class MyStudentComparator implements Comparator<Student> {
    
    /**
     * return result from comparing student's usernames
     */
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentUsername().compareTo(o2.getStudentUsername());
    }
    
}
