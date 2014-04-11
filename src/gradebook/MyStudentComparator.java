package gradebook;

import java.util.Comparator;

public class MyStudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentUsername().compareTo(o2.getStudentUsername());
    }
    
}
