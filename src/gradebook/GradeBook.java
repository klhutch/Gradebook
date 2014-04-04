package gradebook;

import java.util.ArrayList;

public class GradeBook {

    String courseName;
    String couresNumber;
    String teacherName;
    String teacherId;
    ArrayList<Student> students;
    ArrayList<Assignment> assignment;
    
    GradeBook(String courseName, String courseNumber, String teacherName, String teacherId) {
        this.couresNumber = courseName;
        this.couresNumber = courseNumber;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }
    
    void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }
    
    void addAssignment(Assignment newAssignment) {
        this.assignment.add(newAssignment);
    }
}
