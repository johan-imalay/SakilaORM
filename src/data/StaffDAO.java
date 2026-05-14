package data;

import models.Address;
import models.Staff;
import models.Store;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla staff.
 * Hijo de DataContext, no puede ser extendido.

 */
public final class StaffDAO extends DataContext<Staff> {

    private final AddressDAO addressDAO = new AddressDAO();
    private final StoreDAO   storeDAO   = new StoreDAO();

    @Override
    public boolean post(Staff staff) {
        String sql = "INSERT INTO staff (first_name, last_name, address_id, " +
                "email, store_id, active, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                staff.getFirstName(),
                staff.getLastName(),
                staff.getAddress().getAddressId(),
                staff.getEmail(),
                staff.getStore().getStoreId(),
                staff.isActive() ? 1 : 0,
                staff.getUsername());
    }

    @Override
    public boolean put(Staff staff) {
        String sql = "UPDATE staff SET first_name=?, last_name=?, " +
                "address_id=?, email=?, store_id=?, " +
                "active=?, username=? WHERE staff_id=?";
        return executeUpdate(sql,
                staff.getFirstName(),
                staff.getLastName(),
                staff.getAddress().getAddressId(),
                staff.getEmail(),
                staff.getStore().getStoreId(),
                staff.isActive() ? 1 : 0,
                staff.getUsername(),
                staff.getStaffId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM staff WHERE staff_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Staff get(int id) {
        String sql = "SELECT * FROM staff WHERE staff_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapStaff(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Staff> getAll() {
        String sql = "SELECT * FROM staff";
        List<Staff> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapStaff(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Staff mapStaff(ResultSet rs) throws SQLException {
        Address address = addressDAO.get(rs.getInt("address_id"));
        Store   store   = storeDAO.get(rs.getInt("store_id"));
        return new Staff(
                rs.getInt("staff_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                address,
                rs.getString("email"),
                store,
                rs.getInt("active") == 1,
                rs.getString("username")
        );
    }
}