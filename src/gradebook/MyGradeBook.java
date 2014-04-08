package gradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
    /** The name of the course that this gradebook hold students for. */
    String courseName = "";
    /** The CRN for the course that this gradebook hold students for. */
    String courseNumber = "";
    /** The teacher's name for the course for this gradebook. */
    String teacherName = "";
    /** The teacher's username for the course for this gradebook. */
    String teacherId = "";
    
    /** A list of students for this gradebook. */
    ArrayList<Student> students;
    /** A list of assignments for this gradebook. */
    ArrayList<Assignment> assignments;
    
    
    /** Constructor MyGradeBook
     * returns a basic gradebook with all fields empty
     */
    public MyGradeBook() {
        this.students = new ArrayList<Student>();
        this.assignments = new ArrayList<Assignment>();
    }
    
    /**
     * Factory method to construct an empty MyGradebook
     * 
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        return new MyGradeBook();
    }
    
    /**
     * Converts a given file's contents into a string.
     * 
     * @param filename
     *            The name of the given file.
     * @return String The formatted string of the given file's contents.
     * @throws FileNotFoundException
     */
    private String convertFileToString(String filename) {
        Scanner filescan;
        String startingString = "";
        try {
            filescan = new Scanner(new File(filename));
            while (filescan.hasNextLine()) {
                startingString += filescan.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(
                    "InitializeWithFile: The file entered cannot be found.");
        }
        return startingString;
    }
    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from filename
     * 
     * @param filename
     *            the filename for the file that contains the initial grade
     *            book, which is formatted like initial.txt
     * @return a MyGradebook that contains the grade book from filename
     * @throws FileNotFoundException 
     */
    public static MyGradeBook initializeWithFile(String filename) {
        MyGradeBook mygb = MyGradeBook.initialize();
        return MyGradeBook.initializeWithString(mygb.convertFileToString(filename));
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
        Scanner scan = new Scanner(startingString);
        MyGradeBook mygb = MyGradeBook.initialize();
        scan.nextLine();
        Scanner scanNames = new Scanner(scan.nextLine()).useDelimiter("\t");
        scanNames.skip("\t" + "\t" + "\t" + "\t" + "\t");
        Scanner scanTotals = new Scanner(scan.nextLine());
        Scanner scanWeights = new Scanner(scan.nextLine());
        while (scanTotals.hasNext()) {
            Assignment newAssignment = 
                    new Assignment(
                            scanNames.next(), 
                            scanTotals.nextDouble(), 
                            scanWeights.nextDouble());
            mygb.assignments.add(newAssignment);
        }
        while (scan.hasNextLine()) {
            Scanner scanStudent = new Scanner(scan.nextLine());
            scanStudent.useDelimiter("\t"); 
            while (scanStudent.hasNext()) {
                String susername = scanStudent.next();
                Student newStudent = 
                        new Student(
                                susername,
                                scanStudent.next(),
                                scanStudent.next(),
                                scanStudent.next(), 
                                scanStudent.nextInt());
                mygb.students.add(newStudent);
                for (int i = 0; i < mygb.assignments.size(); i++) {
                    mygb.assignments.get(i).addAssignmentGrade(susername,
                           scanStudent.nextDouble());
                }
            }
        }
        return mygb;
    }

    
    /**
     * Gets an assignment that has the same name as the given one from this
     * gradebook's list of assignments.
     * 
     * @param aname
     *            The name of the assignment in question.
     * @return Assignment A way to represent an assignment that contains
     *         student's grades.
     */
    Assignment getAssignment(String aname) {
        for (int i = 0; i < this.assignments.size(); i++) {
            if (this.assignments.get(i).getAssignmentName().equals(aname)) {
                return this.assignments.get(i);
            }
        }
        throw new RuntimeException("Assignment not found");
    }
    
    
    /**
     * Gets a student that has the same name as the given one from this
     * gradebook's list of students.
     * 
     * @param sname
     *            The username of the student in question.
     * @return Student A way to represent a student that contains a student's
     *         basic information, including username.
     */
    Student getStudent(String sname) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getStudentUsername().equals(sname)) {
                return this.students.get(i);
            }
        }
        throw new RuntimeException("Student not found");
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
     * @throws FileNotFoundException 
     */
    public void processFile(String filename) throws FileNotFoundException {
        this.processString(this.convertFileToString(filename));
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
        Scanner scan = new Scanner(additionalString);
        while (scan.hasNextLine()) {
            if (scan.next().equals("ASSIGNMENT")) {
                Assignment a1 = new Assignment(scan.next(), 
                        scan.nextDouble(), scan.nextDouble());
                this.assignments.add(a1);
            }
            if (scan.nextLine().equals("STUDENT")) {
                Student s1 = new Student(scan.next(), scan.next(), scan.next(),
                        scan.next(), scan.nextInt());
                this.students.add(s1);
            }
            if (scan.nextLine().equals("GRADES_FOR_ASSIGNMENT")) {
                String a1name = scan.nextLine();
                Assignment a1 = this.getAssignment(a1name);
                a1.addAssignmentGrade(scan.next(), scan.nextDouble());
            }
            if (scan.nextLine().equals("GRADES_FOR_STUDENT")) {
                String s1name = scan.nextLine();
                Student s1 = this.getStudent(s1name);
                s1.addStudentGrade(this.getAssignment(scan.next()), 
                        scan.nextDouble());
            }
        }
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
        return this.getAssignment(assignmentName).changeGrade(username, 
                newGrade);
    }

    /**
     * Calculates the average across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the average across all students for assignmentName
     */
    public double average(String assignmentName) {
        return this.getAssignment(assignmentName).average();
    }

    /**
     * Calculates the median across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the median across all students for assignmentName
     */
    public double median(String assignmentName) {
        return this.getAssignment(assignmentName).median();
    }

    /**
     * Calculates the min across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the min across all students for assignmentName
     */
    public double min(String assignmentName) {
        return this.getAssignment(assignmentName).min();
    }

    /**
     * Calculates the max across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the max across all students for assignmentName
     */
    public double max(String assignmentName) {
        return this.getAssignment(assignmentName).max();
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
        int allWeights = 0;
        double assignGrades = 0;
        for (int i = 0; i < this.assignments.size(); i++) {
            double grade = this.assignments.get(i).assignmentGrade(username);
            double weight = this.assignments.get(i).getWeight();
            double total = this.assignments.get(i).getTotal();
            assignGrades += (weight * (grade / total));
            allWeights += weight;
        }
        return (assignGrades / allWeights) * 100;
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
        HashMap<String, Double> currents = new HashMap<String, Double>();
        for (int i = 0; i < this.students.size(); i++) {
            String username = this.students.get(i).getStudentUsername();
            currents.put(username, this.currentGrade(username));
        }
        return currents;
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
        return this.getAssignment(assignmentName).assignmentGrade(username);
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
        String formattedList = "CURRENT_GRADES" + "\n";
        Set<String> usernameSet = this.currentGrades().keySet();
        List<String> usernames = new ArrayList<String>();
        for (String user : usernameSet) {
            usernames.add(user);
        }
        Collections.sort(usernames);
        for (int i = 0; i < this.currentGrades().size(); i++) {
            String user = usernames.get(i);
            formattedList += user + "\t" 
                    + this.currentGrades().get(user) + "\n";
        }
        return formattedList;
    }

    /**
     * Provide a String that contains the current grades of the given student
     * 
     * @param username
     *            username for student
     * @return a String that contains the current grades of username. The String
     *         should be formatted like studentGrades.txt---STUDENT_GRADES
     *         heading, student info, dividers, each assignment (assignment
     *         name followed by tab and assignment grade), and current grade.
     *         Assignments are to remain in the same order as given.
     */
    public String outputStudentGrades(String username) {
        String formattedList = "STUDENT_GRADES" + "\n" 
                + this.getStudent(username).toString();
        String assignsAndGrades = "";
        for (int i = 0; i < this.assignments.size(); i++) {
            String aname = this.assignments.get(i).getAssignmentName();
            assignsAndGrades += aname + "\t" 
                    + this.assignmentGrade(aname, username) + "\n";
        }
        String current = "CURRENT GRADE" + "\t" + this.currentGrade(username);
        return formattedList 
                + "----" + "\n"
                + assignsAndGrades 
                + "----" + "\n" 
                + current;
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
        double assignTotal = this.getAssignment(assignName).getTotal();
        double assignWeight = this.getAssignment(assignName).getWeight();
        String formattedList = "ASSIGNMENT_GRADES" + "\n" 
                + assignName + "\n"
                + assignTotal + "\n"
                + assignWeight + "\n"
                + "----" + "\n";
        
        // Print out the grade for each student for the given assignment.
        for (int i = 0; i < this.students.size(); i++) {
            String sname = this.students.get(i).getStudentUsername();
            double grade = this.assignmentGrade(assignName, sname);
            formattedList += sname + "\t" + grade + "\n";
        }
        
        formattedList += "----" + "\n"
                + "STATS" + "\n"
                + "Average" + "\t" + this.average(assignName) + "\n"
                + "Median" + "\t" + this.median(assignName) + "\n"
                + "Max" + "\t" + this.max(assignName) + "\n"
                + "Min" + "\t" + this.min(assignName) + "\n";
        return formattedList;
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
        String gb = "";
        for (int i = 0; i < this.students.size(); i++) {
            gb = gb.concat(this.students.get(i).toString());
        }

        for (int i = 0; i < this.assignments.size(); i++) {
            gb = gb.concat(this.assignments.get(i).toString());
        }
        return gb;
    }
}