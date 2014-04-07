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
    
    ArrayList<Double> getListOfScores(Assignment assignment) {
//        ArrayList<Double> scores = new ArrayList<Double>();
//        for (Student s : this.students) {
//            for (Grade g : s.grades) {
//                if (g.assignment == assignment) {
//                    scores.add(g.getScore());
//                }
//            }
//        }
//        return scores;
        return null;
    }
    
    Double getAssignmentAverage(Assignment assignment) {
        return 0.0;
    }
    
    Double getAssignmentMedian(Assignment assignment) {
        return 0.0;
    }
    
    Double getAssignmentMax(Assignment assignment) {
        return 0.0;
    }
    
    Double getAssignmentMin(Assignment assignment) {
        return 0.0;
    }
}
