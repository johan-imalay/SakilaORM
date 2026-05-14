package data;

import models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class CustomerDAO extends DataContext<Customer> {

    @Override
    public boolean post(Customer customer) {
        String sql = "INSERT INTO customer (store_id, first_name, last_name, email, active) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                customer.getStoreId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.isActive() ? 1 : 0);
    }

    @Override
    public boolean put(Customer customer) {
        String sql = "UPDATE customer SET store_id=?, first_name=?, last_name=?, email=?, active=? WHERE customer_id=?";
        return executeUpdate(sql,
                customer.getStoreId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.isActive() ? 1 : 0,
                customer.getCustomerId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Customer get(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapCustomer(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getInt("store_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getInt("active") == 1
        );
    }
}