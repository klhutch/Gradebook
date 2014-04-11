package gradebook;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
    private MyGradeBook emptyGradebook = MyGradeBook.initialize();
   // Student jesse = new Student("joberste");

    @Test
    public void test() {
        emptyGradebook.addStudent("joberste", "Jesse",
                "Oberstein", "Mazor", 2017);
        emptyGradebook.addStudent("klhutch", "Kate", "Hutchinson",
                "Hughes", 2016);
        emptyGradebook.addStudent("nmg149", "Nathan", "Goodman",
                "Hughes", 2017);

        Student jesseOberstein = emptyGradebook.getStudent("joberste");
        Student kateHutchinson = emptyGradebook.getStudent("klhutch");
        Student nathanGoodman = emptyGradebook.getStudent("nmg149");
        
        // Tests the accessor methods for student Jesse Oberstein.
        assertEquals(jesseOberstein.getStudentUsername(), "joberste");
        assertEquals(jesseOberstein.getFirstName(), "Jesse");
        assertEquals(jesseOberstein.getLastName(), "Oberstein");
        assertEquals(jesseOberstein.getAdvisor(), "Mazor");
        assertTrue(jesseOberstein.getGradYear() == 2017);
        
        // Tests the accessor methods for student Kate Hutchinson.
        assertEquals(kateHutchinson.getStudentUsername(), "klhutch");
        assertEquals(kateHutchinson.getFirstName(), "Kate");
        assertEquals(kateHutchinson.getLastName(), "Hutchinson");
        assertEquals(kateHutchinson.getAdvisor(), "Hughes");
        assertTrue(kateHutchinson.getGradYear() == 2016);
        
        // Tests the accessor methods for student Nathan Goodman
        assertEquals(nathanGoodman.getStudentUsername(), "nmg149");
        assertEquals(nathanGoodman.getFirstName(), "Nathan");
        assertEquals(nathanGoodman.getLastName(), "Goodman");
        assertEquals(nathanGoodman.getAdvisor(), "Hughes");
        assertTrue(nathanGoodman.getGradYear() == 2017);
    }

}
