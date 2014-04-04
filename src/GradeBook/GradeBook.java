package GradeBook;

import java.util.ArrayList;

public class GradeBook {

    final String teacherId;
    final ArrayList<String> studentIds;
    final String courseName;
    final String courseId;
    final ArrayList<Assignment> assignments;
    
    GradeBook() {
        this.teacherId = null;
        this.studentIds = null;
        this.courseName = null;
        this.courseId= null;
        this.assignments = null;
    }
}
