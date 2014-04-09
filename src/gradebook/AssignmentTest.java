package gradebook;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**Class AssignmentTest
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 8th, 2014
 *
 */
public class AssignmentTest {
    Assignment quiz1 = new Assignment("Quiz 1", 200.0, 5.0);
    Assignment fakeQuiz = new Assignment("Homework 1", 10.0, 1.0);
    Assignment hw1 = new Assignment("Homework 1", 10.0, 1.0);
    Assignment hw2test = new Assignment("Homework 1", 10.0, 1.0);
    
    
    /**
     * creates grades to be used in the assignments
     */
    private void createAssignmentsGrades() {
        quiz1.addAssignmentGrade("joberste", 42.0);
        quiz1.addAssignmentGrade("nmg146", 100.0);
        quiz1.addAssignmentGrade("klhutch", 200.0);

        fakeQuiz.addAssignmentGrade("joberste", 42.0);
        fakeQuiz.addAssignmentGrade("nmg146", 100.0);
        fakeQuiz.addAssignmentGrade("klhutch", 200.0);

        hw1.addAssignmentGrade("joberste", 42.0);
        hw1.addAssignmentGrade("nmg146", 100.0);
        hw1.addAssignmentGrade("klhutch", 200.0);

        hw2test.addAssignmentGrade("joberste", 42.0);
        hw2test.addAssignmentGrade("nmg146", 100.0);
        hw2test.addAssignmentGrade("klhutch", 200.0);
    }

    /** 
     * Tests the equals method for assignment.
     */
    @Test
    public void testAssignmentEquals() {
        assertFalse(hw1.equals(quiz1));
        assertFalse(quiz1.equals(fakeQuiz));
        assertTrue(hw1.equals(hw2test));
    }
    
  
}