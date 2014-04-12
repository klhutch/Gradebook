package gradebook;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Test;

/**Class MyGradebook
* Team 10
* 
* @author Kate Hutchinson (klhutch)
* @author Jesse Oberstein (joberste)
* @author Nathan Goodman (nmg49)
* 
* @version April 11th, 2014
*
*/
public class GradeBookTest {
    
    ////////////////////////////////////////////////////////////////////
    ///////        Examples for MyGradeBook             ////////////////
    ////////////////////////////////////////////////////////////////////
    
    // Example gradebook strings.
    private String gradebooktxt = "GRADEBOOK" + "\n" + "\t" + "\t" + "\t"
            + "\t" + "\t" + "Opening Assignment" + "\t" + "A2" + "\t"
            + "First Group Project" + "\t" + "Test" + "\n" + "\t" + "\t" + "\t"
            + "\t" + "\t" + "10.0" + "\t" + "100.0" + "\t" + "150.0" + "\t"
            + "100.0" + "\n" + "\t" + "\t" + "\t" + "\t" + "\t" + "1.0" + "\t"
            + "5.0" + "\t" + "10.0" + "\t" + "25.0" + "\n" 
            + "abetaylor" + "\t" + "Isabella" + "\t" +  "Taylor" + "\t" 
            + "Baker" + "\t" + "2016" + "\t" + "8.0" + "\t" + "71.0" + "\t" 
            + "82.0" + "\t" + "65.0" + "\n"
            + "abethes" + "\t" + "Elizabeth" + "\t" + "White Jones" + "\t"
            + "Nelson" + "\t" + "2014" + "\t" + "6.0" + "\t" +  "90.0" + "\t"
            + "92.0" + "\t" + "88.0" + "\n"
            + "acit" + "\t" + "Jacob" + "\t" + "Smith" + "\t" + "Scott" 
            + "\t" + "2014" + "\t" + "8.0" + "\t" +  "79.0" + "\t" + "122.0"
            + "\t" + "85.0" + "\n"
            + "ahrown" + "\t" + "Noah" + "\t" + "Brown" + "\t" + "Adams" + "\t"
            + "2017" + "\t" + "8.0" + "\t" + "85.0" + "\t" + "146.0" + "\t"
            + "57.0" + "\n"
            + "amller" + "\t" + "Liam" + "\t" + "Miller" + "\t" +  "Scott" 
            + "\t" + "2014" + "\t" + "5.0" + "\t" + "74.0" + "\t" + "100.0" 
            + "\t" + "82.0" + "\n"
            + "are" + "\t" + "Emily Ann" + "\t" + "Moore" + "\t" + "Scott" 
            + "\t" +  "2014" + "\t" + "9.0" + "\t" + "58.0" + "\t" + "99.0" 
            + "\t" + "68.0" + "\n"
            + "enwilson" + "\t" + "Aiden" + "\t" + "Wilson" + "\t" + "Nelson" 
            + "\t" + "2014" + "\t" + "8.0" + "\t" + "83.0" + "\t" + "123.0" 
            + "\t" + "97.0" + "\n"
            + "gailarti" + "\t" + "Abigail" + "\t" + "Martin" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "7.0" + "\t" + "79.0" + "\t" + "132.0" 
            + "\t" + "73.0" + "\n"
            + "iaartinez" + "\t" + "Sophia" + "\t" + "Martinez" + "\t" 
            + "Scott" + "\t" + "2014" + "\t" + "6.0" + "\t" + "51.0" + "\t" 
            + "79.0" + "\t" + "50.0" + "\n"
            + "illines" + "\t" + "William" + "\t" +  "Jones" + "\t" + "Nelson"
            + "\t" + "2014" + "\t" + "9.0" + "\t" + "52.0" + "\t" + "128.0"
            + "\t" + "79.0" + "\n"
            + "marson" + "\t" + "Emma" + "\t" + "Anderson" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "7.0" + "\t" + "81.0" + "\t" + "136.0"
            + "\t" + "54.0" + "\n"
            + "michaeia" + "\t" + "Michael" + "\t" + "Garcia" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "5.0" + "\t" + "100.0" + "\t" + "121.0"
            + "\t" + "58.0" + "\n"
            + "mijacks" + "\t" + "Mia" + "\t" + "Jackson" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "5.0" + "\t" + "50.0" + "\t" + "93.0"
            + "\t" + "83.0" + "\n"
            + "oliviaas" + "\t" + "Olivia" + "\t" + "Thomas" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "6.0" + "\t" + "94.0" + "\t" + "78.0"
            + "\t" + "61.0" + "\n"
            + "onon" + "\t" + "Mason" + "\t" + "Johnson" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "10.0" + "\t" + "81.0" + "\t" + "136.0"
            + "\t" + "51.0" + "\n"
            + "onson" + "\t" + "Madison" + "\t" + "Thompson" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "9.0" + "\t" + "89.0" + "\t" + "133.0"
            + "\t" + "71.0" + "\n"
            + "thms" + "\t" + "Ethan" + "\t" + "Williams" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "8.0" + "\t" + "89.0" + "\t" + "111.0"
            + "\t" + "92.0" + "\n"
            + "vaern" + "\t" + "Ava" + "\t" +  "Hernandez" + "\t" + "Nelson"
            + "\t" + "2014" + "\t" + "6.0" + "\t" + "91.0" + "\t" + "137.0"
            + "\t" + "83.0" + "\n"
            + "xaod" + "\t" + "Alexander" + "\t" + "Rodriguez" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "6.0" + "\t" + "98.0" + "\t" + "93.0"
            + "\t" + "91.0" + "\n"
            + "ydenavi" + "\t" +  "Jayden" + "\t" + "Davis" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "10.0" + "\t" + "97.0" + "\t" + "134.0"
            + "\t" + "84.0" + "\n";
    
    private String shortGradebook = "GRADEBOOK" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "HW1" + "\t" + "Test1" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "10" + "\t" + "100" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "2" + "\t" + "10" + "\n"
            + "joberste" + "\t" + "Jesse" + "\t" + "Oberstein" + "\t" + "Mazor"
            + "\t" + "2017" + "\t" + "8" + "\t" + "95" + "\n"
            + "klhutch" + "\t" + "Kate" + "\t" + "Hutchinson" + "\t" + "Hughes"
            + "\t" + "2016" + "\t" + "10" + "\t" + "100" + "\n"
            + "nmg149" + "\t" + "Nathan" + "\t" + "Goodman" + "\t" + "Hughes"
            + "\t" + "2017" + "\t" + "5" + "\t" + "85";
    
    private String shortGradebookOut = "GRADEBOOK" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "HW1" + "\t" + "Test1" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "10.0" + "\t" + "100.0" + "\n"
            + "\t" + "\t" + "\t" + "\t" + "\t" + "2.0" + "\t" + "10.0" + "\n"
            + "joberste" + "\t" + "Jesse" + "\t" + "Oberstein" + "\t" + "Mazor"
            + "\t" + "2017" + "\t" + "8.0" + "\t" + "95.0" + "\n"
            + "klhutch" + "\t" + "Kate" + "\t" + "Hutchinson" + "\t" + "Hughes"
            + "\t" + "2016" + "\t" + "10.0" + "\t" + "100.0" + "\n"
            + "nmg149" + "\t" + "Nathan" + "\t" + "Goodman" + "\t" + "Hughes"
            + "\t" + "2017" + "\t" + "5.0" + "\t" + "85.0" + "\n";
    
    private String initialtxt = "GRADEBOOK" + "\n" + "\t" + "\t" + "\t" + "\t"
            + "\t" + "Opening Assignment" + "\t" + "A2" + "\n" + "\t" + "\t"
            + "\t" + "\t" + "\t" + "10" + "\t" + "100" + "\n" + "\t" + "\t"
            + "\t" + "\t" + "\t" + "1" + "\t" + "5" + "\n"
            + "abetaylor" + "\t" + "Isabella" + "\t" + "Taylor" + "\t" 
            + "Baker" + "\t" + "2016" + "\t" + "8" + "\t" + "71" + "\n"
            + "abethes" + "\t" + "Elizabeth" + "\t" + "White Jones" + "\t" 
            + "Nelson" + "\t" + "2014" + "\t" + "6" + "\t" + "90" + "\n"
            + "acit" + "\t" + "Jacob" + "\t" + "Smith" + "\t" + "Scott" + "\t"
            + "2014" + "\t" + "8" + "\t" + "79" + "\n"
            + "ahrown" + "\t" + "Noah" + "\t" + "Brown" + "\t" + "Adams" + "\t"
            + "2017" + "\t" + "8" + "\t" + "85" + "\n"
            + "amller" + "\t" + "Liam" + "\t" + "Miller" + "\t" + "Scott"
            + "\t" + "2014" + "\t" + "5" + "\t" + "74" + "\n"
            + "are" + "\t" + "Emily Ann" + "\t" + "Moore" + "\t" + "Scott"
            + "\t" + "2014" + "\t" + "9" + "\t" + "58" + "\n"
            + "enwilson" + "\t" + "Aiden" + "\t" + "Wilson" + "\t" + "Nelson"
            + "\t" + "2014" + "\t" + "8" + "\t" + "83" + "\n"
            + "gailarti" + "\t" + "Abigail" + "\t" + "Martin" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "7" + "\t" + "79" + "\n"
            + "marson" + "\t" + "Emma" + "\t" + "Anderson" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "7" + "\t" + "81" + "\n"
            + "michaeia" + "\t" + "Michael" + "\t" + "Garcia" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "5" + "\t" + "100" + "\n"
            + "mijacks" + "\t" +  "Mia" + "\t" + "Jackson" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "5" + "\t" + "50" + "\n"
            + "oliviaas" + "\t" + "Olivia" + "\t" + "Thomas" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "6" + "\t" + "94" + "\n"
            + "onon" + "\t" + "Mason" + "\t" + "Johnson" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "10" + "\t" + "81" + "\n"
            + "onson" + "\t" + "Madison" + "\t" + "Thompson" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "9" + "\t" + "89" + "\n"
            + "thms" + "\t" + "Ethan" + "\t" + "Williams" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "8" + "\t" + "89" + "\n"
            + "vaern" + "\t" + "Ava" + "\t" +  "Hernandez" + "\t" + "Nelson"
            + "\t" + "2014" + "\t" + "6" + "\t" + "91" + "\n"
            + "ydenavi" + "\t" + "Jayden" + "\t" + "Davis" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "10" + "\t" + "97" + "\n";
    
    private String initialtxtOut = "GRADEBOOK" + "\n" + "\t" + "\t" + "\t"
            + "\t" + "\t" + "Opening Assignment" + "\t" + "A2" + "\n" + "\t"
            + "\t" + "\t" + "\t" + "\t" + "10.0" + "\t" + "100.0" + "\n" + "\t"
            + "\t" + "\t" + "\t" + "\t" + "1.0" + "\t" + "5.0" + "\n"
            + "abetaylor" + "\t" + "Isabella" + "\t" + "Taylor" + "\t" 
            + "Baker" + "\t" + "2016" + "\t" + "8.0" + "\t" + "71.0" + "\n"
            + "abethes" + "\t" + "Elizabeth" + "\t" + "White Jones" + "\t" 
            + "Nelson" + "\t" + "2014" + "\t" + "6.0" + "\t" + "90.0" + "\n"
            + "acit" + "\t" + "Jacob" + "\t" + "Smith" + "\t" + "Scott" + "\t" 
            + "2014" + "\t" + "8.0" + "\t" + "79.0" + "\n"
            + "ahrown" + "\t" + "Noah" + "\t" + "Brown" + "\t" + "Adams" + "\t"
            + "2017" + "\t" + "8.0" + "\t" + "85.0" + "\n"
            + "amller" + "\t" + "Liam" + "\t" + "Miller" + "\t" + "Scott" 
            + "\t" + "2014" + "\t" + "5.0" + "\t" + "74.0" + "\n"
            + "are" + "\t" + "Emily Ann" + "\t" + "Moore" + "\t" + "Scott" 
            + "\t" + "2014" + "\t" + "9.0" + "\t" + "58.0" + "\n"
            + "enwilson" + "\t" + "Aiden" + "\t" + "Wilson" + "\t" + "Nelson"
            + "\t" + "2014" + "\t" + "8.0" + "\t" + "83.0" + "\n"
            + "gailarti" + "\t" + "Abigail" + "\t" + "Martin" + "\t" + "Green" 
            + "\t" + "2015" + "\t" + "7.0" + "\t" + "79.0" + "\n"
            + "marson" + "\t" + "Emma" + "\t" + "Anderson" + "\t" + "Green"
            + "\t" + "2015" + "\t" + "7.0" + "\t" + "81.0" + "\n"
            + "michaeia" + "\t" + "Michael" + "\t" + "Garcia" + "\t" + "Baker"
            + "\t" + "2016" + "\t" + "5.0" + "\t" + "100.0" + "\n"
            + "mijacks" + "\t" +  "Mia" + "\t" + "Jackson" + "\t" + "Baker" 
            + "\t" + "2016" + "\t" + "5.0" + "\t" + "50.0" + "\n"
            + "oliviaas" + "\t" + "Olivia" + "\t" + "Thomas" + "\t" + "Adams"
            + "\t" + "2017" + "\t" + "6.0" + "\t" + "94.0" + "\n"
            + "onon" + "\t" + "Mason" + "\t" + "Johnson" + "\t" + "Green" 
            + "\t" + "2015" + "\t" + "10.0" + "\t" + "81.0" + "\n"
            + "onson" + "\t" + "Madison" + "\t" + "Thompson" + "\t" + "Adams" 
            + "\t" + "2017" + "\t" + "9.0" + "\t" + "89.0" + "\n"
            + "thms" + "\t" + "Ethan" + "\t" + "Williams" + "\t" + "Baker" 
            + "\t" + "2016" + "\t" + "8.0" + "\t" + "89.0" + "\n"
            + "vaern" + "\t" + "Ava" + "\t" +  "Hernandez" + "\t" + "Nelson" 
            + "\t" + "2014" + "\t" + "6.0" + "\t" + "91.0" + "\n"
            + "ydenavi" + "\t" + "Jayden" + "\t" + "Davis" + "\t" + "Green" 
            + "\t" + "2015" + "\t" + "10.0" + "\t" + "97.0" + "\n";
    
    private String addStudents = "STUDENT" + "\n" + "iaartinez" + "\n"
            + "Sophia" + "\n" + "Martinez" + "\n" + "Scott" + "\n" + "2014" 
            + "\n" + "STUDENT" + "\n" + "illines" + "\n" + "William" + "\n"
            + "Jones" + "\n" + "Nelson" + "\n" + "2014" + "\n" + "STUDENT"
            + "\n" + "xaod" + "\n" + "Alexander" + "\n" + "Rodriguez" + "\n"
            + "Adams" + "\n" + "2017";
    
    private String addAssignments = "ASSIGNMENT" + "\n" + "First Group Project"
            + "\n" + "150.0" + "\n" + "10.0" + "\n" + "ASSIGNMENT" + "\n" +
            "Test" + "\n" + "100.0" + "\n" + "25.0";
    
    private String gradesForStudent = "GRADES_FOR_STUDENT" + "\n" + "iaartinez"
            + "\n" + "Opening Assignment" + "\n" + "6" + "\n" + "A2" + "\n"
            + "51";
    
    private String gradesForAssignment = "GRADES_FOR_ASSIGNMENT" + "\n"
            + "First Group Project"  + "\n" + "abetaylor" + "\n" + "82" + "\n"
            + "abethes" + "\n" + "92" + "\n" + "acit" + "\n" + "122" + "\n"
            + "ahrown" + "\n" + "146" + "\n" + "amller" + "\n" + "100" + "\n"
            + "are" + "\n" + "99" + "\n" + "enwilson" + "\n" + "123" + "\n"
            + "gailarti" + "\n" + "132"  + "\n" + "iaartinez" + "\n" + "79"
            + "\n" + "illines" + "\n" + "128" + "\n" + "marson" + "\n"
            + "136" + "\n" + "michaeia" + "\n" + "121" + "\n" + "mijacks" 
            + "\n" + "93" + "\n" + "oliviaas" + "\n" + "78" + "\n" + "onon" 
            + "\n" + "136" + "\n" + "onson" + "\n" + "133" + "\n" + "thms" 
            + "\n" + "111" + "\n" + "vaern" + "\n" + "137" + "\n" + "xaod" 
            + "\n" + "93" + "\n" + "ydenavi" + "\n" + "134";
    
    private String studentEmptyGradebook = "STUDENT_GRADES" + "\n"
            + "joberste" + "\n" + "Jesse" + "\n" + "Oberstein" + "\n"
            + "Mazor" + "\n" + "2017" + "\n" + "----" + "\n" + "HW1" + "\t" 
            + "10.0" + "\n" + "Test1" + "\t" + "90.0" + "\n" + "----" + "\n"
            + "CURRENT GRADE" + "\t" + "91.6666666666666";
    
    private String studentGradebook = "STUDENT_GRADES" + "\n"
            + "iaartinez" + "\n" + "Sophia" + "\n" + "Martinez" + "\n"
            + "Scott" + "\n" + "2014" + "\n" + "----" + "\n"
            + "Opening Assignment" + "\t" + "6.0" + "\n" + "A2" + "\t" + "51.0"
            + "\n" + "First Group Project" + "\t" + "79.0" + "\n" + "Test"
            + "\t" + "50.0" + "\n" + "----" + "\n" + "CURRENT GRADE" + "\t" 
            + "51.0162601626016";
    
    private String assignmentGradebook = "ASSIGNMENT_GRADES" + "\n" 
            + "Test" + "\n" 
            + "100.0" + "\n" 
            + "25.0" + "\n" 
            + "----" + "\n" 
            + "abetaylor" + "\t" + "65.0" + "\n" 
            + "abethes" + "\t" + "88.0" + "\n" 
            + "acit" + "\t" + "85.0" + "\n"
            + "ahrown" + "\t" + "57.0" + "\n" 
            + "amller" + "\t" + "82.0" + "\n" 
            + "are" + "\t" + "68.0" + "\n" 
            + "enwilson" + "\t" + "97.0" + "\n" 
            + "gailarti" + "\t" + "73.0" + "\n"
            + "iaartinez" + "\t" + "50.0" + "\n" 
            + "illines" + "\t" + "79.0" + "\n" 
            + "marson" + "\t" + "54.0" + "\n" 
            + "michaeia" + "\t" + "58.0" + "\n" 
            + "mijacks" + "\t" + "83.0" + "\n" 
            + "oliviaas" + "\t" + "61.0" + "\n" 
            + "onon" + "\t" + "51.0" + "\n" 
            + "onson" + "\t" + "71.0" + "\n" 
            + "thms" + "\t" + "92.0" + "\n" 
            + "vaern" + "\t" + "83.0" + "\n" 
            + "xaod" + "\t" + "91.0" + "\n" 
            + "ydenavi" + "\t" + "84.0" + "\n" 
            + "----" + "\n" 
            + "STATS" + "\n" 
            + "Average" + "\t" + "73.6" + "\n" 
            + "Median" + "\t" + "76.0" + "\n"
            + "Max" + "\t" + "97.0" + "\n" 
            + "Min" + "\t" + "50.0" + "\n";
    
    private String assignmentEmptyGradebook = "ASSIGNMENT_GRADES" + "\n" 
            + "Test1" + "\n" 
            + "100.0" + "\n" 
            + "10.0" + "\n"
            + "----" + "\n"
            + "joberste" + "\t" + "90.0" + "\n"
            + "klhutch" + "\t" + "75.0" + "\n"
            + "nmg149" + "\t" + "80.0" + "\n" 
            + "----" + "\n" 
            + "STATS" + "\n" 
            + "Average" + "\t" + "81.66666666666667" + "\n" 
            + "Median" + "\t" + "80.0" + "\n"
            + "Max" + "\t" + "90.0" + "\n" 
            + "Min" + "\t" + "75.0" + "\n";

    private String currentGradebook = "CURRENT_GRADES" + "\n" +
            "abetaylor" + "\t" + "63.5772357723577" + "\n" +
            "abethes" + "\t" + "81.0569105691056" + "\n" +
            "acit" + "\t" + "83.2520325203252" + "\n" +
            "ahrown" + "\t" + "70.8130081300813" + "\n" +
            "amller" + "\t" + "76.5040650406504" + "\n" +
            "are" + "\t" + "66.8292682926829" + "\n" +
            "enwilson" + "\t" + "91.2195121951219" + "\n" +
            "gailarti" + "\t" + "77.3170731707317" + "\n" +
            "iaartinez" + "\t" + "51.0162601626016" + "\n" +
            "illines" + "\t" + "77.520325203252" + "\n" +
            "marson" + "\t" + "66.6260162601626" + "\n" +
            "michaeia" + "\t" + "68.4552845528455" + "\n" +
            "mijacks" + "\t" + "73.0487804878048" + "\n" +
            "oliviaas" + "\t" + "62.8048780487804" + "\n" +
            "onon"  + "\t" + "65.5284552845528" + "\n" +
            "onson" + "\t" + "77.9674796747967" + "\n" +
            "thms" + "\t" + "86.9512195121951" + "\n" +
            "vaern" + "\t" + "85.4471544715447" + "\n" +
            "xaod" + "\t" + "84.0243902439024" + "\n" +
            "ydenavi" + "\t" + "87.2764227642276" + "\n";
    
    private String currentShortGradebook = "CURRENT_GRADES" + "\n"
            + "joberste" + "\t" + "91.6666666666666" + "\n"
            + "klhutch" + "\t" + "74.1666666666666" + "\n"
            + "nmg149" + "\t" + "71.6666666666666" + "\n";
    

    // Example Gradebooks
    private MyGradeBook emptyGradebook = MyGradeBook.initialize();
    private MyGradeBook gradebookFile = 
            MyGradeBook.initializeWithFile("gradebook.txt");
    private MyGradeBook initialFile = 
            MyGradeBook.initializeWithFile("initial.txt");
    private MyGradeBook initialString = 
            MyGradeBook.initializeWithString(initialtxt);
    private MyGradeBook gradebookString = 
            MyGradeBook.initializeWithString(gradebooktxt);
    private MyGradeBook shortGradebookString = 
            MyGradeBook.initializeWithString(shortGradebook);
    
    // Example Students
    private Student jesseOberstein = new Student("joberste", "Jesse", 
            "Oberstein", "Mazor", 2017);
    private Student kateHutchinson = new Student("klhutch", "Kate", 
            "Hutchinson", "Hughes", 2016);
    private Student nathanGoodman = new Student("nmg149", "Nathan", 
            "Goodman", "Hughes", 2017);
    
    // Example Assignments
    private Assignment hw1 = new Assignment("HW1", 10.0, 2.0);
    private Assignment test1 = new Assignment("Test1", 100.0, 10.0);
    
    // Example HashMap
    private HashMap<String, Double> hmgrades;
    
    
    
    ////////////////////////////////////////////////////////////////////
    ///////     JUnit Test Cases for MyGradeBook        ////////////////
    ////////////////////////////////////////////////////////////////////
    
    /**
     * Resets all the gradebooks to their example state.
     */
    public void resetGradebooks() {
        emptyGradebook = MyGradeBook.initialize();
        gradebookFile = MyGradeBook.initializeWithFile("gradebook.txt");
        gradebookString = MyGradeBook.initializeWithString(gradebooktxt);
        initialFile = MyGradeBook.initializeWithFile("initial.txt");
        initialString = MyGradeBook.initializeWithString(initialtxt);
        shortGradebookString = MyGradeBook.initializeWithString(
                shortGradebook);
    }
    
    /**
     * tests the initialization of a MyGradeBook
     */
    @Test
    public void testInitialize() {
        // Initializes an empty gradebook.
        assertEquals(MyGradeBook.initialize(), emptyGradebook);
        
        // Initialize gradebook with a File.
        assertEquals(MyGradeBook.initializeWithFile("gradebook.txt"), 
                gradebookFile);
        assertEquals(MyGradeBook.initializeWithFile("initial.txt"), 
                initialFile);
        
        // Initialize gradebook with a String.
        assertEquals(MyGradeBook.initializeWithString(gradebooktxt), 
                gradebookString);
        assertEquals(MyGradeBook.initializeWithString(initialtxt), 
                initialString);
        assertEquals(MyGradeBook.initializeWithString(shortGradebook), 
                shortGradebookString);
    }
    
    /**
     * Adds some examples to an empty gradebook.
     */
    public void addSamplesToGB() {
        // Add assignments to the empty gradebook.
        emptyGradebook.addAssignment("HW1", 10.0, 2.0);
        emptyGradebook.addAssignment("Test1", 100.0, 10.0);

        // Add students to the empty gradebook.
        emptyGradebook.addStudent("joberste", "Jesse",
                "Oberstein", "Mazor", 2017);
        emptyGradebook.addStudent("klhutch", "Kate", "Hutchinson",
                "Hughes", 2016);
        emptyGradebook.addStudent("nmg149", "Nathan", "Goodman",
                "Hughes", 2017);
        
        // Add assignment grades to the empty gradebook.
        assertTrue(emptyGradebook.changeGrade("HW1", "joberste", 10.0));
        emptyGradebook.changeGrade("HW1", "klhutch", 7.0);
        emptyGradebook.changeGrade("HW1", "nmg149", 3.0);
        emptyGradebook.changeGrade("Test1", "joberste", 90.0);
        emptyGradebook.changeGrade("Test1", "klhutch", 75.0);
        emptyGradebook.changeGrade("Test1", "nmg149", 80.0);
        
        hw1.changeGrade("joberste", 10.0);
        hw1.changeGrade("klhutch", 7.0);
        hw1.changeGrade("nmg149", 3.0);
        
        test1.changeGrade("joberste", 90.0);
        test1.changeGrade("klhutch", 75.0);
        test1.changeGrade("nmg149", 80.0);

        hmgrades = new HashMap<String, Double>();
    }
    
    /**
     * tests the manual addition of assignments
     */
    @Test
    public void testAddAssignment() {
        this.addSamplesToGB();
        
        assertTrue(emptyGradebook.hasAssignment(
                emptyGradebook.getAssignment("HW1")));
        assertTrue(emptyGradebook.hasAssignment(
                emptyGradebook.getAssignment("Test1")));
        
        Assignment hw1copy = emptyGradebook.getAssignment("HW1");
        
        assertFalse(hw1copy.equals(test1));
        assertTrue(emptyGradebook.hasAssignment(hw1));
    }
    
    /**
     * tests the manual addition of students
     */
    @Test
    public void testAddStudent() {
        this.addSamplesToGB();
        assertTrue(emptyGradebook.students.contains(jesseOberstein));
        assertTrue(emptyGradebook.students.contains(kateHutchinson));
        assertTrue(emptyGradebook.students.contains(nathanGoodman));
    }
    
    /**
     * tests the processFile method in MyGradeBook
     * @throws FileNotFoundException 
     */
    @Test
    public void testProcessFile() throws FileNotFoundException {
        this.addSamplesToGB();
        emptyGradebook.addAssignment("Test1", 100.0, 10.0);
        
        assertEquals(emptyGradebook.getAssignment("Test1"), test1);
        assertEquals(emptyGradebook.getAssignment("HW1"), hw1);
        
        this.resetGradebooks();
        emptyGradebook.processFile("addStudents.txt");
        emptyGradebook.processFile("addAssignments.txt");
        emptyGradebook.processFile("gradesForAssignment3.txt");
        emptyGradebook.processFile("gradesForStudent.txt");
        assertEquals(emptyGradebook.getStudent("iaartinez"),
                new Student("iaartinez", "Sophia", "Martinez", "Scott", 2014));
        assertEquals(emptyGradebook.getStudent("illines"),
                new Student("illines", "William", "Jones", "Nelson", 2014));
        assertEquals(emptyGradebook.getStudent("xaod"),
                new Student("xaod", "Alexander", "Rodriguez", "Adams", 2017));
    }
    
    /**
     * tests the processString method in MyGradeBook
     */
    @Test
    public void testProcessString() {
        this.addSamplesToGB();
        emptyGradebook.processString(addStudents);
        emptyGradebook.processString(addAssignments);
        emptyGradebook.processString(gradesForStudent);
        emptyGradebook.processString(gradesForAssignment);
        assertTrue(emptyGradebook.hasAssignment(emptyGradebook.getAssignment(
                "Test")));
        assertTrue(emptyGradebook.hasAssignment(emptyGradebook.getAssignment(
                "First Group Project")));
        assertTrue(emptyGradebook.hasStudent(emptyGradebook.getStudent(
                "iaartinez")));
        assertTrue(emptyGradebook.hasStudent(emptyGradebook.getStudent(
                "illines")));
        assertTrue(emptyGradebook.hasStudent(emptyGradebook.getStudent(
                "xaod")));
        
        initialFile.processString(addStudents);
        initialFile.processString(gradesForStudent);
        assertEquals(new Double(initialFile.assignmentGrade(
                "Opening Assignment", "iaartinez")), new Double(6.0));
    }
    
    /**
     * tests the assignment grade method in MyGradeBook
     */
    @Test
    public void assignmentGrade() {
        assertEquals(new Double(gradebookFile.assignmentGrade("A2", 
                "gailarti")), new Double(79));
        assertEquals(new Double(gradebookString.assignmentGrade("A2", 
                "gailarti")), new Double(79));
        assertEquals(new Double(initialFile.assignmentGrade(
                "Opening Assignment", "are")), new Double(9.0));
        assertEquals(new Double(initialString.assignmentGrade(
                "Opening Assignment", "are")), new Double(9.0));
        assertEquals(new Double(shortGradebookString.assignmentGrade("Test1",
                "nmg149")), new Double(85.0));
    }
    
    /**
     * tests the changeGrade method of MyGradeBook
     */
    @Test
    public void testChangeGrade() {
        assertTrue(gradebookFile.changeGrade("First Group Project", 
                "abetaylor", 95));
        assertEquals(new Double(gradebookFile.assignmentGrade(
                "First Group Project", "abetaylor")), new Double(95));
        assertFalse(gradebookFile.changeGrade("Blargh", "abetaylor", 100));
        assertFalse(gradebookFile.changeGrade("Blargh", "natalia42", -4));
        assertFalse(gradebookFile.changeGrade("First Group Project",
                "natalia42", 100));
    }
    
    /**
     * tests the average method in MyGradeBook
     */
    @Test
    public void testAverage() {
        this.addSamplesToGB();
        assertEquals(new Double(emptyGradebook.getAssignment("HW1").average()), 
                new Double(6.666666666666667));
        assertEquals(new Double(hw1.average()), new Double(6.666666666666667));
        assertEquals(new Double(test1.average()), 
                new Double(81.66666666666667));
        assertEquals(new Double(emptyGradebook.getAssignment(
                "Test1").average()), new Double(81.66666666666667));
    }
    
    /**
     * tests the median method in MyGradeBook
     */
    @Test
    public void testMedian() {
        this.addSamplesToGB();
        assertEquals(new Double(emptyGradebook.getAssignment("HW1").median()),
                new Double(7.0));
        assertEquals(new Double(hw1.median()), new Double(7.0));
        assertEquals(new Double(test1.median()), new Double(80.0));
        assertEquals(new Double(emptyGradebook.getAssignment(
                "Test1").median()), new Double(80.0));
    }
    
    /**
     * tests the min function in MyGradeBook
     */
    @Test
    public void testMin() {
        this.addSamplesToGB();
        assertEquals(new Double(emptyGradebook.getAssignment("HW1").min()),
                new Double(3.0));
        assertEquals(new Double(hw1.min()), new Double(3.0));
        assertEquals(new Double(test1.min()), new Double(75.0));
        assertEquals(new Double(emptyGradebook.getAssignment("Test1").min()), 
                new Double(75.0));
    }
    
    /**
     * tests the max function in MyGradeBook
     */
    @Test
    public void testMax() {
        this.addSamplesToGB();
        assertEquals(new Double(emptyGradebook.getAssignment("HW1").max()),
                new Double(10.0));
        assertEquals(new Double(hw1.max()), new Double(10.0));
        assertEquals(new Double(test1.max()), new Double(90.0));
        assertEquals(new Double(emptyGradebook.getAssignment("Test1").max()),
                new Double(90.0));
    }
    
    /**
     * tests the currentGrade method in MyGradeBook
     */
    @Test
    public void testCurrentGrade() {
        this.addSamplesToGB();
        //System.out.println(emptyGradebook.currentGrade("joberste"));
        assertEquals(new Double(emptyGradebook.currentGrade("joberste")),
                new Double(91.6666666666666));
        assertEquals(new Double(emptyGradebook.currentGrade("klhutch")),
                new Double(74.1666666666666));
        assertEquals(new Double(emptyGradebook.currentGrade("nmg149")),
                new Double(71.6666666666666));
        assertEquals(new Double(gradebookFile.currentGrade("abetaylor")),
                new Double(63.5772357723577));
        assertEquals(new Double(gradebookString.currentGrade("michaeia")),
                new Double(68.4552845528455));
    }
    
    /**
     * Creates the grades for the gradebook.txt file/string.
     */
    public void createGradebookGrades() {
        hmgrades.put("abetaylor", 63.5772357723577);
        hmgrades.put("abethes", 81.0569105691056);
        hmgrades.put("acit", 83.2520325203252);
        hmgrades.put("ahrown", 70.8130081300813);
        hmgrades.put("amller", 76.5040650406504);
        hmgrades.put("are", 66.8292682926829);
        hmgrades.put("enwilson", 91.2195121951219);
        hmgrades.put("gailarti", 77.3170731707317);
        hmgrades.put("iaartinez", 51.0162601626016);
        hmgrades.put("illines", 77.520325203252);
        hmgrades.put("marson", 66.6260162601626);
        hmgrades.put("michaeia", 68.4552845528455);
        hmgrades.put("mijacks", 73.0487804878048);
        hmgrades.put("oliviaas", 62.8048780487804);
        hmgrades.put("onon", 65.5284552845527);
        hmgrades.put("onson", 77.9674796747967);
        hmgrades.put("thms", 86.9512195121951);
        hmgrades.put("vaern", 85.4471544715447);
        hmgrades.put("xaod", 84.0243902439024);
        hmgrades.put("ydenavi", 87.2764227642276);
    }
    
    /**
     * tests the currentGrades method in MyGradeBook
     */
    @Test
    public void testCurrentGrades() {
        this.addSamplesToGB();
        hmgrades.put("joberste", 91.6666666666666);
        hmgrades.put("klhutch", 74.1666666666666);
        hmgrades.put("nmg149", 71.6666666666666);
        assertEquals(emptyGradebook.currentGrades(), hmgrades);
        
        this.addSamplesToGB();
        this.createGradebookGrades();
        //assertEquals(gradebookString.currentGrades(), hmgrades);
        //assertEquals(gradebookFile.currentGrades(), hmgrades);
    }
    
    /**
     * tests the outputCurrentGrades method in MyGradeBook
     */
    @Test
    public void testOutputCurrentGrades() {
        this.addSamplesToGB();
        assertEquals(emptyGradebook.outputCurrentGrades(), 
                currentShortGradebook);
        assertEquals(gradebookFile.outputCurrentGrades(),
                currentGradebook);
        assertEquals(gradebookString.outputCurrentGrades(), 
                currentGradebook);
    }
    
    /**
     * tests the outputStudentGrades method in MyGradeBook
     */
    @Test
    public void testOutputStudentGrades() {
        this.addSamplesToGB();

        hmgrades.put("joberste", 91.6666666666666);
        hmgrades.put("klhutch", 74.1666666666666);
        hmgrades.put("nmg149", 71.6666666666666);
        
        assertEquals(emptyGradebook.outputStudentGrades("joberste"), 
                studentEmptyGradebook);
        
        this.createGradebookGrades();
        assertEquals(gradebookFile.outputStudentGrades("iaartinez"),
                studentGradebook);
        assertEquals(gradebookString.outputStudentGrades("iaartinez"),
                studentGradebook);
    }
    
    /**
     * tests the outputAssignmentGrades method in MyGradeBook
     */
    @Test
    public void testOutputAssignmentGrades() {
        this.addSamplesToGB();
        
        hmgrades.put("joberste", 91.6666666666666);
        hmgrades.put("klhutch", 74.1666666666666);
        hmgrades.put("nmg149", 71.6666666666666);
        
        assertEquals(emptyGradebook.outputAssignmentGrades("Test1"), 
                assignmentEmptyGradebook);
        assertEquals(gradebookString.outputAssignmentGrades("Test"),
                assignmentGradebook);
        assertEquals(gradebookFile.outputAssignmentGrades("Test"),
                assignmentGradebook);
    }
    
    /**
     * tests the outputGradebook method in MyGradeBook
     */
    @Test
    public void testOutputGradebook() {
        assertEquals(gradebookFile.outputGradebook(), gradebooktxt);
        assertEquals(gradebookString.outputGradebook(), gradebooktxt);
        assertEquals(initialFile.outputGradebook(), initialtxtOut);
        assertEquals(shortGradebookString.outputGradebook(), 
                shortGradebookOut);
    }
}
