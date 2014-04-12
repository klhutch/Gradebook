package gradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**Class MyGradebook
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 8th, 2014
 *
 */
public class MyGradeBook {
    
    /** The name of the course that this gradebook hold students for. */
    //String courseName = "";
    /** The CRN for the course that this gradebook hold students for. */
    //String courseNumber = "";
    /** The teacher's name for the course for this gradebook. */
    //String teacherName = "";
    /** The teacher's username for the course for this gradebook. */
    //String teacherId = "";
    
    
    /** A list of students for this gradebook. */
    Set<Student> students;
    /** A list of assignments for this gradebook. */
    Set<Assignment> assignments;
    
    
    /** Constructor MyGradeBook
     * returns a basic gradebook with all fields empty
     */
    public MyGradeBook() {
        this.students = new TreeSet<Student>(new MyStudentComparator());
        this.assignments = new LinkedHashSet<Assignment>();
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
     * @throws RuntimeException if file not found
     */
    private String convertFileToString(String filename) 
        throws RuntimeException {
        Scanner filescan;
        String startingString = "";
        try {
            filescan = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
            
        while (filescan.hasNextLine()) {
            startingString += filescan.nextLine() + "\n";
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
     * @throws RuntimeException if file not found
     */
    public static MyGradeBook initializeWithFile(String filename) 
        throws RuntimeException {
        MyGradeBook mygb = MyGradeBook.initialize();
        String file = mygb.convertFileToString(filename);
        
        return MyGradeBook.initializeWithString(file);
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
        MyGradeBook mygb = MyGradeBook.initialize();
        
        //Create a scanner
        Scanner scan = new Scanner(startingString);
        scan.nextLine();
        
        //Initialize Assignments & Students
        initializeWithStringAssignments(mygb, scan);      
        initializeWithStringStudents(mygb, scan);
              
        return mygb;
    }
    
    /**
     * Helper method for initializeWithString
     * adds students to the gradebook from a string initialization
     * 
     * @param mygb the gradebook to add students to
     * @param scan the scanner containing the student information
     */
    private static void initializeWithStringStudents(MyGradeBook mygb,
            Scanner scan) {
        //Add Students to the GradeBook
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
                
                //Add the created Student's grades to each assignment
                Iterator<Assignment> myit = mygb.assignments.iterator();
                while (myit.hasNext()) {
                    myit.next().addAssignmentGrade(susername,
                           scanStudent.nextDouble());
                }
            }
        }        
    }
    
    /**
     * Helper method for initializeWithString
     * adds assignments to the gradebook from a string initialization
     * 
     * @param mygb the gradebook to add students to
     * @param scan the scanner containing the assignment information
     */
    private static void initializeWithStringAssignments(MyGradeBook mygb,
            Scanner scan) {
        //Separate the next three lines for names, totals, and weights
        String names = scan.nextLine();
        String totals = scan.nextLine();
        String weights = scan.nextLine();
        
        //Create a scanner for the lines with names, weights, and totals
        Scanner scanNames = new Scanner(names).useDelimiter("\t");
        Scanner scanTotals = new Scanner(totals);
        Scanner scanWeights = new Scanner(weights);
        
        
        
        //Tell the Scanner for names to skip a bunch of tabs
        scanNames.skip("\t" + "\t" + "\t" + "\t" + "\t");
        
        //Add assignments to the GradeBook
        while (scanTotals.hasNext()) {
            mygb.addAssignment(scanNames.next(), 
                    scanTotals.nextDouble(), 
                    scanWeights.nextDouble());
        }
        
        
    }

    /**
     * Adds an assignment to this gradebook.
     * 
     * @param name The name of a given assignment.
     * @param totalPoints The total points for the given assignment.
     * @param weight The weight of the given assignment.
     */
    public void addAssignment(String name, double totalPoints, double weight) {
        Assignment newAssign = new Assignment(name, totalPoints, weight);
        this.assignments.add(newAssign);
        
        Iterator<Student> myiter = this.students.iterator();
        while (myiter.hasNext()) {
            newAssign.changeGrade(myiter.next().getStudentUsername(), 0);
        }
    }
    
    /**
     * Adds a student to this gradebook.
     * 
     * @param username The username of a given student.
     * @param first The first name of the given student.
     * @param last The last name of the given student.
     * @param advisor The advisor of the given student.
     * @param year The graduation year of the given student.
     */
    public void addStudent(String username, String first, String last, 
            String advisor, int year) {
        this.students.add(new Student(username, first, last, advisor, year));
        
        Iterator<Assignment> myiter = this.assignments.iterator();
        while (myiter.hasNext()) {
            myiter.next().changeGrade(username, 0);
        }
    }
    
    /**
     * Gets an assignment that has the same name as the given one from this
     * gradebook's list of assignments.
     * 
     * @param aname
     *            The name of the assignment in question.
     * @return Assignment A way to represent an assignment that contains
     *         student's grades.
     * @throws RuntimeException if Assignment is not found
     */
    Assignment getAssignment(String aname) throws RuntimeException {
        Iterator<Assignment> myit = this.assignments.iterator();
        
        while (myit.hasNext()) {
            Assignment current = myit.next();
            if (current.getAssignmentName().equals(aname)) {
                return current;
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
     * @throws RuntimeException if Student is not found
     */
    Student getStudent(String sname) throws RuntimeException {
        Iterator<Student> myit = this.students.iterator();
        
        while (myit.hasNext()) {
            Student current = myit.next();
            if (current.getStudentUsername().equals(sname)) {
                return current;
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
    public void processString(String additionalString) 
        throws RuntimeException {
        
        String option1 = "ASSIGNMENT";
        String option2 = "STUDENT";
        String option3 = "GRADES_FOR_STUDENT";
        String option4 = "GRADES_FOR_ASSIGNMENT";
        
        try { 
            Scanner scan = new Scanner(additionalString);
            String firstLine = scan.nextLine();
            
        
            if (firstLine.equals(option1)) {
                //ASSIGNMENT
                this.processStringAssignments(additionalString);
                
            }
            else if (firstLine.substring(
                    0, option2.length()).equals(option2)) {
                //STUDENT
                this.processStringStudents(additionalString);
            }
            
            else if (firstLine.equals(option3)) {
                //GRADES_FOR_STUDENT
                this.processStringGradesStudents(additionalString);
               
            }
            else if (firstLine.equals(option4)) { 
                //GRADES_FOR_ASSIGNMENT 
                this.processStringGradesAssignments(additionalString);
            }
            else {
                throw new RuntimeException("Wrong Header");
            }
            scan.close();
        }
        catch (InputMismatchException m ) {
            throw new RuntimeException("Wrong Format");
        }
    }
    
    /**
     * helper method for processString
     * processes a String with the header "GRADES_FOR_ASSIGNMENT
     * @param additionalString the String to be processed
     */
    private void processStringGradesAssignments(String additionalString) {
        Scanner scan = new Scanner(additionalString);
        String firstLine = scan.nextLine();

        String assignName = scan.nextLine();
        
        
        while (scan.hasNextLine()) {
            
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                if (user.equals(firstLine)) {
                    break;
                }
                Double grade = scan.nextDouble();
                this.changeGrade(assignName, user, grade);
                
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }
                
            }   
        }
        
            
    }
    
    /**
     * helper method for processString
     * processes a String with the header "GRADES_FOR_STUDENT"
     * @param additionalString the String to be processed
     */
    private void processStringGradesStudents(String additionalString) {
        
        Scanner scan = new Scanner(additionalString);
        scan.nextLine();
        String user = scan.nextLine();
        
        while (scan.hasNextLine()) {
            String aName = scan.nextLine();
            double grade = scan.nextDouble();
            this.changeGrade(aName, user, grade);
            
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
        }  
    }
    
    /**
     * helper method for processString
     * processes a String with the header "STUDENT"
     * @param additionalString the String to be processed
     */
    private void processStringStudents(String additionalString) {
        Scanner scan = new Scanner(additionalString);
        
        while (scan.hasNextLine()) {
            scan.nextLine();
        
            String user = scan.next();
            String first = scan.next();
            String last = scan.next();
            String advisor = scan.next();
            int year = scan.nextInt();
            
            this.addStudent(user, first, last, advisor, year);
            
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
            
        }        
    }
    
    /**
     * helper method for processString
     * processes a String with the header "ASSIGNMENT"
     * @param additionalString the String to be processed
     */
    private void processStringAssignments(String additionalString) {
        Scanner scan = new Scanner(additionalString);
        
        while (scan.hasNextLine()) {
            scan.nextLine();
            String name = scan.nextLine();
            Double points = scan.nextDouble();
            Double weight = scan.nextDouble();
            
            this.addAssignment(name, points, weight);
            
            if (scan.hasNextLine()) {
                scan.nextLine();
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
        try {
            this.getStudent(username); //throws an error if it's not there
            Assignment assign = this.getAssignment(assignmentName);
            return assign.changeGrade(username, newGrade);
        }
        catch (RuntimeException e) {
            // assignment is not there or student is not there
            return false; 
        }
        
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
        
        Iterator<Assignment> myiter = this.assignments.iterator();
        
        while (myiter.hasNext()) {
            Assignment current = myiter.next();
            double grade = current.assignmentGrade(username);
            double weight = current.getWeight();
            double total = current.getTotal();
            assignGrades += (weight * (grade / total));
            allWeights += weight;
        }
        double curGrade = (assignGrades / allWeights) * 100;
        
        BigDecimal bGrade = new BigDecimal(curGrade);
        bGrade = bGrade.setScale(13, RoundingMode.FLOOR);
        curGrade = bGrade.doubleValue();
        
        return curGrade; 
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
        Iterator<Student> myiter = this.students.iterator();
        
        while (myiter.hasNext()) {
            Student current = myiter.next();
            String username = current.getStudentUsername();
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
        
        Iterator<Student> myiter = this.students.iterator();
        while (myiter.hasNext()) {
            String user = myiter.next().getStudentUsername();
            formattedList += user + "\t" 
                    + this.currentGrades().get(user) + "\n";
        }
        return formattedList;
        
    }
    
    /**
     * writes the result from outputCurrentGrades to a file
     * 
     * @param filename The name of the file to ouput to
     * @return String message re: completion of file output
     */
    public String fileOutputCurrentGrades(String filename) {
        try {
            PrintWriter file = new PrintWriter(filename, "UTF-8");
            file.println(this.outputCurrentGrades());
            file.close();    
            
        } 
        catch (FileNotFoundException e) {
            return filename + " was not found";
        } 
        catch (UnsupportedEncodingException e) {
            return "Cannot write file";
        }
        return "Wrote current grades to " + filename;
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
     * @throws RuntimeException if Student not found
     */
    public String outputStudentGrades(String username) 
        throws RuntimeException {
        String formattedList = "STUDENT_GRADES" + "\n" 
                + this.getStudent(username).toString();
        String assignsAndGrades = "";
        
        Iterator<Assignment> myiter = this.assignments.iterator();
        while (myiter.hasNext()) {
            Assignment current = myiter.next();
            String aname = current.getAssignmentName();
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
     * writes the result from outputCurrentGrades to a file
     * 
     * @param user The username of the Student to get grades of
     * @param filename The name of the file to ouput to
     * @return String telling of completion of file Output
     */
    public String fileOutputStudentGrades(String user, String filename) {
        try {
            PrintWriter file = new PrintWriter(filename, "UTF-8");
            file.println(this.outputStudentGrades(user));
            file.close();                
        } 
        catch (FileNotFoundException e) {
            return filename + " was not found";
        } 
        catch (UnsupportedEncodingException e) {
            return "Cannot write file";
        }
        catch (RuntimeException e) {
            return "Student not found";
        }
        
        return "Wrote student grades to " + filename;
    }

    /**
     * Provide a String that contains the assignment grades of all students in
     * the course for the given assignment
     * 
     * @param assignName
     *            name of the assignment to get
     * @return a String that contains the assignment grades of all students in
     *         the course for assignName. The String should be formatted like
     *         assignmentGrade.txt--ASSIGNMENT_GRADES heading, assignment info,
     *         dividers, each student (username followed by tab and assignment
     *         grade), and assignment stats. The usernames will be listed
     *         alphabetically while assignments are to remain in the same 
     *         order as given.
     * @throws RuntimeException if Assignment is not found
     */
    public String outputAssignmentGrades(String assignName) 
        throws RuntimeException {
        double assignTotal = this.getAssignment(assignName).getTotal();
        double assignWeight = this.getAssignment(assignName).getWeight();
        String formattedList = "ASSIGNMENT_GRADES" + "\n" 
                + assignName + "\n"
                + assignTotal + "\n"
                + assignWeight + "\n"
                + "----" + "\n";
        
        // Print out the grade for each student for the given assignment.
        Iterator<Student> myiter = this.students.iterator();
        while (myiter.hasNext()) {
            Student current = myiter.next();
            String sname = current.getStudentUsername();
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
     * writes the result from outputAssignmentGrades to a file
     * 
     * @param aName The name of the Assignment to get grades of
     * @param fName The name of the file to ouput to
     * @return a String message about completion of Output
     */
    public String fileOutputAssignmentGrades(String aName, String fName) {
        PrintWriter file;
        try {
            file = new PrintWriter(fName, "UTF-8");
            file.println(this.outputAssignmentGrades(aName));
            file.close();  
        } 
        catch (FileNotFoundException e) {
            return fName + " was not found";
        } 
        catch (UnsupportedEncodingException e) {
            return "Cannot write file";
        }
        catch (RuntimeException e) {
            return "Assignment not found";
        }
        return "Wrote assignment grades to " + file;
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
    // TODO Needs to be shorter.
    public String outputGradebook() {
        String formattedGB = "GRADEBOOK" + "\n";
        String assignTabs = "\t" + "\t" + "\t" + "\t";
        String assignNames = "";
        String assignTotals = "";
        String assignWeights = "";
        formattedGB += assignTabs;
        
        Iterator<Assignment> assigniter = this.assignments.iterator();
        while (assigniter.hasNext()) {
            Assignment current = assigniter.next();
            assignNames += "\t" + current.getAssignmentName();
            assignTotals += "\t" + current.getTotal();
            assignWeights += "\t" + current.getWeight();
        }
        formattedGB += assignNames + "\n" + assignTabs + assignTotals + "\n" 
                + assignTabs + assignWeights + "\n";
        
        String studentList = "";
        
        Iterator<Student> stuiter = this.students.iterator();
        while (stuiter.hasNext()) {
            Student curStud = stuiter.next();
            String assignGrades = "";
            String sname = curStud.getStudentUsername();
            Student student = this.getStudent(sname);
            studentList = sname + "\t" + student.getFirstName() + "\t" 
                    + student.getLastName() + "\t" 
                    + student.getAdvisor() + "\t"
                    + student.getGradYear();
            Iterator<Assignment> myiter = this.assignments.iterator();
            while (myiter.hasNext()) {
                Assignment curA = myiter.next();
                String aname = curA.getAssignmentName();
                assignGrades += "\t" + this.assignmentGrade(aname, sname);
            }
            formattedGB += studentList + assignGrades + "\n";
        }
        return formattedGB;
    }
    
    
    /**
     * writes the result from outputGradebook to a file
     * 
     * @param filename The name of the file to ouput to
     * @return String message re: completion of file output
     */
    public String fileOutputGradebook(String filename) {
        
        try {
            PrintWriter file = new PrintWriter(filename, "UTF-8");
            file.println(this.outputGradebook());
            file.close();
            
            return "Wrote gradebook to " + filename;
        } 
        catch (FileNotFoundException e) {
            return filename + " was not found";
        } 
        catch (UnsupportedEncodingException e) {
            return "Cannot write file";
        }
    }
    
    /** 
     * method equals
     * to determine the equivalence of two objects, one of which is
     * an assignment
     * 
     * @param obj - the Object to compare 'this' to
     * @return boolean - "Is 'this' equivalent to obj?"
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (!(obj instanceof MyGradeBook))) {
            return false;
        }
       
        MyGradeBook comp = (MyGradeBook) obj;
        
        
        return (this.hasSameStudents(comp) &&
                this.hasSameAssignments(comp));
        
       
  
    }

    /**
     * checks to see if two MyGradeBooks have the same Assignments
     * @param comp the MyGradeBook to check against 'this'
     * @return do 'this' and comp have the same Assignments?
     */
    private boolean hasSameAssignments(MyGradeBook comp) {
        
        Iterator<Assignment> myiter = comp.assignments.iterator();
        while (myiter.hasNext()) {
            if ( !(this.hasAssignment(myiter.next()))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * checks to see if an Assignment is in 'this' MyGradeBook
     * @param assign the Assignment to look for
     * @return does 'this' have the Assignment assign?
     */
    boolean hasAssignment(Assignment assign) {
        
        Iterator<Assignment> myiter = this.assignments.iterator();
        while (myiter.hasNext()) {
            if (myiter.next().equals(assign)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks to see if two MyGradeBooks have the same Students
     * @param comp the MyGradeBook to check against 'this'
     * @return do 'this' and comp have the same Students?
     */
    private boolean hasSameStudents(MyGradeBook comp) {
        
        Iterator<Student> myiter = comp.students.iterator();
        while (myiter.hasNext()) {
            if ( !(this.hasStudent(myiter.next()))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * checks to see if a Student is in 'this' MyGradeBook
     * @param stud The student to look for
     * @return does 'this' contain stud?
     */
    boolean hasStudent(Student stud) {
        
        Iterator<Student> myiter = this.students.iterator();
        while (myiter.hasNext()) {
            if (myiter.next().equals(stud)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * converts 'this' MyGradeBook to a String
     * @return the result from outputGradebook
     * 
     */
    @Override
    public String toString() {
        return this.outputGradebook();
        
    }
    
    
    
    
    
}
