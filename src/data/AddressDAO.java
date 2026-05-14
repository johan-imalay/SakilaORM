package data;

import models.Address;
import models.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla address.
 * Hijo de DataContext, no puede ser extendido.
 
 */
public final class AddressDAO extends DataContext<Address> {

    private final CityDAO cityDAO = new CityDAO();

    @Override
    public boolean post(Address address) {
        String sql = "INSERT INTO address (address, address2, district, " +
                "city_id, postal_code, phone) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                address.getCity().getCityId(),
                address.getPostalCode(),
                address.getPhone());
    }

    @Override
    public boolean put(Address address) {
        String sql = "UPDATE address SET address=?, address2=?, district=?, " +
                "city_id=?, postal_code=?, phone=? WHERE address_id=?";
        return executeUpdate(sql,
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                address.getCity().getCityId(),
                address.getPostalCode(),
                address.getPhone(),
                address.getAddressId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM address WHERE address_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Address get(int id) {
        String sql = "SELECT * FROM address WHERE address_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapAddress(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Address> getAll() {
        String sql = "SELECT * FROM address";
        List<Address> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapAddress(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Address mapAddress(ResultSet rs) throws SQLException {
        City city = cityDAO.get(rs.getInt("city_id"));
        return new Address(
                rs.getInt("address_id"),
                rs.getString("address"),
                rs.getString("address2"),
                rs.getString("district"),
                city,
                rs.getString("postal_code"),
                rs.getString("phone")
        );
    }
}