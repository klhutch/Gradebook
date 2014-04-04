package gradebook;

public class Grade {

    Assignment assignment;
    Double score;
    
    Grade(Assignment assignment, Double score) {
        this.assignment = assignment;
        this.score = score;
    }
}
