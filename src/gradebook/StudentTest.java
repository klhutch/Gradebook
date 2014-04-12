package gradebook;

import static org.junit.Assert.*;

import org.junit.Test;

/**Class StudentTest
 * Team 10
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 11th, 2014
 *
 */
public class StudentTest {
    // Examples of Students.
    private Student jesseOberstein = 
            new Student("joberste", "Jesse", "Oberstein", "Mazor", 2017);
    private Student kateHutchinson = 
            new Student("klhutch", "Kate", "Hutchinson", "Hughes", 2016);
    private Student nathanGoodman =
            new Student("nmg149", "Nathan", "Goodman", "Hughes", 2017);

    /**
     * Tests the accessor methods for Student.
     */
    @Test
    public void testAccessors() {        
        // Tests the accessor methods for student Jesse Oberstein.
        assertEquals(jesseOberstein.getStudentUsername(), "joberste");
        assertEquals(jesseOberstein.getFirstName(), "Jesse");
        assertEquals(jesseOberstein.getLastName(), "Oberstein");
        assertEquals(jesseOberstein.getAdvisor(), "Mazor");
        assertEquals(new Integer(jesseOberstein.getGradYear()),
                new Integer(2017));
        
        // Tests the accessor methods for student Kate Hutchinson.
        assertEquals(kateHutchinson.getStudentUsername(), "klhutch");
        assertEquals(kateHutchinson.getFirstName(), "Kate");
        assertEquals(kateHutchinson.getLastName(), "Hutchinson");
        assertEquals(kateHutchinson.getAdvisor(), "Hughes");
        assertEquals(new Integer(kateHutchinson.getGradYear()),
                new Integer(2016));
        
        // Tests the accessor methods for student Nathan Goodman
        assertEquals(nathanGoodman.getStudentUsername(), "nmg149");
        assertEquals(nathanGoodman.getFirstName(), "Nathan");
        assertEquals(nathanGoodman.getLastName(), "Goodman");
        assertEquals(nathanGoodman.getAdvisor(), "Hughes");
        assertEquals(new Integer(nathanGoodman.getGradYear()),
                new Integer(2017));
    }
    
    public void testEquals() {
        assertFalse(jesseOberstein.equals(nathanGoodman));
        assertTrue(kateHutchinson.equals(kateHutchinson));
    }
    
    public void testToString() {
        assertEquals(jesseOberstein.toString(),
                "joberste" + "\n" + "Jesse" + "\n" + "Oberstein" + "\n"
                        + "Mazor" + "\n" + "2017");
        assertFalse(nathanGoodman.toString().equals("Nathan Goodman"));
    }

}
