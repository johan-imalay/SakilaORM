package models;

/**
 * Modelo que representa la tabla staff de Sakila.
 * Foreign Key: address_id → Address, store_id → Store
///Johan Manuel Feliz Montero 100146608
 */
public class Staff {

    private int     staffId;
    private String  firstName;
    private String  lastName;
    private Address address;  // FK como composición
    private String  email;
    private Store   store;    // FK como composición
    private boolean active;
    private String  username;

    public Staff() {}

    public Staff(int staffId, String firstName, String lastName,
                 Address address, String email, Store store,
                 boolean active, String username) {
        this.staffId   = staffId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.address   = address;
        this.email     = email;
        this.store     = store;
        this.active    = active;
        this.username  = username;
    }

    public int     getStaffId()               { return staffId; }
    public void    setStaffId(int id)         { this.staffId = id; }

    public String  getFirstName()             { return firstName; }
    public void    setFirstName(String name)  { this.firstName = name; }

    public String  getLastName()              { return lastName; }
    public void    setLastName(String name)   { this.lastName = name; }

    public Address getAddress()               { return address; }
    public void    setAddress(Address a)      { this.address = a; }

    public String  getEmail()                 { return email; }
    public void    setEmail(String email)     { this.email = email; }

    public Store   getStore()                 { return store; }
    public void    setStore(Store store)      { this.store = store; }

    public boolean isActive()                 { return active; }
    public void    setActive(boolean active)  { this.active = active; }

    public String  getUsername()              { return username; }
    public void    setUsername(String user)   { this.username = user; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s | Email: %s | Tienda: %s | Activo: %s",
                staffId, firstName, lastName, email,
                store != null ? store.getStoreId() : "N/A",
                active ? "Sí" : "No");
    }
}