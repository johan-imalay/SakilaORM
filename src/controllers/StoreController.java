package controllers;

import data.StoreDAO;
import data.AddressDAO;
import models.Store;
import models.Address;
import java.util.List;

/**
 * Controlador MVC para gestionar las tiendas.

 */
public class StoreController {

    private final StoreDAO   storeDAO   = new StoreDAO();
    private final AddressDAO addressDAO = new AddressDAO();

    /**
     * Retorna todas las tiendas.
     * @return Lista de tiendas
     */
    public List<Store> listarTodos() {
        return storeDAO.getAll();
    }

    /**
     * Busca una tienda por ID.
     * @param id ID de la tienda
     * @return objeto Store o null
     */
    public Store buscarPorId(int id) {
        return storeDAO.get(id);
    }

    /**
     * Crea una nueva tienda.
     * @param addressId ID de la dirección
     * @return true si se creó correctamente
     */
    public boolean crear(int addressId) {
        Address address = addressDAO.get(addressId);
        if (address == null) {
            System.out.println("✘ Dirección no encontrada.");
            return false;
        }
        Store store = new Store(0, address);
        return storeDAO.post(store);
    }

    /**
     * Actualiza una tienda existente.
     * @param id        ID de la tienda
     * @param addressId nuevo ID de dirección
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(int id, int addressId) {
        Store store = storeDAO.get(id);
        if (store == null) {
            System.out.println("✘ Tienda no encontrada.");
            return false;
        }
        Address address = addressDAO.get(addressId);
        if (address == null) {
            System.out.println("✘ Dirección no encontrada.");
            return false;
        }
        store.setAddress(address);
        return storeDAO.put(store);
    }

    /**
     * Elimina una tienda por ID.
     * @param id ID de la tienda
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Store store = storeDAO.get(id);
        if (store == null) {
            System.out.println("✘ Tienda no encontrada.");
            return false;
        }
        return storeDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        storeDAO.closeConnection();
        addressDAO.closeConnection();
    }
}