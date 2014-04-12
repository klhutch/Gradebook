package gradebook;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**Class AssignmentTest
 * Team 10
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 11th, 2014
 *
 */
public class AssignmentTest {
    // Examples of Assignments.
    private Assignment hw1 = new Assignment("HW1", 10.0, 2.0);
    private Assignment hw1copy = new Assignment("HW1", 10.0, 2.0);
    private Assignment test1 = new Assignment("Test1", 100.0, 10.0);
    
    /**
     * Tests the accessor methods for assignments.
     */
    @Test
    public void testAssignmentAccessor() {
        assertEquals(hw1.getAssignmentName(), "HW1");
        assertTrue(hw1.getTotal().equals(10.0));
        assertTrue(hw1.getWeight().equals(2.0));
        
        assertEquals(test1.getAssignmentName(), "Test1");
        assertTrue(test1.getTotal().equals(100.0));
        assertTrue(test1.getWeight().equals(10.0));
    }
    
    /**
     * Tests the addAssignmentGrade method.
     */
    @Test
    public void testAddGrade() {
        test1.addAssignmentGrade("joberste", 87.0);
        assertTrue(test1.grades.get("joberste").equals(87.0));
    }

    /** 
     * Tests the equals and hashCode methods for assignment.
     */
    @Test
    public void testAssignmentEquals() {
        assertTrue(hw1.equals(hw1copy));
        assertFalse(test1.equals(hw1));
    }
    
    /**
     * Tests the toString method for assignment.
     */
    @Test
    public void testToString() {
        assertEquals(hw1.toString(), "HW1" + "\n" 
                + "10.0" + "\n" 
                + "2.0" + "\n"
                + hw1.grades.toString() + "\n");
        assertFalse(test1.toString().equals("Test1"));
    }
}