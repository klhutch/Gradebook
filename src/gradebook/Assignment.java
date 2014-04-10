package gradebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**Class Assignment
 * 
 * @author Kate Hutchinson (klhutch)
 * @author Jesse Oberstein (joberste)
 * @author Nathan Goodman (nmg49)
 * 
 * @version April 8th, 2014
 *
 */
class Assignment {
    String name;
    Double totalPoints;
    Double weight;
    
    /**
     * Store a collection of each grade of the assignment. 
     * The String corresponds to the StudentID and the 
     * double corresponds to the number of points earned
     */
    HashMap<String, Double> grades;
    
    /** 
     * Constructor Assignment
     * 
     * @param name - the name of the Assignment
     * @param weight - the weight of the Assignment
     * @param totalPoints - the total number of points an assignement is worth
     */
    Assignment(String name, Double totalPoints, Double weight) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.weight = weight;
        this.grades = new HashMap<String, Double>();
    }
    
    
    /**
     * Adds a student's grade to this assignment by pairing his/her username
     * with his/her grade for this assignment.
     * 
     * @param username
     *            The username of a student, represented by a string.
     * @param grade
     *            The matching student's grade for this assignment.
     * @return HashMap<String, Double> A hashmap of student username and grade
     *         for this assignment.
     */
    public void addAssignmentGrade(String username, Double grade) {
        this.grades.put(username, grade);
    }
    
    /**
     * Gets the name of this assignment.
     * 
     * @return String The name of this assignment is returned.
     */
    String getAssignmentName() {
        return this.name;
    }

    /**
     * Gets the weight of this assignment.
     * 
     * @return String The name of this assignment is returned.
     */
    Double getWeight() {
        return this.weight;
    }
    
    /**
     * Gets the total points of this assignment.
     * 
     * @return String The name of this assignment is returned.
     */
    Double getTotal() {
        return this.totalPoints;
    }
    

    /**
     * Gets the grades for this assignment.
     * 
     * @return HashMap<String, Double> A map of student usernames and those
     *         students' grades is returned.
     */
    HashMap<String, Double> getGrades() {
        return this.grades;
    }
    
    
    
    
    
    public String toString() {
        return this.name + "\n" 
                + this.totalPoints + "\n" 
                + this.weight + "\n"
                + this.grades.toString() + "\n";
    }


    /**
     * Calculates the average across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the average across all students for assignmentName
     */
    double average() {
        double avg = 0;
        for (double grade : this.grades.values()) {
            avg = avg + grade;
        }
        return avg / this.grades.size();
    }
    
    /**
     * Sorts the grades from this assignment in ascending order.
     * 
     * @return List<Double> Returns a sorted list of grades.
     */
    List<Double> sortGrades() {
        List<Double> gradelist = new ArrayList<Double>();
        for (double grade : this.grades.values()) {
            gradelist.add(grade);
        }
        Collections.sort(gradelist);
        return gradelist;
    }
    
    /**
     * Calculates the median across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the median across all students for assignmentName
     */
    double median() {
        List<Double> gradelist = this.sortGrades();
        double ans = 0;
        int half = (gradelist.size() / 2);
            if (gradelist.size() % 2 == 0) {
                ans = (gradelist.get(half - 1) + gradelist.get(half)) / 2;
            }
            else {
                ans = gradelist.get(half - 1);
            }
            return ans;
    }

    /**
     * Calculates the min across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the min across all students for assignmentName
     */
    double min() {
        Iterator<Double> it = this.grades.values().iterator();
        double ans = it.next();
        while (it.hasNext()) {
            ans = Math.min(ans, it.next());
        }
        return ans;
    }

    /**
     * Calculates the max across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the max across all students for assignmentName
     */
    double max() {
        Iterator<Double> it = this.grades.values().iterator();
        double ans = it.next();
        while (it.hasNext()) {
            ans = Math.max(ans, it.next());
        }
        return ans;
    }
    
    /**
     * Provides the grade earned by the given student for the given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @return the grade earned by username for assignmentName
     */
    double assignmentGrade(String username) {
        return this.grades.get(username);
    }
    
    /**
     * Changes the assignment grade for student (whose username is equal
     * to username) to newGrade
     * 
     * @param username
     *            username for the student
     * @param newGrade
     *            the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *         assignment/student combination exists, returns false otherwise
     */
    boolean changeGrade(String username, double newGrade) {
        if (this.grades.containsKey(username)) {
            this.grades.put(username, newGrade);
            return true;
        }
        else {
            return false;
        }
    }
    
    /** 
     * method equals
     * to determine the equivalence of two objects, one of which is
     * an assignment
     * 
     * @param obj - the Object to compare 'this' to
     * @return boolean - "Is 'this' equivalent to obj?"
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || ( !(obj instanceof Assignment))) {
            return false;
        }
        
        Assignment comp = (Assignment) obj;
        return (this.name.equals(comp.name) &&
                this.weight.equals(comp.weight) &&
                this.grades.equals(comp.grades) && 
                this.totalPoints.equals(comp.totalPoints));
           
      
    }
    
    
    @Override
    public int hashCode() {
       return grades.hashCode() + name.hashCode() + 
               totalPoints.hashCode() + weight.hashCode();
    }
    
     
    
}