package controllers;

import data.FilmDAO;
import data.InventoryDAO;
import data.StoreDAO;
import models.Film;
import models.Inventory;
import models.Store;
import java.util.List;

/**
 * Controlador MVC para gestionar el inventario.

 */
public class InventoryController {

    private final InventoryDAO inventoryDAO = new InventoryDAO();
    private final FilmDAO      filmDAO      = new FilmDAO();
    private final StoreDAO     storeDAO     = new StoreDAO();

    /**
     * Retorna todo el inventario.
     * @return Lista de inventario
     */
    public List<Inventory> listarTodos() {
        return inventoryDAO.getAll();
    }

    /**
     * Busca un inventario por ID.
     * @param id ID del inventario
     * @return objeto Inventory o null
     */
    public Inventory buscarPorId(int id) {
        return inventoryDAO.get(id);
    }

    /**
     * Busca inventario por tienda.
     * @param storeId ID de la tienda
     * @return Lista de inventario de la tienda
     */
    public List<Inventory> buscarPorTienda(int storeId) {
        return inventoryDAO.getByStore(storeId);
    }

    /**
     * Busca inventario por película.
     * @param filmId ID de la película
     * @return Lista de inventario de la película
     */
    public List<Inventory> buscarPorFilm(int filmId) {
        return inventoryDAO.getByFilm(filmId);
    }

    /**
     * Crea un nuevo registro de inventario.
     * @param filmId  ID de la película
     * @param storeId ID de la tienda
     * @return true si se creó correctamente
     */
    public boolean crear(int filmId, int storeId) {
        Film film = filmDAO.get(filmId);
        if (film == null) {
            System.out.println("✘ Película no encontrada.");
            return false;
        }
        Store store = storeDAO.get(storeId);
        if (store == null) {
            System.out.println("✘ Tienda no encontrada.");
            return false;
        }
        Inventory inventory = new Inventory(0, film, store);
        return inventoryDAO.post(inventory);
    }

    /**
     * Actualiza un registro de inventario.
     * @param id      ID del inventario
     * @param filmId  nuevo ID de película
     * @param storeId nuevo ID de tienda
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(int id, int filmId, int storeId) {
        Inventory inventory = inventoryDAO.get(id);
        if (inventory == null) {
            System.out.println("✘ Inventario no encontrado.");
            return false;
        }
        Film film = filmDAO.get(filmId);
        if (film == null) {
            System.out.println("✘ Película no encontrada.");
            return false;
        }
        Store store = storeDAO.get(storeId);
        if (store == null) {
            System.out.println("✘ Tienda no encontrada.");
            return false;
        }
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventoryDAO.put(inventory);
    }

    /**
     * Elimina un registro de inventario por ID.
     * @param id ID del inventario
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Inventory inventory = inventoryDAO.get(id);
        if (inventory == null) {
            System.out.println("✘ Inventario no encontrado.");
            return false;
        }
        return inventoryDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        inventoryDAO.closeConnection();
        filmDAO.closeConnection();
        storeDAO.closeConnection();
    }
}