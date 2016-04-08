package meomobile.it.ilgruppatore.database.model;

/**
 * Creato da fabio on 05/04/2016.
 */
public class Result {
    String name;
    Float vote;

    public Result(String name) {
        this.vote = 0.0F;
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

    public void setVote(Float vote) {
        this.vote = vote;
    }
}
