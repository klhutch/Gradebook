package gradebook;

public abstract class Assignment {

    String name;
    Integer weight;
    Integer totalPoints;
    
    Assignment(String name, Integer weight, Integer totalPoints) {
        this.name = name;
        this.weight = weight;
        this.totalPoints = totalPoints;
    }
}