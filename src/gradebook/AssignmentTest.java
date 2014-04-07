package gradebook;
import static org.junit.Assert.*;
import org.junit.Test;

public class AssignmentTest {
    Quiz quiz1 = new Quiz("Quiz 1", 5, 200.0);
    Quiz fakeQuiz = new Quiz("Homework 1", 1, 10.0);
    Homework hw1 = new Homework("Homework 1", 1, 10.0);
    Homework hw2test = new Homework("Homework 1", 1, 10.0);


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
