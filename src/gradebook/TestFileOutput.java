package gradebook;

public class TestFileOutput {
    public static void main(String[] args) {
        MyGradeBook gb = MyGradeBook.initializeWithFile("gradebook.txt");
        System.out.println(gb.fileOutputCurrentGrades("testOutputCurGrades1.txt"));
        
    }
}
