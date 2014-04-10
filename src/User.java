import java.util.Scanner;



/**Class User
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
public class User {
    
    /** Main method
     * used in running a program, determines what happens in
     * program execution and calls other functions
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Console console = Console.getInstance();
        boolean login = console.login(input);
        input.nextLine(); //throw away empty space
        boolean quit = false;
        if (login) {
        System.out.println("Enter a command to begin "
                + "(type \"gb help\" for a list of commands)");
            while (!quit) {
                System.out.print("$$$$~ ");
                quit = console.getCommand(input);
            }
        }
        input.close();
    }
}
