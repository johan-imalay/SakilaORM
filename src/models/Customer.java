package models;

/**
 * Modelo que representa la tabla customer de Sakila.

 */
public class Customer {

    private int customerId;
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;

    public Customer() {}

    public Customer(int customerId, int storeId, String firstName,
                    String lastName, String email, boolean active) {
        this.customerId = customerId;
        this.storeId    = storeId;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.active     = active;
    }

    public int getCustomerId()               { return customerId; }
    public void setCustomerId(int id)        { this.customerId = id; }

    public int getStoreId()                  { return storeId; }
    public void setStoreId(int id)           { this.storeId = id; }

    public String getFirstName()             { return firstName; }
    public void setFirstName(String name)    { this.firstName = name; }

    public String getLastName()              { return lastName; }
    public void setLastName(String name)     { this.lastName = name; }

    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }

    public boolean isActive()                { return active; }
    public void setActive(boolean active)    { this.active = active; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s | Email: %s | Activo: %s",
                customerId, firstName, lastName, email, active ? "Sí" : "No");
    }
}