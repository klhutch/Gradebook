/**Class User
 * Team 10
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
     * @param args - give args to main function
     */
    public static void main(String[] args) {
        Console console = Console.getInstance();
        boolean login = console.login();
        boolean quit = false;
        if (login) {
            System.out.println("Enter a command to begin "
                    + "(type \"gb help\" for a list of commands). "
                    + "Make sure to proceed each subcommand with a '-'.");
            while (!quit) {
                System.out.print("$$$$~ ");
                quit = console.getCommand();
            }
        }
    }
}
