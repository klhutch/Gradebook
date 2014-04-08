package gradebook;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class AssignmentTest {
    Assignment quiz1 = new Assignment("Quiz 1", 200, 5);
    Assignment fakeQuiz = new Assignment("Homework 1", 10, 1);
    Assignment hw1 = new Assignment("Homework 1", 10, 1);
    Assignment hw2test = new Assignment("Homework 1", 10, 1);
    MyGradeBook gradebook = new MyGradeBook();

    public void createAssignmentsGrades() {
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
    
    @Test
    public void testInitialize() {
        //try {
            //System.out.println(MyGradeBook.initializeWithFile("initial.txt").outputGradebook());
        //}
        //catch (FileNotFoundException e) {
          //  e.printStackTrace();
        //}
    }
    
    @Test
    public void testStats() throws FileNotFoundException {
        System.out.println(MyGradeBook.initializeWithFile("gradebook.txt").average("Test"));
        MyGradeBook.initializeWithFile("gradebook.txt").average("Test");
        System.out.println(MyGradeBook.initializeWithFile("gradebook.txt").outputAssignmentGrades("Test"));
        System.out.println(MyGradeBook.initializeWithFile("gradebook.txt").currentGrade("abetaylor"));
        System.out.println(MyGradeBook.initializeWithFile("gradebook.txt").currentGrades());
    }
}

/*
// Alternate Equals Method
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (!(obj.getClass().equals(this.getClass()))) {
        return false;
    }
    Assignment temp = (Assignment) obj;
    return this.name.equals(temp.name)
            && this.weight == temp.weight
            && this.grades.equals(temp.grades)
            && this.totalPoints.equals(temp.totalPoints);
}*/