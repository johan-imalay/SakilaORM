package data;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla payment.
 * Hijo de DataContext, no puede ser extendido.
 */
public final class PaymentDAO extends DataContext<Payment> {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final StaffDAO    staffDAO    = new StaffDAO();
    private final RentalDAO   rentalDAO   = new RentalDAO();

    @Override
    public boolean post(Payment payment) {
        String sql = "INSERT INTO payment (customer_id, staff_id, " +
                "rental_id, amount, payment_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                payment.getCustomer().getCustomerId(),
                payment.getStaff().getStaffId(),
                payment.getRental().getRentalId(),
                payment.getAmount(),
                Timestamp.valueOf(payment.getPaymentDate()));
    }

    @Override
    public boolean put(Payment payment) {
        String sql = "UPDATE payment SET customer_id=?, staff_id=?, " +
                "rental_id=?, amount=?, payment_date=? " +
                "WHERE payment_id=?";
        return executeUpdate(sql,
                payment.getCustomer().getCustomerId(),
                payment.getStaff().getStaffId(),
                payment.getRental().getRentalId(),
                payment.getAmount(),
                Timestamp.valueOf(payment.getPaymentDate()),
                payment.getPaymentId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM payment WHERE payment_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Payment get(int id) {
        String sql = "SELECT * FROM payment WHERE payment_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapPayment(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> getAll() {
        String sql = "SELECT * FROM payment";
        List<Payment> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca pagos por cliente.
     * @param customerId ID del cliente
     * @return Lista de pagos del cliente
     */
    public List<Payment> getByCustomer(int customerId) {
        String sql = "SELECT * FROM payment WHERE customer_id=?";
        List<Payment> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql, customerId)) {
            while (rs != null && rs.next()) {
                lista.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getByCustomer: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca pagos por tienda.
     * @param storeId ID de la tienda
     * @return Lista de pagos de la tienda
     */
    public List<Payment> getByStore(int storeId) {
        String sql = "SELECT p.* FROM payment p " +
                "JOIN staff s ON p.staff_id = s.staff_id " +
                "WHERE s.store_id=?";
        List<Payment> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql, storeId)) {
            while (rs != null && rs.next()) {
                lista.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getByStore: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Total de pagos por cliente.
     * @param customerId ID del cliente
     * @return Total pagado
     */
    public double totalPorCliente(int customerId) {
        List<Payment> pagos = getByCustomer(customerId);
        return pagos.stream()
                .mapToDouble(p -> p.getAmount().doubleValue())
                .sum();
    }

    private Payment mapPayment(ResultSet rs) throws SQLException {
        Customer customer = customerDAO.get(rs.getInt("customer_id"));
        Staff    staff    = staffDAO.get(rs.getInt("staff_id"));
        Rental   rental   = rentalDAO.get(rs.getInt("rental_id"));
        return new Payment(
                rs.getInt("payment_id"),
                customer,
                staff,
                rental,
                rs.getBigDecimal("amount"),
                rs.getTimestamp("payment_date").toLocalDateTime()
        );
    }
}