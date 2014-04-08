
import java.util.Scanner;

// Test class for using the console!
class ReadConsoleSystem {
    String input;
    Thread t;

    ReadConsoleSystem() {
        this.input = "";
        this.t = new Thread();
    }

    public static void main(String[] args) {
        ReadConsoleSystem rcs = new ReadConsoleSystem();
        System.out.println("What is 2 + 2?");
        rcs.startConsole();
        rcs.mathQuestion1();
        System.out.println();
        System.out.println("But now, what is 4 * 3?");
        rcs.startConsole();
        rcs.mathQuestion2();
    }
    
    public void start() {
        String[] args = {};
        ReadConsoleSystem.main(args);
    }

    public void startConsole() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        this.input = s;
    }
    
    // To print user input to the console.
    public void printUserInput() {
        System.out.println("\t'" + this.input + "'");
    }
    
    public void mathQuestion1() {
        ReadConsoleSystem rcs = new ReadConsoleSystem();
        try {
            new Integer(this.input);
            new Double(this.input).intValue();
            new Float(this.input).intValue();
            
            if (new Integer(this.input) == 4) {
                System.out.println("Good Job!");
            }
            else {
                System.out.println("Dummy, it's 4!");
            }
        }
        catch (Exception e) {
            System.out.println("Please enter a number next time!");
            //rcs.startConsole();
            this.t.start();
        }
    }
    
    public void mathQuestion2() {
        ReadConsoleSystem rcs = new ReadConsoleSystem();
        try {
            new Integer(this.input);
            new Double(this.input).intValue();
            new Float(this.input).intValue();
            
            if (new Integer(this.input) == 12) {
                System.out.println("Wowzers!");
            }
            else {
                System.out.println("Dummy, it's 12!");
            }
        }
        catch (Exception e) {
            System.out.println("Please enter a number next time!");
            this.t.start();
        }
    }
}