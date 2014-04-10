package gradebook;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GradeBookWhiteTest {
    
    
    String gradebooktxt = "GRADEBOOK" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "Opening Assignment" + "\t" +  "A2" + "\t" + "First Group Project" + "\t" + "Test" + "\n"
            + "10.0" + "\t" + "100.0" + "\t" + "150.0" + "\t" + "100.0" + "\n"
            + "1.0" + "\t" + "5.0" + "\t" + "10.0" + "\t" + "25.0" + "\n"
            + "abetaylor" + "\t" + "Isabella" + "\t" +  "Taylor" + "\t" + "Baker" + "\t" + "2016" + "\t" + "8.0" + "\t" + "71.0" + "\t" + "82.0" + "\t" + "65.0" + "\n"
            + "abethes" + "\t" + "Elizabeth" + "\t" + "White Jones" + "\t" + "Nelson" + "\t" + "2014" + "\t" + "6.0" + "\t" +  "90.0" + "\t" + "92.0" + "\t" + "88.0" + "\n"
            + "acit" + "\t" + "Jacob" + "\t" + "Smith" + "\t" + "Scott" + "\t" + "2014" + "\t" + "8.0" + "\t" +  "79.0" + "\t" + "122.0" + "\t" + "85.0" + "\n"
            + "ahrown" + "\t" + "Noah" + "\t" + "Brown" + "\t" + "Adams" + "\t" + "2017" + "\t" + "8.0" + "\t" + "85.0" + "\t" + "146.0" + "\t" + "57.0" + "\n"
            + "amller" + "\t" + "Liam" + "\t" + "Miller" + "\t" +  "Scott" + "\t" + "2014" + "\t" + "5.0" + "\t" + "74.0" + "\t" + "100.0" + "\t" + "82.0" + "\n"
            + "are" + "\t" + "Emily Ann" + "\t" + "Moore" + "\t" + "Scott" + "\t" +  "2014" + "\t" + "9.0" + "\t" + "58.0" + "\t" + "99.0" + "\t" + "68.0" + "\n"
            + "enwilson" + "\t" + "Aiden" + "\t" + "Wilson" + "\t" + "Nelson" + "\t" + "2014" + "\t" + "8.0" + "\t" + "83.0" + "\t" + "123.0" + "\t" + "97.0" + "\n"
            + "gailarti" + "\t" + "Abigail" + "\t" + "Martin" + "\t" + "Green" + "\t" + "2015" + "\t" + "7.0" + "\t" + "79.0" + "\t" + "132.0" + "\t" + "73.0" + "\n"
            + "iaartinez" + "\t" + "Sophia" + "\t" + "Martinez" + "\t" + "Scott" + "\t" + "2014" + "\t" + "6.0" + "\t" + "51.0" + "\t" + "79.0" + "\t" + "50.0" + "\n"
            + "illines" + "\t" + "William" + "\t" +  "Jones" + "\t" + "Nelson" + "\t" + "2014" + "\t" + "9.0" + "\t" + "52.0" + "\t" + "128.0" + "\t" + "79.0" + "\n"
            + "marson" + "\t" + "Emma" + "\t" + "Anderson" + "\t" + "Green" + "\t" + "2015" + "\t" + "7.0" + "\t" + "81.0" + "\t" + "136.0" + "\t" + "54.0" + "\n"
            + "michaeia" + "\t" + "Michael" + "\t" + "Garcia" + "\t" + "Baker" + "\t" + "2016" + "\t" + "5.0" + "\t" + "100.0" + "\t" + "121.0" + "\t" + "58.0" + "\n"
            + "mijacks" + "\t" + "Mia" + "\t" + "Jackson" + "\t" + "Baker" + "\t" + "2016" + "\t" + "5.0" + "\t" + "50.0" + "\t" + "93.0" + "\t" + "83.0" + "\n"
            + "oliviaas" + "\t" + "Olivia" + "\t" + "Thomas" + "\t" + "Adams" + "\t" + "2017" + "\t" + "6.0" + "\t" + "94.0" + "\t" + "78.0" + "\t" + "61.0" + "\n"
            + "onon" + "\t" + "Mason" + "\t" + "Johnson" + "\t" + "Green" + "\t" + "2015" + "\t" + "10.0" + "\t" + "81.0" + "\t" + "136.0" + "\t" + "51.0" + "\n"
            + "onson" + "\t" + "Madison" + "\t" + "Thompson" + "\t" + "Adams" + "\t" + "2017" + "\t" + "9.0" + "\t" + "89.0" + "\t" + "133.0" + "\t" + "71.0" + "\n"
            + "thms" + "\t" + "Ethan" + "\t" + "Williams" + "\t" + "Baker" + "\t" + "2016" + "\t" + "8.0" + "\t" + "89.0" + "\t" + "111.0" + "\t" + "92.0" + "\n"
            + "vaern" + "\t" + "Ava" + "\t" +  "Hernandez" + "\t" + "Nelson" + "\t" + "2014" + "\t" + "6.0" + "\t" + "91.0" + "\t" + "137.0" + "\t" + "83.0" + "\n"
            + "xaod" + "\t" + "Alexander" + "\t" + "Rodriguez" + "\t" + "Adams" + "\t" + "2017" + "\t" + "6.0" + "\t" + "98.0" + "\t" + "93.0" + "\t" + "91.0" + "\n"
            + "ydenavi" + "\t" +  "Jayden" + "\t" + "Davis" + "\t" + "Green" + "\t" + "2015" + "\t" + "10.0" + "\t" + "97.0" + "\t" + "134.0" + "\t" + "84.0";
    MyGradeBook gradebookFile = MyGradeBook.initializeWithFile("gradebook.txt");
    MyGradeBook gradebookString = MyGradeBook.initializeWithString(gradebooktxt);
    MyGradeBook emptyGradebook = MyGradeBook.initialize();
    
    /**
     * test statistics of MyGradeBook
     * @throws FileNotFoundException
     */
    /*@Test
    public void testGradeBookStats() throws FileNotFoundException {
        //TODO web-cat requires the usage of "Assert" functions
        System.out.println(gradebookString.average("Test"));
        System.out.println(gradebookString.median("Test"));
        System.out.println(gradebookString.min("Test"));
        System.out.println(gradebookString.max("Test"));
        System.out.println(gradebookString.outputAssignmentGrades("Test"));
        System.out.println(gradebookString.currentGrade("abetaylor"));
        System.out.println(gradebookString.currentGrades());
        
        System.out.println(gradebookFile.average("Test"));
        System.out.println(gradebookFile.outputAssignmentGrades("Test"));
        System.out.println(gradebookFile.currentGrade("abetaylor"));
        System.out.println(gradebookFile.outputCurrentGrades());
        System.out.println(gradebookFile.outputStudentGrades("iaartinez"));
        System.out.println(gradebookString.outputGradebook());
        System.out.println(MyGradeBook.initializeWithString(gradebookFile.outputGradebook()).outputGradebook());
    }
    */
    
    /**
     * tests the manual addition of assignments
     */
    @Test
    public void testAddAssignment() {
        //TODO write test for addAssignment
    }
    
    /**
     * tests the manual addition of students
     */
    @Test
    public void testAddStudent() {
        //TODO write test for addStudent
    }
    
    /**
     * tests the getAssignment method in MyGradeBook
     */
    @Test
    public void testGetAssignment() {
        //TODO write test for getAssignment
    }
    
    /**
     * tests the GetStudent method in MyGradeBook
     */
    @Test
    public void testGetStudent() {
        //TODO write test for getStudent
    }
    
    
    /**
     * tests the processFile method in MyGradeBook
     */
    @Test
    public void testProcessFile() {
        //TODO write test for processFile
    }
    
    /**
     * tests the processString method in MyGradeBook
     */
    @Test
    public void testProcessString() {
        //TODO write test for processString
    }
    
    /**
     * tests the changeGrade method of MyGradeBook
     */
    @Test
    public void testChangeGrade() {
        //TODO write test for changeGrade
    }
    
    /**
     * tests the average method in MyGradeBook
     */
    @Test
    public void testAverage() {
        //TODO write test for average
    }
    
    /**
     * tests the median method in MyGradeBook
     */
    @Test
    public void testMedian() {
        //TODO write test for median
    }
    
    /**
     * tests the min function in MyGradeBook
     */
    @Test
    public void testMin() {
        //TODO write test for min
    }
    
    /**
     * tests the max function in MyGradeBook
     */
    @Test
    public void testMax() {
        //TODO write test for max
    }
    
    /**
     * tests the currentGrade method in MyGradeBook
     */
    @Test
    public void testCurrentGrade() {
        //TODO write test for currentGrade
    }
    
    /**
     * tests the currentGrades method in MyGradeBook
     */
    @Test
    public void testCurrentGrades() {
        //TODO write test for currentGrades
    }
    
    /**
     * tests the assignmentGrade method in MyGradeBook
     */
    @Test
    public void testAssignmentGrade() {
        //TODO write test for assignmentGrade
    }
    
    
    /**
     * tests the initialization of a MyGradeBook
     */
    @Test
    public void testInitialize() {
        //initialize
        assertEquals(MyGradeBook.initialize(), emptyGradebook);
        
        //initialize with file
        assertEquals(MyGradeBook.initializeWithFile("gradebook.txt"), gradebookFile);
        
        //initialize with string
        //assertEquals(MyGradeBook.initializeWithString(gradebooktxt), gradebookString);
    }
    
    //TODO add tests for the output functions
    
}
