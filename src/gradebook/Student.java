package gradebook;

/**Class Student
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 8th, 2014
 *
 */
class Student {
    /** This student's username */
    private String id;
    /** This student's first name */
    private String first;
    /** This student's last name */
    private String last;
    /** This student's advisor */
    private String advisor;
    /** This student's graduation year */
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
        return this.id + "\n" 
                + this.first + "\n" 
                + this.last + "\n" 
                + this.advisor + "\n" 
                + this.year + "\n";
    }
    
    void addStudentGrade(Assignment assignment, Double grade) {
        assignment.addAssignmentGrade(this.id, grade);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || ( !(obj instanceof Student))) {
            return false;
        }
        
        Student comp = (Student) obj;
        
        return (this.id.equals(comp.getStudentUsername()) && 
                this.first.equals(comp.getFirstName()) &&
                this.last.equals(comp.getLastName()) &&
                this.advisor.equals(comp.getAdvisor()) &&
                this.year == comp.getGradYear());
    }
    
    
    /**
     * Gets the username of this student.
     * @return String The username of this student is returned.
     */
    String getStudentUsername() {
        return this.id;
    }
    
    /**
     * Gets the first name of this student.
     * @return String The first name of this student is returned.
     */
    String getFirstName() {
        return this.first;
    }
    
    /**
     * Gets the last name of this student.
     * @return String The last name of this student is returned.
     */
    String getLastName() {
        return this.last;
    }
    
    /**
     * Gets the advisor of this student.
     * @return String The advisor of this student is returned.
     */
    String getAdvisor() {
        return this.advisor;
    }
    
    /**
     * Gets the gradutation year of this student.
     * @return int The graduation year of this student is returned.
     */
    int getGradYear() {
        return this.year;
    }
}
