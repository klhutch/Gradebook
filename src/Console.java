import gradebook.MyGradeBook;

import java.util.ArrayList;
import java.util.Scanner;


public class Console {
    
    private static final Console INSTANCE = new Console();
    private MyGradeBook gradebook;
    
    private Console() {
        gradebook = MyGradeBook.initialize();
    }
    
    public static Console getInstance() {
        return INSTANCE;
    }
    
    /************PROTECTED FUNCTIONS TO COMMUNICATE WITH USER CLASS***********/
    protected boolean login(Scanner input) {
        System.out.println("Welcome to the NuForcer© Gradebook Console:");
        System.out.println("Please enter your teacher ID to continue:");
        String teacherId = input.next();
        if (teacherId != null) {
            System.out.println("Authentication Successful!");
            return true;
        }
        System.out.println("Sorry, you're ID is invalid");
        return false;
    }
    
    protected boolean getCommand(Scanner input) {
        System.out.print("$$$$~ ");
        String command = input.nextLine();
        ArrayList<String> parsedCommand = this.parseCommand(command);
        if (!(parsedCommand.isEmpty())) {
            switch(parsedCommand.get(0)) {
            case "gb add":
                this.add(parsedCommand);
                return false;
            case "gb assign":
                this.assign(parsedCommand);
                return false;
            case "gb calc":
                this.calc(parsedCommand);
                return false;
            case "gb print":
                this.print(parsedCommand);
                return false;
            case "gb output":
                this.output(parsedCommand);
                return false;
//            case "gb remove":
//                this.remove(parsedCommand);
//                return false;
//            case "gb update":
//                this.update(parsedCommand);
//                return false;
            case "gb help":
                this.help(parsedCommand);
                return false;
            case "gb quit":
                return true;
            default:
                System.out.println(parsedCommand.get(0) + 
                        " is not a valid command. Please enter a valid command "
                        + "to continue. For a list of valid commands, "
                        + "type \"gb help\" into the console");
                return false;
            }
        }
        else {
            System.out.println("Please enter a valid command "
                        + "to continue. For a list of valid commands, "
                        + "type \"gb help\" into the console");
            return false;
        }
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
                parsedString.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        parsedString.add(sb.toString());
        return parsedString;
    }
    
    /******************PRIVATE METHODS EXECUTED FROM CONSOLE******************/ 
    private void add(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 1) {
            switch(parsedCommand.get(1)) {
            case "assignment":
            case "a":
//                this.gradebook.addAssignment();
                System.out.println("Added assignment");
                break;
            case "student":
            case "s":
//                this.gradebook.addStudent();
                System.out.println("Added student");
                break;
            default:
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
            String assignment = parsedCommand.get(1);
            String studentId = parsedCommand.get(2);
            Double grade = Double.parseDouble(parsedCommand.get(3));
        }
        else {
            System.out.println("gb assign must take an assignment, studentId, and grade. Please enter the correct parameters. Type \"gb help -assign\" for more details");
        }
    }
    
    private void calc(ArrayList<String> parsedCommand) {
        switch (parsedCommand.get(1)) {
        case "assignment":
        case "a":
            String assignmentName = parsedCommand.get(2);
            break;
        case "student":
        case "s":
            String studentName = parsedCommand.get(2);
            break;
        default:
            System.out.println("The first parameter of gb calc must specify a an assignment or a student. Type \"gb help -calc\" for more details");
        }
        
//        switch (parsedCommand.get(1)) {
//        case "mean":
//            break;
//        case "median":
//            break;
//        case "min":
//            break;
//        case "max":
//            break;
//        case "range":
//            break;
//        default:
//            System.out.println();
//        }
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
        if (parsedCommand.size() >= 2) {
            switch (parsedCommand.get(1)) {
            case "add":
                System.out.println("Adds a student or assignment to the gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Add a student");
                System.out.println("-assignment (or -a): Add an assignment");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb add -s (StudentId) (StudentFirstName) (StudentLastName) (Advisor) (Year)");
                System.out.println("IF ASSIGNMENT: gb add -a");
                break;
            case "assign":
                System.out.println("Assign a grade to a student on a particular assignment");
                System.out.println("FORMAT: gb assign (assignmentName) (StudentId) (grade)");
                break;
            case "calc":
                System.out.println("Calculate stats for a student or an assignment");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Calculate stats for a student");
                System.out.println("-assignment (or -a): Calculate stats for an assignment");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb calc -(studentId)");
                System.out.println("IF ASSIGNMENT: gb calc -(assignmentName)");
                break;
            case "help":
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
                break;
            case "output":
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
                break;
            case "print":
                System.out.println("Prints out data for a student, assignment, or a gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): prints student data to the console");
                System.out.println("-assignment (or -a): prints assignment data to the console");
                System.out.println("-gradebook (or -g): print gradebook data to the console");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb print -s -(studentId)");
                System.out.println("IF ASSIGNMENT: gb print -a -(assignmentName)");
                System.out.println("IF GRADEBOOK: gb print -g");
                break;
            case "quit":
                System.out.println("Quits the program");
                System.out.println("FLAGS:");
                System.out.println("(none)");
                System.out.println("FORMAT:");
                System.out.println("gb quit");
                break;
            case "remove":
                System.out.println("Remove a student or assignment from the gradebook");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): Remove student from the gradebook");
                System.out.println("-assignment (or -a): Remove assignment from the gradebook");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb remove -s -(studentId)");
                System.out.println("IF ASSIGNMENT: gb remove -a -(assignmentName)");
                break;
            case "update":
                System.out.println("Updates information about a student, assignment, or a gradebook.");
                System.out.println("FLAGS:");
                System.out.println("-student (or -s): prints student data to a file");
                System.out.println("-assignment (or -a): prints assignment data to a file");
                System.out.println("-gradebook (or -g): print gradebook data to a file");
                System.out.println("FORMAT:");
                System.out.println("IF STUDENT: gb update -s -(studentId)");
                System.out.println("IF ASSIGNMENT: gb output -a -(assignmentName)");
                System.out.println("IF GRADEBOOK: gb output -g");
                break;
            default:
                System.out.println(parsedCommand.get(1) + " is not a valid sub-command for gb help. "
                        + "Please enter a valid command to continue. For a list of valid commands, "
                        + "type \"gb help -help\" into the console");
            }
        }
    }
    
    private void output(ArrayList<String> parsedCommand) {
        String fileName = parsedCommand.get(1);
        if (parsedCommand.size() == 2) {
            //output entire gradebook to file
        }
        switch (parsedCommand.get(2)) {
            case "assignment":
            case "a":
                String assignmentName = parsedCommand.get(3);
                break;
            case "student":
            case "s":
                String studentName = parsedCommand.get(3);
                break;
            default:
                System.out.println(parsedCommand.get(2) + " is not a valid subcommand for gb output." 
                        + "Please enter a valid command to continue. For a list of valid commands, "
                        + "type \"gb help -output\" into the console");
        }
    }

    private void print(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() == 1) {
            //print entire gradebook
        }
        switch (parsedCommand.get(1)) {
        case "assignment":
        case "a":
            String assignmentName = parsedCommand.get(2);
            break;
        case "student":
        case "s":
            String studentName = parsedCommand.get(2);
            break;
        default:
            System.out.println(parsedCommand.get(2) + " is not a valid subcommand for gb print." 
                    + "Please enter a valid command to continue. For a list of valid commands, "
                    + "type \"gb help -print\" into the console");
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
