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

    private String first;
    private String last;
    private String id;
    private String advisor;
    private Integer gradYear;
    
    Student(String first, String last, String id,String advisor,
            Integer gradYear) {
        this.first = first;
        this.last = last;
        this.id = id;
        this.advisor = advisor;
    }
    
    String getUsername() {
        return id;
    }
    
    
}
