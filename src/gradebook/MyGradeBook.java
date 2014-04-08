package gradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    
    /**
     * Factory method to construct an empty MyGradebook
     * 
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        return new MyGradeBook();
    }

    Scanner convertToInt(Scanner intline) {
        String ints = "";
        while (intline.hasNext()) {
            if (intline.hasNextDouble()) {
                int i = new Integer((int) intline.nextDouble());
                ints = ints.concat(i + "\t");
            }
            else {
                ints = ints.concat(intline.nextInt() + "\t");
            }
        }
        return new Scanner(ints);
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
    public static MyGradeBook initializeWithFile(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        MyGradeBook mygb = MyGradeBook.initialize();
        scan.nextLine();
        Scanner scanNames = new Scanner(scan.nextLine()).useDelimiter("\t");
        scanNames.skip("\t" + "\t" + "\t" + "\t" + "\t");
        Scanner scanTotals = new Scanner(scan.nextLine());
        Scanner scanWeights = new Scanner(scan.nextLine());
        //mygb.convertToInt(scanTotals);
        //mygb.convertToInt(scanWeights);
        while (scanTotals.hasNext()) {
            Assignment newAssignment = 
                    new Assignment(
                            scanNames.next(), 
                            scanTotals.nextInt(), 
                            scanWeights.nextInt());
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
                    if (scanStudent.hasNextInt()) {
                        mygb.assignments.get(i).addAssignmentGrade(susername,
                                new Double(scanStudent.nextInt()));
                    }
                    else {
                        mygb.assignments.get(i).addAssignmentGrade(susername,
                                scanStudent.nextDouble());
                    }
                }
            }
        }
        return mygb;
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
        // TODO Needs to be made applicable to strings.
        /*Scanner scan = new Scanner(startingString);
        while(scan.hasNext()) {
            if (scan.hasNext("\n")) {   
            }
            
        }*/
        return null;
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
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            if (scan.nextLine().equals("ASSIGNMENT")) {
                new Assignment(scan.nextLine(), scan.nextInt(), 
                        scan.nextInt());
            }
            if (scan.nextLine().equals("STUDENT")) {
                new Student(scan.nextLine(), scan.nextLine(), scan.nextLine(),
                        scan.nextLine(), scan.nextInt());
            }
            if (scan.nextLine().equals("GRADES_FOR_ASSIGNMENT")) {
                String a1name = scan.nextLine();
                Assignment a1 = this.getAssignment(a1name);
                a1.addAssignmentGrade(scan.nextLine(), scan.nextDouble());
            }
            if (scan.nextLine().equals("GRADES_FOR_STUDENT")) {
                String s1name = scan.nextLine();
                Student s1 = this.getStudent(s1name);
                s1.addStudentGrade(this.getAssignment(scan.nextLine()), 
                        scan.nextDouble());
            }
        }
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
        // TODO Needs to be tweaked so that it is applicable to strings.
        /*Scanner scan = new Scanner(additionalString);
        while (scan.hasNextLine()) {
            if (scan.next().equals("ASSIGNMENT")) {
                new Assignment(scan.next(), scan.nextInt(), scan.nextInt());
            }
            if (scan.nextLine().equals("STUDENT")) {
                new Student(scan.next(), scan.next(), scan.next(), scan.next(),
                        scan.nextInt());
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
        }*/
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
            int weight = this.assignments.get(i).getWeight();
            int total = this.assignments.get(i).getTotal();
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
        String agrades = "";
        int assignTotal = this.getAssignment(assignName).getTotal();
        int assignWeight = this.getAssignment(assignName).getWeight();
        String heading = "ASSIGNMENT_GRADES" + "\n" 
                + assignName + "\n"
                + assignTotal + "\n"
                + assignWeight + "\n"
                + "----" + "\n";
        agrades = agrades + heading;
        for (int i = 0; i < this.students.size(); i++) {
            String sname = this.students.get(i).getStudentUsername();
            double grade = this.assignmentGrade(assignName, sname);
            agrades = agrades.concat(sname + "\t" + grade + "\n");
        }
        
        agrades = agrades + "----" + "\n"
                + "STATS" + "\n"
                + "Average" + "\t" + this.average(assignName) + "\n"
                + "Median" + "\t" + this.median(assignName) + "\n"
                + "Max" + "\t" + this.max(assignName) + "\n"
                + "Min" + "\t" + this.min(assignName) + "\n";
        return agrades;
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
    
    
    /* Stuff from defunct GradeBook class
     * 
    
    GradeBook(String courseName, String courseNumber, String teacherName, String teacherId) {
        this.couresNumber = courseName;
        this.couresNumber = courseNumber;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }
  
    
    ArrayList<Double> getListOfScores(Assignment assignment) {
//        ArrayList<Double> scores = new ArrayList<Double>();
//        for (Student s : this.students) {
//            for (Grade g : s.grades) {
//                if (g.assignment == assignment) {
//                    scores.add(g.getScore());
//                }
//            }
//        }
//        return scores;
        return null;
    }
    
    
   
     */
    
}