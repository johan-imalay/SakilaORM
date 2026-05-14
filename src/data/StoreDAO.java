package data;

import models.Address;
import models.Store;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla store.
 * Hijo de DataContext, no puede ser extendido.
///Johan Manuel Feliz Montero 100146608
 */
public final class StoreDAO extends DataContext<Store> {

    private final AddressDAO addressDAO = new AddressDAO();

    @Override
    public boolean post(Store store) {
        String sql = "INSERT INTO store (address_id) VALUES (?)";
        return executeUpdate(sql,
                store.getAddress().getAddressId());
    }

    @Override
    public boolean put(Store store) {
        String sql = "UPDATE store SET address_id=? WHERE store_id=?";
        return executeUpdate(sql,
                store.getAddress().getAddressId(),
                store.getStoreId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM store WHERE store_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Store get(int id) {
        String sql = "SELECT * FROM store WHERE store_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapStore(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Store> getAll() {
        String sql = "SELECT * FROM store";
        List<Store> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapStore(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Store mapStore(ResultSet rs) throws SQLException {
        Address address = addressDAO.get(rs.getInt("address_id"));
        return new Store(
                rs.getInt("store_id"),
                address
        );
    }
}