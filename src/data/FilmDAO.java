package data;

import models.Film;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla film.
 * Hijo de DataContext, no puede ser extendido.
///Johan Manuel Feliz Montero 100146608
 */
public final class FilmDAO extends DataContext<Film> {

    /**
     * Inserta una nueva película en la base de datos.
     * @param film objeto Film a insertar
     * @return true si se insertó correctamente
     */
    @Override
    public boolean post(Film film) {
        String sql = "INSERT INTO film (title, description, release_year, " +
                "language_id, rental_duration, rental_rate, " +
                "length, replacement_cost, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating());
    }

    /**
     * Actualiza una película existente.
     * @param film objeto Film con los datos actualizados
     * @return true si se actualizó correctamente
     */
    @Override
    public boolean put(Film film) {
        String sql = "UPDATE film SET title=?, description=?, release_year=?, " +
                "language_id=?, rental_duration=?, rental_rate=?, " +
                "length=?, replacement_cost=?, rating=? " +
                "WHERE film_id=?";
        return executeUpdate(sql,
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getFilmId());
    }

    /**
     * Elimina una película por su ID.
     * @param id ID de la película a eliminar
     * @return true si se eliminó correctamente
     */
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM film WHERE film_id=?";
        return executeUpdate(sql, id);
    }

    /**
     * Busca una película por su ID.
     * @param id ID de la película
     * @return objeto Film o null si no existe
     */
    @Override
    public Film get(int id) {
        String sql = "SELECT * FROM film WHERE film_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapFilm(rs);
            }
        } catch (SQLException e) {
            System.err.println("✘ Error en get: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna todas las películas de la base de datos.
     * @return Lista de películas
     */
    @Override
    public List<Film> getAll() {
        String sql = "SELECT * FROM film";
        List<Film> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapFilm(rs));
            }
        } catch (SQLException e) {
            System.err.println("✘ Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Convierte un ResultSet en un objeto Film.
     * @param rs ResultSet con los datos
     * @return objeto Film
     */
    private Film mapFilm(ResultSet rs) throws SQLException {
        return new Film(
                rs.getInt("film_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("release_year"),
                rs.getInt("language_id"),
                rs.getInt("rental_duration"),
                rs.getBigDecimal("rental_rate"),
                rs.getInt("length"),
                rs.getBigDecimal("replacement_cost"),
                rs.getString("rating")
        );
    }
}