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
    private String first;
    private String last;
    private String id;
    private String advisor;
    private Integer gradYear;
    
    
    /** Constructor Student
     * 
     * @param first - first name of the student
     * @param last - last name of the student
     * @param id - the student's username
     * @param advisor - the name of the student's advisor
     * @param gradYear - the year the student is set to graduate
     */
    Student(String first, String last, String id,String advisor,
            Integer gradYear) {
        this.first = first;
        this.last = last;
        this.id = id;
        this.advisor = advisor;
    }
    
    /** method getUseername
     * used to retrieve the username of one student
     * 
     * @return the value of the id field
     */
    String getUsername() {
        return id;
    }
    
    
}
