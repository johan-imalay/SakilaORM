package models;

/**
 * Modelo que representa la tabla inventory de Sakila.
 * Foreign Key: film_id → Film, store_id → Store (composición)
///Johan Manuel Feliz Montero 100146608
 */
public class Inventory {

    private int   inventoryId;
    private Film  film;   // FK como composición
    private Store store;  // FK como composición

    public Inventory() {}

    public Inventory(int inventoryId, Film film, Store store) {
        this.inventoryId = inventoryId;
        this.film        = film;
        this.store       = store;
    }

    public int   getInventoryId()           { return inventoryId; }
    public void  setInventoryId(int id)     { this.inventoryId = id; }

    public Film  getFilm()                  { return film; }
    public void  setFilm(Film film)         { this.film = film; }

    public Store getStore()                 { return store; }
    public void  setStore(Store store)      { this.store = store; }

    @Override
    public String toString() {
        return String.format("[%d] Película: %s | Tienda: %d",
                inventoryId,
                film  != null ? film.getTitle()       : "N/A",
                store != null ? store.getStoreId()    : 0);
    }
}