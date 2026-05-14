package controllers;

import data.CustomerDAO;
import data.PaymentDAO;
import data.RentalDAO;
import data.StaffDAO;
import models.Customer;
import models.Payment;
import models.Rental;
import models.Staff;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador MVC para gestionar los pagos.
///Johan Manuel Feliz Montero 100146608
 */
public class PaymentController {

    private final PaymentDAO  paymentDAO  = new PaymentDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final StaffDAO    staffDAO    = new StaffDAO();
    private final RentalDAO   rentalDAO   = new RentalDAO();

    /**
     * Retorna todos los pagos.
     * @return Lista de pagos
     */
    public List<Payment> listarTodos() {
        return paymentDAO.getAll();
    }

    /**
     * Busca un pago por ID.
     * @param id ID del pago
     * @return objeto Payment o null
     */
    public Payment buscarPorId(int id) {
        return paymentDAO.get(id);
    }

    /**
     * Busca pagos por cliente.
     * @param customerId ID del cliente
     * @return Lista de pagos del cliente
     */
    public List<Payment> buscarPorCliente(int customerId) {
        return paymentDAO.getByCustomer(customerId);
    }

    /**
     * Busca pagos por tienda.
     * @param storeId ID de la tienda
     * @return Lista de pagos de la tienda
     */
    public List<Payment> buscarPorTienda(int storeId) {
        return paymentDAO.getByStore(storeId);
    }

    /**
     * Registra un nuevo pago.
     * @param customerId ID del cliente
     * @param staffId    ID del empleado
     * @param rentalId   ID de la renta
     * @param amount     monto del pago
     * @return true si se registró correctamente
     */
    public boolean crear(int customerId, int staffId,
                         int rentalId, BigDecimal amount) {
        Customer customer = customerDAO.get(customerId);
        if (customer == null) {
            System.out.println("✘ Cliente no encontrado.");
            return false;
        }
        Staff staff = staffDAO.get(staffId);
        if (staff == null) {
            System.out.println("✘ Empleado no encontrado.");
            return false;
        }
        Rental rental = rentalDAO.get(rentalId);
        if (rental == null) {
            System.out.println("✘ Renta no encontrada.");
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("✘ El monto debe ser mayor a 0.");
            return false;
        }
        Payment payment = new Payment(0, customer, staff,
                rental, amount, LocalDateTime.now());
        return paymentDAO.post(payment);
    }

    /**
     * Retorna el total pagado por un cliente.
     * @param customerId ID del cliente
     * @return total pagado
     */
    public double totalPorCliente(int customerId) {
        return paymentDAO.totalPorCliente(customerId);
    }

    /**
     * Elimina un pago por ID.
     * @param id ID del pago
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Payment payment = paymentDAO.get(id);
        if (payment == null) {
            System.out.println("✘ Pago no encontrado.");
            return false;
        }
        return paymentDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        paymentDAO.closeConnection();
        customerDAO.closeConnection();
        staffDAO.closeConnection();
        rentalDAO.closeConnection();
    }
}