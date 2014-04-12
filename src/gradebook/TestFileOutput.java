package gradebook;

/**Class TestFileOutput
* Team 10
* 
* @author Kate Hutchinson (klhutch)
* @author Jesse Oberstein (joberste)
* @author Nathan Goodman (nmg49)
* 
* @version April 11th, 2014
*
*/
public class TestFileOutput {
    /**
     * main method - runs the program
     * @param args arguments to be used in main NOT USED
     */
    public static void main(String[] args) {
        MyGradeBook gb = MyGradeBook.initializeWithFile("gradebook.txt");
        gb.fileOutputCurrentGrades("testOutputCurGrades1.txt");
        
        MyGradeBook init = MyGradeBook.initializeWithFile("initial.txt");
        init.fileOutputCurrentGrades("testOutputCurGrades2.txt");
        
        System.out.println(init.outputCurrentGrades());
    }
}
