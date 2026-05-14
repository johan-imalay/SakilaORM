package controllers;

import data.RentalDAO;
import models.Rental;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador MVC para gestionar las rentas.

 */
public class RentalController {

    private final RentalDAO rentalDAO;

    public RentalController() {
        this.rentalDAO = new RentalDAO();
    }

    /**
     * Retorna todas las rentas.
     * @return Lista de rentas
     */
    public List<Rental> listarTodos() {
        return rentalDAO.getAll();
    }

    /**
     * Busca una renta por ID.
     * @param id ID de la renta
     * @return objeto Rental o null
     */
    public Rental buscarPorId(int id) {
        return rentalDAO.get(id);
    }

    /**
     * Crea una nueva renta.
     * @param inventoryId ID del inventario
     * @param customerId  ID del cliente
     * @param staffId     ID del empleado
     * @return true si se creó correctamente
     */
    public boolean crear(int inventoryId, int customerId, int staffId) {
        if (inventoryId <= 0 || customerId <= 0 || staffId <= 0) {
            System.out.println("✘ IDs inválidos.");
            return false;
        }
        Rental rental = new Rental(0, LocalDateTime.now(),
                inventoryId, customerId, null, staffId);
        return rentalDAO.post(rental);
    }

    /**
     * Registra la devolución de una renta.
     * @param id ID de la renta
     * @return true si se actualizó correctamente
     */
    public boolean registrarDevolucion(int id) {
        Rental rental = rentalDAO.get(id);
        if (rental == null) {
            System.out.println("✘ Renta no encontrada.");
            return false;
        }
        if (rental.getReturnDate() != null) {
            System.out.println("✘ Esta renta ya fue devuelta.");
            return false;
        }
        rental.setReturnDate(LocalDateTime.now());
        return rentalDAO.put(rental);
    }

    /**
     * Elimina una renta por ID.
     * @param id ID de la renta
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Rental rental = rentalDAO.get(id);
        if (rental == null) {
            System.out.println("✘ Renta no encontrada.");
            return false;
        }
        return rentalDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        rentalDAO.closeConnection();
    }
}