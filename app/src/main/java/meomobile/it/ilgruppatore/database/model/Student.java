package meomobile.it.ilgruppatore.database.model;

/**
 * Creato da fabio on 05/04/2016.
 */
public class Student {
    String name;
    Float vote;

    public Student(String name, Float vote) {
        this.vote = vote;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getVote() {
        return vote;
    }
}
