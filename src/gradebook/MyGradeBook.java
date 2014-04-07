package gradebook;

import java.util.ArrayList;
import java.util.HashMap;

/**Class MyGradebook
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
public class MyGradeBook {
    //TODO add setters for these?
    
    //FIELDS
    
    //basic information about the gradebook
    String courseName = "";
    String couresNumber = "";
    String teacherName = "";
    String teacherId = "";
    
    //information about the gradebook that will and can change
    ArrayList<Student> students;
    ArrayList<Assignment> assignments;
    
    
    /** Constructor MyGradeBook
     * returns a basic gradebook with all fields empty
     */
    public MyGradeBook() {
        this.students = new ArrayList<Student>();
        this.assignments = new ArrayList<Assignment>();
    }
    

    /** method addStudent
     * used to add a Student object to a gradebook
     * 
     * @param newStudent - the Student object to be added
     */
    void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }
    
    /** method addAssignment
     * used to add an Assignment object to a gradebook
     * 
     * @param newAssignment - the Assignment object ot be added
     */
    void addAssignment(Assignment newAssignment) {
        this.assignments.add(newAssignment);
    }
    
    /**
     * Factory method to construct an empty MyGradebook
     * 
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        //TODO write initialize [empty]
        return new MyGradeBook();
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from filename
     * 
     * @param filename
     *            the filename for the file that contains the initial grade
     *            book, which is formatted like initial.txt
     * @return a MyGradebook that contains the grade book from filename
     */
    public static MyGradeBook initializeWithFile(String filename) {
        //TODO write initializeWithFile
        return null;
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from startingString
     * 
     * @param startingString
     *            String that contains the initial grade book, which is
     *            formatted like initial.txt
     * @return a MyGradebook that contains the grade book from startingString
     */
    public static MyGradeBook initializeWithString(String startingString) {
        //TODO write initializeWIthString
        return null;
    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing filename
     * 
     * @param filename
     *            the filename for a file that contains information that will be
     *            added to the grade book. The file could contain several
     *            different types of information---new assignments, new
     *            students, new grades. The file will be formatted like
     *            addAssignments.txt, addStudents.txt, gradesForAssignment1.txt,
     *            and gradesForStudent.txt.
     */
    public void processFile(String filename) {
        //TODO write processFile
    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing additionalString
     * 
     * @param additionalString
     *            String that contains information that will be added to the
     *            grade book. The String could contain several different types
     *            of information---new assignments, new students, new grades.
     *            The String will be formatted like addAssignments.txt,
     *            addStudents.txt, gradesForAssignment1.txt, and
     *            gradesForStudent.txt.
     */
    public void processString(String additionalString) {
        //TODO write processString
    }

    /**
     * Changes the assignment (named assignmentName) grade for student (whose
     * username is equal to username) to newGrade
     * 
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @param newGrade
     *            the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *         assignment/student combination exists, returns false otherwise
     */
    public boolean changeGrade(String assignmentName, String username, 
            double newGrade) {
        //TODO write changeGrade
        return false;
    }

    /**
     * Calculates the average across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the average across all students for assignmentName
     */
    public double average(String assignmentName) {
        //TODO write average
        //i think we said we'd have this in assignment
        // so this could just be a get-assignment then ____.average()
        return 0;
    }

    /**
     * Calculates the median across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the median across all students for assignmentName
     */
    public double median(String assignmentName) {
        //TODO write median
        // see average for how this oculd work
        return 0;
    }

    /**
     * Calculates the min across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the min across all students for assignmentName
     */
    public double min(String assignmentName) {
        //TODO write min
        // see average for how this could work
        return 0;
    }

    /**
     * Calculates the max across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the max across all students for assignmentName
     */
    public double max(String assignmentName) {
        //TODO write max
        // see average for how this could work
        return 0;
    }

    /**
     * Calculates the current grade for the given student
     * 
     * @param username
     *            username for the student
     * @return the current grade for student with username. The current grade is
     *         calculated based on the current assignment grades, assignment
     *         total points, assignment percent of semester. The current grade
     *         for a student is the sum of the relative assignment grades
     *         divided by the current percent of semester time 100. Since all
     *         grades may not currently be entered, we have to divide by the
     *         current percent. The relative assignment grade is the student's
     *         assignment grade divide by total point value for the assignment
     *         times the percent of semester.
     */
    public double currentGrade(String username) {
        //TODO write currentGrade
        return 0;
    }

    /**
     * Calculates the current grade for all students
     * 
     * @return HashMap of the current grades for all students. The key of the
     *         HashMap is the username of the student. The value is the current
     *         grade for the associated student. The current grade is calculated
     *         based on the current assignment grades, assignment total points,
     *         assignment percent of semester. The current grade for a student
     *         is the sum of the relative assignment grades divided by the
     *         current percent of semester time 100. Since all grades may not
     *         currently be entered, we have to divide by the current percent.
     *         The relative assignment grade is the student's assignment grade
     *         divide by total point value for the assignment times the percent
     *         of semester.
     */
    public HashMap<String, Double> currentGrades() {
        //TODO write currentGrades
        return null;
    }

    /**
     * Provides the grade earned by the given student for the given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @return the grade earned by username for assignmentName
     */
    public double assignmentGrade(String assignmentName, String username) {
        //TODO write assignmentGrade
        return 0;
    }

    /**
     * Provide a String that contains the current grades of all students in the
     * course
     * 
     * @return a String that contains the current grades of all students in the
     *         course. The String should be formatted like
     *         currentGrades.txt---CURRENT_GRADES heading and each row: username
     *         followed by tab and current grade. The usernames will be listed
     *         alphabetically.
     */
    public String outputCurrentGrades() {
        //TODO write outputCurrentGrades
        return "";
    }

    /**
     * Provide a String that contains the current grades of the given student
     * 
     * @param username
     *            username for student
     * @return a String that contains the current grades of username. The String
     *         should be formatted like studentGrades.txt---STUDENT_GRADES
     *         heading, student info, dividers, each assignment (assignment name
     *         followed by tab and assignment grade), and current grade.
     *         Assignments are to remain in the same order as given.
     */
    public String outputStudentGrades(String username) {
        //TODO write outputStudentGrades
        return "";
    }

    /**
     * Provide a String that contains the assignment grades of all students in
     * the course for the given assignment
     * 
     * @param assignName
     *            name of the assignment
     * @return a String that contains the assignment grades of all students in
     *         the course for assignName. The String should be formatted like
     *         assignmentGrade.txt---ASSIGNMENT_GRADES heading, assignment info,
     *         dividers, each student (username followed by tab and assignment
     *         grade), and assignment stats. The usernames will be listed
     *         alphabetically while assignments are to remain in the same 
     *         order as given.
     */
    public String outputAssignmentGrades(String assignName) {
        //TODO write outputAssignmentGrades
        return "";
    }

    /**
     * Provide a String that contains the current grade book. This String could
     * be used to initialize a new grade book.
     * 
     * @return a String that contains the current grade book. This String could
     *         be used to initialize a new grade book. The String should be
     *         formatted like gradebook.txt. The usernames will be listed
     *         alphabetically.
     */
    public String outputGradebook() {
        //TODO write outputGradeBook
        return "";
    }
    
}