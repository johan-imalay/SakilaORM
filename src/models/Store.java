package models;

/**
 * Modelo que representa la tabla store de Sakila.
 * Foreign Key: address_id → objeto Address (composición)

 */
public class Store {

    private int     storeId;
    private Address address;  // FK como composición

    public Store() {}

    public Store(int storeId, Address address) {
        this.storeId = storeId;
        this.address = address;
    }

    public int     getStoreId()             { return storeId; }
    public void    setStoreId(int id)       { this.storeId = id; }

    public Address getAddress()             { return address; }
    public void    setAddress(Address a)    { this.address = a; }

    @Override
    public String toString() {
        return String.format("[%d] Tienda | Dirección: %s",
                storeId, address != null ? address.getAddress() : "N/A");
    }
}