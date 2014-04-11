import gradebook.MyGradeBook;
import java.util.ArrayList;
import java.util.Scanner;


public class Console {
    
    private static final Console INSTANCE = new Console();
    private MyGradeBook gradebook;
    private Scanner input;
    
    private Console() {
        this.gradebook = MyGradeBook.initialize();
        input = new Scanner(System.in);
    }
    
    public static Console getInstance() {
        return INSTANCE;
    }
    
    /************PROTECTED FUNCTIONS TO COMMUNICATE WITH USER CLASS***********/
    boolean login() {
        System.out.println("Welcome to the KAKO© GBS Console:");
        System.out.print("Please enter your teacher ID to continue:");
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
                return false;
            }
            else {
                System.out.println(parsedCommand.get(0) + 
                        " is not a valid command. Please enter a valid command "
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
    private void add(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 1) {
            String subCommandAdd = parsedCommand.get(1);
            if (subCommandAdd.equals("assignment") || subCommandAdd.equals("a")) {
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
                    System.out.print("weight: ");
                    weight = Double.parseDouble(input.nextLine());
                }
                this.gradebook.addAssignment(name, totalPoints, weight);
                System.out.println("Added assignment");
            }
            else if(subCommandAdd.equals("student") || subCommandAdd.equals("s")) {
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
                this.gradebook.addStudent(username, first, last, advisor, year);
                System.out.println("Added student");
            }
            else {
                System.out.println(parsedCommand.get(1) + " is not a valid sub-command for gb add. "
                    + "Please enter a valid command to continue. For a list of valid commands, "
                    + "type \"gb help -add\" into the console");
            }
        }
        else {
            System.out.println("You must specify what you wish to add. Type \"gb help -add\" for move details");
        }
    }
    
    private void assign(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 4) {
            String assignmentName = parsedCommand.get(1);
            String username = parsedCommand.get(2);
            Double newGrade = Double.parseDouble(parsedCommand.get(3));
            this.gradebook.changeGrade(assignmentName, username, newGrade);
        }
        else {
            System.out.println("gb assign must take an assignment, studentId, and grade. Please enter the correct parameters. Type \"gb help -assign\" for more details");
        }
    }
    
    private void calc(ArrayList<String> parsedCommand) {
        String subCommandCalc = parsedCommand.get(1);
        if (subCommandCalc.equals("assignment") || subCommandCalc.equals("a")) {
            String assignmentName = parsedCommand.get(2);
            String statsField = parsedCommand.get(3);
            if (statsField == "mean") {
                System.out.println(this.gradebook.average(assignmentName));
            }
            else if (statsField == "median") {
                System.out.println(this.gradebook.median(assignmentName));
            }
            else if (statsField == "min") {
                System.out.println(this.gradebook.min(assignmentName));
            }
            else if (statsField == "max") {
                System.out.println(this.gradebook.max(assignmentName));
            }
            else {
                System.out.println(parsedCommand.get(3) + " is not a valid sub-command for gb calc. "
                        + "Please enter a valid command to continue. For a list of valid commands, "
                        + "type \"gb help -calc\" into the console");
            }
        }
        else if (subCommandCalc.equals("student") || subCommandCalc.equals("s")) {
            String studentName = parsedCommand.get(2);
            
        }
        else {
            System.out.println("The first parameter of gb calc must specify a an assignment or a student. Type \"gb help -calc\" for more details");
        }
    }
    
    private void help(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 1) {
            System.out.println("gb add -s (student) -a (assignment) -g (gradebook))");
            System.out.println("gb assign -(assignmentName) -(StudentId) -(grade)");
            System.out.println("gb calc -student OR -assignment OR -gradebook "
                    + "AND ONEOF(-mean, -median, -min, -max, -range)");
            System.out.println("gb print -student OR -assignment OR -gradebook");
            System.out.println("gb output - student OR -assignment OR -gradebook");
            System.out.println("gb remove -student OR -assignment OR -gradebook");
            System.out.println("gb update -student OR -assignment OR -gradebook");
            System.out.println("gb help");
            System.out.println("gb quit");
        }
        else if (parsedCommand.size() >= 2) {
            String subCommandHelp = parsedCommand.get(1);
            if (subCommandHelp.equals("add")) {
                System.out.println("Adds a student or assignment to the gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Add a student");
                System.out.println("-assignment (or -a): Add an assignment");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb add -s (StudentId) (StudentFirstName) (StudentLastName) (Advisor) (Year)");
                System.out.println("IF ASSIGNMENT: gb add -a");
            }
            else if (subCommandHelp.equals("assign")) {
                System.out.println("Assign a grade to a student on a particular assignment");
                System.out.println("FORMAT: gb assign (assignmentName) (StudentId) (grade)");
            }
            else if (subCommandHelp.equals("calc")) {
                System.out.println("Calculate stats for a student or an assignment");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Calculate stats for a student");
                System.out.println("-assignment (or -a): Calculate stats for an assignment");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb calc -(studentId)");
                System.out.println("IF ASSIGNMENT: gb calc -(assignmentName)");
            }
            else if (subCommandHelp.equals("help")) {
                System.out.println("Displays help information:");
                System.out.println("FLAGS:");
                System.out.println("(none): prints out general help information");
                System.out.println("-add: prints out specific information for add");
                System.out.println("-assign: prints out specific information for assign");
                System.out.println("-calc: prints out specific information for calc");
                System.out.println("-help: prints out this page. You obviously know this command if you got this far :)");
                System.out.println("-output: prints out specific information for output");
                System.out.println("-print: prints out specific information for print");
                System.out.println("-quit: prints out specific information for quit");
                System.out.println("-remove: prints out specific information for remove");
                System.out.println("-update: prints out specific information for update");
                System.out.println("FORMAT:");
                System.out.println("Example Usage: gb help -add");
            }
            else if (subCommandHelp.equals("output")) {
                System.out.println("Outputs data for a student, assignment, or a gradebook to a file");
                System.out.println("FLAGS:");
                System.out.println("-file (or -f): specifies which file to use");
                System.out.println("-student (or -s): prints student data to a file");
                System.out.println("-assignment (or -a): prints assignment data to a file");
                System.out.println("-gradebook (or -g): print gradebook data to a file");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb output (fileName) -s -(studentId)");
                System.out.println("IF ASSIGNMENT: gb output (fileName) -a -(assignmentName)");
                System.out.println("IF GRADEBOOK: gb output (fileName) -g");
            }
            else if (subCommandHelp.equals("print")) {
                System.out.println("Prints out data for a student, assignment, or a gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): prints student data to the console");
                System.out.println("-assignment (or -a): prints assignment data to the console");
                System.out.println("-gradebook (or -g): print gradebook data to the console");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb print -s -(studentId)");
                System.out.println("IF ASSIGNMENT: gb print -a -(assignmentName)");
                System.out.println("IF GRADEBOOK: gb print -g");
            }
            else if (subCommandHelp.equals("quit")) {
                System.out.println("Quits the program");
                System.out.println("FLAGS:");
                System.out.println("(none)");
                System.out.println("FORMAT:");
                System.out.println("gb quit");
            }
//            case "remove":
//                System.out.println("Remove a student or assignment from the gradebook");
//                System.out.println("FLAGS:");
//                System.out.println("-student (or -s): Remove student from the gradebook");
//                System.out.println("-assignment (or -a): Remove assignment from the gradebook");
//                System.out.println("FORMAT:");
//                System.out.println("IF STUDENT: gb remove -s -(studentId)");
//                System.out.println("IF ASSIGNMENT: gb remove -a -(assignmentName)");
//                break;
//            case "update":
//                System.out.println("Updates information about a student, assignment, or a gradebook.");
//                System.out.println("FLAGS:");
//                System.out.println("-student (or -s): prints student data to a file");
//                System.out.println("-assignment (or -a): prints assignment data to a file");
//                System.out.println("-gradebook (or -g): print gradebook data to a file");
//                System.out.println("FORMAT:");
//                System.out.println("IF STUDENT: gb update -s -(studentId)");
//                System.out.println("IF ASSIGNMENT: gb output -a -(assignmentName)");
//                System.out.println("IF GRADEBOOK: gb output -g");
//                break;
            else {
                System.out.println(parsedCommand.get(1) + " is not a valid sub-command for gb help. "
                        + "Please enter a valid command to continue. For a list of valid commands, "
                        + "type \"gb help -help\" into the console");
            }
        }
    }
    
    private void output(ArrayList<String> parsedCommand) {
        String fileName = parsedCommand.get(1);
        if (parsedCommand.size() == 2) {
//            this.gradebook.
        }
        else {
            String subCommandOutput = parsedCommand.get(1);
            if (subCommandOutput.equals("assignment") || subCommandOutput.equals("a")) {
                String assignmentName = parsedCommand.get(3);
            }
            else if (subCommandOutput.equals("student") || subCommandOutput.equals("s")) {
                String studentName = parsedCommand.get(3);
            }
            else {
                System.out.println(parsedCommand.get(2) + " is not a valid subcommand for gb output." 
                    + "Please enter a valid command to continue. For a list of valid commands, "
                    + "type \"gb help -output\" into the console");
            }
        }
    }

    private void print(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 1) {
            System.out.print(gradebook.outputGradebook());
        }
        else {
            String subCommandPrint = parsedCommand.get(1);
            if (subCommandPrint.equals("assignment") || subCommandPrint.equals("a")) {
                if (parsedCommand.size() > 2) {
                    String assignmentName = parsedCommand.get(2);
                    try {
                        System.out.print(gradebook.outputAssignmentGrades(assignmentName));
                    }
                    catch (RuntimeException ex) {
                        System.out.println("The assignment name you looked up does not exist!");
                    }
                }
                else {
                    System.out.println("The assignment name you entered is not valid");
                }
                
            }
            else if (subCommandPrint.equals("student") || subCommandPrint.equals("s")) {
                if (parsedCommand.size() > 2) {
                    String username = parsedCommand.get(2);
                    try {
                        System.out.print(gradebook.outputStudentGrades(username));
                    }
                    catch (RuntimeException ex) {
                        System.out.println("The student name you entered is not valid");
                    }
                }
                else {
                    System.out.println("You must enter a a student name");
                }
            }
            else {
                System.out.println(parsedCommand.get(1) + " is not a valid subcommand for gb print." 
                        + "Please enter a valid command to continue. For a list of valid commands, "
                        + "type \"gb help -print\" into the console");
            }
        }
    }
    
//    private void remove(ArrayList<String> parsedCommand) {
//        
//    }
//    
//    private void update(ArrayList<String> parsedCommand) {
//        
//    }
}
