package data;

import models.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla country.
 * Hijo de DataContext, no puede ser extendido.

 */
public final class CountryDAO extends DataContext<Country> {

    @Override
    public boolean post(Country country) {
        String sql = "INSERT INTO country (country) VALUES (?)";
        return executeUpdate(sql, country.getCountry());
    }

    @Override
    public boolean put(Country country) {
        String sql = "UPDATE country SET country=? WHERE country_id=?";
        return executeUpdate(sql, country.getCountry(), country.getCountryId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM country WHERE country_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Country get(int id) {
        String sql = "SELECT * FROM country WHERE country_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapCountry(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Country> getAll() {
        String sql = "SELECT * FROM country";
        List<Country> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapCountry(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Country mapCountry(ResultSet rs) throws SQLException {
        return new Country(
                rs.getInt("country_id"),
                rs.getString("country")
        );
    }
}