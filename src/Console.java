import gradebook.MyGradeBook;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles console and command line interface
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
public class Console {
    
    private static final Console INSTANCE = new Console();
    private MyGradeBook gradebook;
    private Scanner input;
    
    /**
     * Singleton Constructor to create a console
     */
    private Console() {
        this.gradebook = MyGradeBook.initialize();
        input = new Scanner(System.in);
    }
    
    /**
     * Returns a final instance of the Console 
     * class to ensure that only one Console is created
     * @return
     */
    public static Console getInstance() {
        return INSTANCE;
    }
    
    /************PROTECTED FUNCTIONS TO COMMUNICATE WITH USER CLASS***********/
    /**
     * Logs into the console. CURRENTLY DOES NOT ACTUALLY CHECK ID
     * @return true if login is successful, otherwise false
     */
    boolean login() {
        System.out.println("Welcome to the KAKO© GBS Console:");
        System.out.print("Please enter your teacher ID to continue: ");
        String teacherId = this.input.next();
        if (teacherId != null) {
            System.out.println("Authentication Successful!");
            this.input.nextLine(); //throw away empty space
            return true;
        }
        System.out.println("Sorry, you're ID is invalid");
        input.nextLine(); //throw away empty space
        return false;
    }
    
    /**
     * Takes in the user data from the console. Calls parseCommand which 
     * converts a command into an ArrayList of Strings
     * @return true if quit, otherwise false
     */
    boolean getCommand() {
        String command = this.input.nextLine();
        ArrayList<String> parsedCommand = this.parseCommand(command);
        if (!(parsedCommand.isEmpty())) {
            String firstCommand = parsedCommand.get(0);
            if (firstCommand.equals("gb add")) {
                this.add(parsedCommand);
            }
            else if (firstCommand.equals("gb assign")) {
                this.assign(parsedCommand);
            }
            else if (firstCommand.equals("gb calc")) {
                this.calc(parsedCommand);
            }
            else if (firstCommand.equals("gb import")) {
                this.importFormFile(parsedCommand);
            }
            else if (firstCommand.equals("gb print")) {
                this.print(parsedCommand);
            }
            else if (firstCommand.equals("gb output")) {
                this.output(parsedCommand);
            }
            else if (firstCommand.equals("gb help")) {
                this.help(parsedCommand);
            }
            else if (firstCommand.equals("gb quit")) {
                System.out.println(
                        "Thank you for using the KAKO© GBS Console. Goodbye!");
                return true;
            }
            else {
                System.out.println(parsedCommand.get(0) + 
                        " is not a valid command. "
                        + "Please enter a valid command "
                        + "to continue. For a list of valid commands, "
                        + "type \"gb help\" into the console");
            }
        }
        else {
            System.out.println("Please enter a valid command "
                        + "to continue. For a list of valid commands, "
                        + "type \"gb help\" into the console");
        }
        return false;
    }
    
    /********************GENERAL HELPER FUNCTIONS FOR CONSOLE*****************/
    /**
     * Parses comandline input. Converts multiple fields 
     * into an ArrayList of Strings
     * @param command - the command to parse
     * @return an ArrayList of Strings containing the parsed command
     */
    private ArrayList<String> parseCommand(String command) {
        ArrayList<String> parsedString = new ArrayList<String>();
        char[] chars = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars) {
            if (c != '-') {
                sb.append(c);
            }
            else {
                parsedString.add(sb.toString().trim());
                sb = new StringBuilder();
            }
        }
        parsedString.add(sb.toString().trim());
        return parsedString;
    }
    
    /******************PRIVATE METHODS EXECUTED FROM CONSOLE******************/
    /**
     * Add a Student or an Assignment to the gradebook
     * @param parsedCommand - a list of strings that contains the data to add
     */
    private void add(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 1) {
            String subCommandAdd = parsedCommand.get(1);
            if (subCommandAdd.equals("assignment") 
                    || subCommandAdd.equals("a")) {
                String name;
                Double totalPoints;
                Double weight;
                if (parsedCommand.size() == 5) {
                    name = parsedCommand.get(2);
                    totalPoints = Double.parseDouble(parsedCommand.get(3));
                    weight = Double.parseDouble(parsedCommand.get(4));
                }
                else {
                    System.out.print("Assignment Name: ");
                    name = input.nextLine();
                    System.out.print("Total Points: ");
                    totalPoints = Double.parseDouble(input.nextLine());
                    System.out.print("Weight: ");
                    weight = Double.parseDouble(input.nextLine());
                }
                this.gradebook.addAssignment(name, totalPoints, weight);
                System.out.println("Added assignment");
            }
            else if(subCommandAdd.equals("student") 
                    || subCommandAdd.equals("s")) {
                String username;
                String first;
                String last;
                String advisor;
                Integer year;
                if (parsedCommand.size() == 7) {
                    username = parsedCommand.get(2);
                    first = parsedCommand.get(3);
                    last = parsedCommand.get(4);
                    advisor = parsedCommand.get(5);
                    year = Integer.parseInt(parsedCommand.get(6));
                }
                else {
                    System.out.print("Student ID: ");
                    username = input.nextLine();
                    System.out.print("First Name: ");
                    first = input.nextLine();
                    System.out.print("Last Name: ");
                    last = input.nextLine();
                    System.out.print("Advisor: ");
                    advisor = input.nextLine();
                    System.out.print("Year: ");
                    year = Integer.parseInt(input.nextLine());
                }
                this.gradebook.addStudent(
                        username, first, last, advisor, year);
                System.out.println("Added student");
            }
            else {
                System.out.println(parsedCommand.get(1) + 
                        " is not a valid sub-command for gb add. "
                    + "Please enter a valid command to continue. "
                    + "For a list of valid commands, "
                    + "type \"gb help -add\" into the console");
            }
        }
        else {
            System.out.println(
                    "You must specify what you wish to add. "
                    + "Type \"gb help -add\" for move details");
        }
    }
    
    /**
     * Assign a grade to a gradebook
     * @param parsedCommand - a list of strings that 
     * contains the data to assign grade
     */
    private void assign(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 4) {
            String assignmentName = parsedCommand.get(1);
            String username = parsedCommand.get(2);
            Double newGrade = Double.parseDouble(parsedCommand.get(3));
            this.gradebook.changeGrade(assignmentName, username, newGrade);
            System.out.println("Grade assigned successfully");
        }
        else {
            System.out.println(
                    "gb assign must take an assignment, studentId, and grade. "
                    + "Please enter the correct parameters. "
                    + "Type \"gb help -assign\" for more details");
        }
    }
    
    /**
     * Calculate stats for an assignment
     * @param parsedCommand - a list of strings that contains 
     * the data to calculate stats
     */
    private void calc(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 1) {
            if (parsedCommand.size() > 2) {
                String assignmentName = parsedCommand.get(1);
                String statsField = parsedCommand.get(2);
                if (statsField.equals("mean")) {
                    System.out.println(this.gradebook.average(assignmentName));
                }
                else if (statsField.equals("median")) {
                    System.out.println(this.gradebook.median(assignmentName));
                }
                else if (statsField.equals("min")) {
                    System.out.println(this.gradebook.min(assignmentName));
                }
                else if (statsField.equals("max")) {
                    System.out.println(this.gradebook.max(assignmentName));
                }
                else {
                    System.out.println(parsedCommand.get(2) + 
                            " is not a valid sub-command for gb calc. "
                            + "Please enter a valid command to continue. "
                            + "For a list of valid commands, "
                            + "type \"gb help -calc\" into the console");
                }
            }
            else {
                System.out.println(
                        "You must specify which calculation you "
                        + "wish to perform. Choices are "
                        + "-mean, -median, -min, or -max");
            }
            
        }
        else {
            System.out.println(
                    "The command \" gb calc \" cannot be called without flags."
                    + " Please type \" gb help -calc \" for more information");
        }
    }
    
    /**
     * Display help information
     * @param parsedCommand - a list of strings that contains 
     * the data to display help information
     */
    private void help(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 1) {
            System.out.println(
                    "gb add -a(assignment) -s(student) ");
            System.out.println(
                    "gb assign -(assignmentName) -(StudentId) -(grade)");
            System.out.println(
                    "gb calc [-(studentName) -(assignmentName)] "
                    + "AND ONEOF[(-mean, -median, -min, -max)]");
            System.out.println("gb help");
            System.out.println(""
                    + "gb import (filename) -s(student) -a(assignment)");
            System.out.println(
                    "gb output -(fileName) -a(assignment) -s(student)");
            System.out.println(
                    "gb print -a(assignment) -s(student)");
            System.out.println("gb quit");
        }
        else if (parsedCommand.size() >= 2) {
            String subCommandHelp = parsedCommand.get(1);
            if (subCommandHelp.equals("add")) {
                System.out.println(
                        "Adds a student or assignment to the gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Add a student");
                System.out.println("-assignment (or -a): Add an assignment");
                System.out.println("FORMAT:");
                System.out.println(
                        "IF STUDENT: gb add -s (StudentId) (StudentFirstName) "
                        + "(StudentLastName) (Advisor) (Year)");
                System.out.println("IF ASSIGNMENT: gb add -a");
            }
            else if (subCommandHelp.equals("assign")) {
                System.out.println(
                        "Assign a grade to a student "
                        + "on a particular assignment");
                System.out.println("FORMAT: gb assign "
                        + "(assignmentName) (StudentId) (grade)");
            }
            else if (subCommandHelp.equals("calc")) {
                System.out.println(
                        "Calculate stats for a student or an assignment");
                System.out.println("FLAGS:");
                System.out.println(
                        "-student (or -s): Calculate stats for a student");
                System.out.println(
                        "-assignment (or -a): "
                        + "Calculate stats for an assignment");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb calc -(studentId)");
                System.out.println("IF ASSIGNMENT: gb calc -(assignmentName)");
            }
            else if (subCommandHelp.equals("help")) {
                System.out.println("Displays help information:");
                System.out.println("FLAGS:");
                System.out.println(
                        "(none): prints out general help information");
                System.out.println(
                        "-add: prints out specific information for add");
                System.out.println(
                        "-assign: prints out specific information for assign");
                System.out.println(
                        "-calc: prints out specific information for calc");
                System.out.println(
                        "-help: prints out this page. You obviously know this"
                        + " command if you got this far :)");
                System.out.println(
                        "-output: prints out specific information for output");
                System.out.println(
                        "-print: prints out specific information for print");
                System.out.println(
                        "-quit: prints out specific information for quit");
                System.out.println(
                        "-remove: prints out specific information for remove");
                System.out.println(
                        "-update: prints out specific information for update");
                System.out.println("FORMAT:");
                System.out.println(
                        "Example Usage: gb help -add");
            }
            else if (subCommandHelp.equals("import")) {
                System.out.println(
                        "Imports data for a student, "
                        + "assignment, or a gradebook from file");
                System.out.println("FLAGS:");
                System.out.println(
                        "-(fileName): specifies which file to use");
                System.out.println(
                        "-student (or -s): prints student data to a file");
                System.out.println(
                        "-assignment (or -a): "
                        + "prints assignment data to a file");
                System.out.println(
                        "-gradebook (or -g): print gradebook data to a file");
                System.out.println("FORMAT:");
                System.out.println(
                        "IF STUDENT: gb import (fileName) -s(studentId)");
                System.out.println(
                        "IF ASSIGNMENT: gb import "
                        + "(fileName) -a(assignmentName) ");
                System.out.println(
                        "IF GRADEBOOK: gb import (fileName)");
            }
            else if (subCommandHelp.equals("output")) {
                System.out.println(
                        "Outputs data for a student, "
                        + "assignment, or a gradebook to a file");
                System.out.println("FLAGS:");
                System.out.println(
                        "-file (or -f): specifies which file to use");
                System.out.println(
                        "-student (or -s): prints student data to a file");
                System.out.println(
                        "-assignment (or -a): "
                        + "prints assignment data to a file");
                System.out.println(
                        "-gradebook (or -g): print gradebook data to a file");
                System.out.println("FORMAT:");
                System.out.println(
                        "IF STUDENT: gb output (fileName) -s -(studentId)");
                System.out.println(
                        "IF ASSIGNMENT: "
                        + "gb output (fileName) -a -(assignmentName)");
                System.out.println(
                        "IF GRADEBOOK: gb output (fileName) -g");
            }
            else if (subCommandHelp.equals("print")) {
                System.out.println(
                        "Prints out data for a student, "
                        + "assignment, or a gradebook");
                System.out.println("FLAGS:");
                System.out.println(
                        "-student (or -s): "
                        + "prints student data to the console");
                System.out.println(
                        "-assignment (or -a): "
                        + "prints assignment data to the console");
                System.out.println(
                        "-gradebook (or -g): "
                        + "print gradebook data to the console");
                System.out.println("FORMAT:");
                System.out.println(
                        "IF STUDENT: gb print -s -(studentId)");
                System.out.println(
                        "IF ASSIGNMENT: gb print -a -(assignmentName)");
                System.out.println(
                        "IF GRADEBOOK: gb print -g");
            }
            else if (subCommandHelp.equals("quit")) {
                System.out.println("Quits the program");
                System.out.println("FLAGS:");
                System.out.println("(none)");
                System.out.println("FORMAT:");
                System.out.println("gb quit");
            }
            else {
                System.out.println(parsedCommand.get(1) + " "
                        + "is not a valid sub-command for gb help. "
                        + "Please enter a valid command to continue. "
                        + "For a list of valid commands, "
                        + "type \"gb help -help\" into the console");
            }
        }
    }
    
    /**
     * Output gradebook data the a file
     * @param parsedCommand - a list of strings that contains 
     * the data to output to a file
     */
    private void output(ArrayList<String> parsedCommand) {
        String fileName = parsedCommand.get(1);
        if (parsedCommand.size() == 2) {
//            this.gradebook.
        }
        else {
            String subCommandOutput = parsedCommand.get(1);
            if (subCommandOutput.equals("assignment") 
                    || subCommandOutput.equals("a")) {
                String assignmentName = parsedCommand.get(3);
            }
            else if (subCommandOutput.equals("student") 
                    || subCommandOutput.equals("s")) {
                String studentName = parsedCommand.get(3);
            }
            else {
                System.out.println(parsedCommand.get(2) + 
                        " is not a valid subcommand for gb output." 
                    + "Please enter a valid command to continue. "
                    + "For a list of valid commands, "
                    + "type \"gb help -output\" into the console");
            }
        }
    }

    /**
     * Print gradebook data to console
     * @param parsedCommand - a list of strings that contains 
     * the data to print data to console
     */
    private void print(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 1) {
            System.out.print(gradebook.outputGradebook());
        }
        else {
            String subCommandPrint = parsedCommand.get(1);
            if (subCommandPrint.equals("assignment") 
                    || subCommandPrint.equals("a")) {
                if (parsedCommand.size() > 2) {
                    String assignmentName = parsedCommand.get(2);
                    try {
                        System.out.print(
                                gradebook.outputAssignmentGrades(
                                        assignmentName));
                        
                    }
                    catch (RuntimeException ex) {
                        System.out.println(
                                "The assignment name you looked "
                                + "up does not exist!");
                    }
                }
                else {
                    System.out.println(
                            "The assignment name you entered is not valid");
                }
                
            }
            else if (subCommandPrint.equals("student") 
                    || subCommandPrint.equals("s")) {
                if (parsedCommand.size() > 2) {
                    String username = parsedCommand.get(2);
                    try {
                        System.out.print(
                                gradebook.outputStudentGrades(username));
                    }
                    catch (RuntimeException ex) {
                        System.out.println(
                                "The student name you entered is not valid");
                    }
                }
                else {
                    System.out.println("You must enter a a student name");
                }
            }
            else {
                System.out.println(parsedCommand.get(1) 
                        + " is not a valid subcommand for gb print." 
                        + "Please enter a valid command to continue. "
                        + "For a list of valid commands, "
                        + "type \"gb help -print\" into the console");
            }
        }
    }
    
    /**
     * Import a gradebook or information from a file
     * @param parsedCommand - a list of strings that contains the 
     * data to import gradebook information
     */
    private void importFormFile(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 0) {
            String fileName = parsedCommand.get(1);
        }
        else {
            System.out.println(
                    "The command \" gb import \" cannot "
                    + "be called without flags. Please type "
                    + "\" gb help -import \" for more information");
        }
    }
}
