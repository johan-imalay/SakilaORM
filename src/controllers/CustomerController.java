package controllers;

import data.Validator;
import data.CustomerDAO;
import models.Customer;
import java.util.List;

/**
 * Controlador MVC para gestionar los clientes.
///Johan Manuel Feliz Montero 100146608
 */
public class CustomerController {

    private final CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAO();
    }

    /**
     * Retorna todos los clientes.
     * @return Lista de clientes
     */
    public List<Customer> listarTodos() {
        return customerDAO.getAll();
    }

    /**
     * Busca un cliente por ID.
     * @param id ID del cliente
     * @return objeto Customer o null
     */
    public Customer buscarPorId(int id) {
        return customerDAO.get(id);
    }

    /**
     * Crea un nuevo cliente.
     * @param storeId   ID de la tienda
     * @param firstName nombre
     * @param lastName  apellido
     * @param email     correo electrónico
     * @param active    estado activo
     * @return true si se creó correctamente
     */
    public boolean crear(int storeId, String firstName, String lastName,
                         String email, boolean active) {
        if (!Validator.validarNombre(firstName)) {
            Validator.mostrarError("Nombre", "solo letras, mínimo 2 caracteres");
            return false;
        }
        if (!Validator.validarNombre(lastName)) {
            Validator.mostrarError("Apellido", "solo letras, mínimo 2 caracteres");
            return false;
        }
        if (!Validator.validarEmail(email)) {
            Validator.mostrarError("Email", "usuario@dominio.com");
            return false;
        }
        Customer customer = new Customer(0, storeId, firstName,
                lastName, email, active);
        return customerDAO.post(customer);
    }

    /**
     * Actualiza un cliente existente.
     * @param id        ID del cliente
     * @param firstName nuevo nombre
     * @param lastName  nuevo apellido
     * @param email     nuevo email
     * @param active    nuevo estado
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(int id, String firstName, String lastName,
                              String email, boolean active) {
        Customer customer = customerDAO.get(id);
        if (customer == null) {
            System.out.println("✘ Cliente no encontrado.");
            return false;
        }
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setActive(active);
        return customerDAO.put(customer);
    }

    /**
     * Elimina un cliente por ID.
     * @param id ID del cliente
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Customer customer = customerDAO.get(id);
        if (customer == null) {
            System.out.println("✘ Cliente no encontrado.");
            return false;
        }
        return customerDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        customerDAO.closeConnection();
    }
}