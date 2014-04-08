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
    
    boolean login(Scanner input) {
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
    
    boolean getCommand(Scanner input) {
        System.out.print("$$$$~ ");
        String command = input.nextLine();
        ArrayList<String> parsedCommand = this.parseCommand(command);
        if (!(parsedCommand.isEmpty())) {
            switch(parsedCommand.get(0)) {
            case "gb add":
                this.add(parsedCommand);
                return false;
            case "gb assign":
                return false;
            case "gb calc":
                return false;
            case "gb print":
                return false;
            case "gb output":
                return false;
            case "gb remove":
                return false;
            case "gb update":
                return false;
            case "gb help":
                this.displayHelp();
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
    
    private void add(ArrayList<String> parsedCommand) {
        if (parsedCommand.size() > 1) {
            switch(parsedCommand.get(1)) {
            case "student":
            case "s":
//                this.gradebook.addStudent();
                System.out.println("Added student");
                break;
            case "assignment":
            case "a":
//                this.gradebook.addAssignment();
                System.out.println("Added assignment");
                break;
            case "gradebook":
//            case "g":
//                this.gradebook.addGradeBook
//                System.out.println("Added gradebook");
//                break;
//            }
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
    
    private void displayHelp() {
        System.out.println("gb add -s (student) -a (assignment) -g (gradebook))");
        System.out.println("gb assign -student -s (student) -a (assignment)");
        System.out.println("gb calc -student OR -assignment OR -gradebook "
                + "AND ONEOF(-mean, -median, -min, -max, -range)");
        System.out.println("gb print -student OR -assignment OR -gradebook");
        System.out.println("gb output - student OR -assignment OR -gradebook");
        System.out.println("gb remove -student OR -assignment OR -gradebook");
        System.out.println("gb update -student OR -assignment OR -gradebook");
        System.out.println("gb help");
        System.out.println("gb quit");
    }
}
