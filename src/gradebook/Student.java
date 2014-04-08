package gradebook;

/**Class Student
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version 4-4-14
 *
 */
class Student {
    //FIELDS - basic information about a student
    private String id;
    private String first;
    private String last;
    private String advisor;
    private int year;
    
    
    /** Constructor Student
     * 
     * @param id - the student's username
     * @param first - first name of the student
     * @param last - last name of the student
     * @param advisor - the name of the student's advisor
     * @param year - the year the student is set to graduate
     */
    Student(String id, String first, String last, String advisor, int year) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.advisor = advisor;
        this.year = year;
    }
    
    public String toString() {
        return this.id + "\t"
                + this.first + "\t" 
                + this.last + "\t" 
                + this.advisor + "\t" 
                + this.year + "\n";
    }
    
    void addStudentGrade(Assignment assignment, Double grade) {
        assignment.addAssignmentGrade(this.id, grade);
    }
    
    /**
     * Gets the username of this student.
     * 
     * @return String The username of this student is returned.
     */
    String getStudentUsername() {
        return this.id;
    }
}
