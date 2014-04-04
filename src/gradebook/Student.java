package gradebook;

import java.util.ArrayList;

public class Student {

    String firstName;
    String lastName;
    String studentId;
    Integer age;
    Boolean sex;
    Integer grade;
    ArrayList<Grade> grades;
    
    Student(String firstName, String lastName, String studentId, Integer age, Boolean sex, Integer grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.age = age;
        this.sex = sex;
        this.grade = grade;
    }
    
    void addGrade(Grade grade) {
        this.grades.add(grade);
    }
}
