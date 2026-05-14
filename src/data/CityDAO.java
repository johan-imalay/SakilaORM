package data;

import models.City;
import models.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla city.
 * Hijo de DataContext, no puede ser extendido.
///Johan Manuel Feliz Montero 100146608
 */
public final class CityDAO extends DataContext<City> {

    private final CountryDAO countryDAO = new CountryDAO();

    @Override
    public boolean post(City city) {
        String sql = "INSERT INTO city (city, country_id) VALUES (?, ?)";
        return executeUpdate(sql,
                city.getCity(),
                city.getCountry().getCountryId());
    }

    @Override
    public boolean put(City city) {
        String sql = "UPDATE city SET city=?, country_id=? WHERE city_id=?";
        return executeUpdate(sql,
                city.getCity(),
                city.getCountry().getCountryId(),
                city.getCityId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM city WHERE city_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public City get(int id) {
        String sql = "SELECT * FROM city WHERE city_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapCity(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<City> getAll() {
        String sql = "SELECT * FROM city";
        List<City> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapCity(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private City mapCity(ResultSet rs) throws SQLException {
        Country country = countryDAO.get(rs.getInt("country_id"));
        return new City(
                rs.getInt("city_id"),
                rs.getString("city"),
                country
        );
    }
}