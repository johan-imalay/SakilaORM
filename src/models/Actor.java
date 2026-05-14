package models;

/**
 * Modelo que representa la tabla actor de Sakila.

 */
public class Actor {

    private int actorId;
    private String firstName;
    private String lastName;

    public Actor() {}

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId   = actorId;
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public int getActorId()               { return actorId; }
    public void setActorId(int id)        { this.actorId = id; }

    public String getFirstName()          { return firstName; }
    public void setFirstName(String name) { this.firstName = name; }

    public String getLastName()           { return lastName; }
    public void setLastName(String name)  { this.lastName = name; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s", actorId, firstName, lastName);
    }
}