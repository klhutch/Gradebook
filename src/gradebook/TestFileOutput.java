package gradebook;

public class TestFileOutput {
    public static void main(String[] args) {
        MyGradeBook gb = MyGradeBook.initializeWithFile("gradebook.txt");
        gb.fileOutputCurrentGrades("testOutputCurGrades1.txt");
        
        MyGradeBook init = MyGradeBook.initializeWithFile("initial.txt");
        init.fileOutputCurrentGrades("testOutputCurGrades2.txt");
        
    }
}
