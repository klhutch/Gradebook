package gradebook;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GradeBookWhiteTest {
    
    //MYGRADEBOOK TESTS 
    //TODO either change the class name or create new test class for these
    
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
    
    /**
     * test statistics of MyGradeBook
     * @throws FileNotFoundException
     */
    @Test
    public void testGradeBookStats() throws FileNotFoundException {
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
    
    /**
     * tests the initialization of a MyGradeBook
     */
    @Test
    public void testGradeBookInitialize() {
        assertTrue(MyGradeBook.initializeWithFile("gradebook.txt").equals(gradebookFile));
        assertSame(MyGradeBook.initializeWithString(gradebooktxt), 
                gradebookString);
    }
    
}
