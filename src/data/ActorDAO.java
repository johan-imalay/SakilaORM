package data;

import models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla actor.
 * Hijo de DataContext, no puede ser extendido.

 */
public final class ActorDAO extends DataContext<Actor> {

    /**
     * Inserta un nuevo actor en la base de datos.
     * @param actor objeto Actor a insertar
     * @return true si se insertó correctamente
     */
    @Override
    public boolean post(Actor actor) {
        String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        return executeUpdate(sql, actor.getFirstName(), actor.getLastName());
    }

    /**
     * Actualiza un actor existente.
     * @param actor objeto Actor con los datos actualizados
     * @return true si se actualizó correctamente
     */
    @Override
    public boolean put(Actor actor) {
        String sql = "UPDATE actor SET first_name=?, last_name=? WHERE actor_id=?";
        return executeUpdate(sql, actor.getFirstName(), actor.getLastName(), actor.getActorId());
    }

    /**
     * Elimina un actor por su ID.
     * @param id ID del actor a eliminar
     * @return true si se eliminó correctamente
     */
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM actor WHERE actor_id=?";
        return executeUpdate(sql, id);
    }

    /**
     * Busca un actor por su ID.
     * @param id ID del actor
     * @return objeto Actor o null si no existe
     */
    @Override
    public Actor get(int id) {
        String sql = "SELECT * FROM actor WHERE actor_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("✘ Error en get: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna todos los actores de la base de datos.
     * @return Lista de actores
     */
    @Override
    public List<Actor> getAll() {
        String sql = "SELECT * FROM actor";
        List<Actor> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("✘ Error en getAll: " + e.getMessage());
        }
        return lista;
    }
}