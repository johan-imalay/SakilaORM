package data;

import models.Film;
import models.Inventory;
import models.Store;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto y final para la tabla inventory.
 * Hijo de DataContext, no puede ser extendido.
 
 */
public final class InventoryDAO extends DataContext<Inventory> {

    private final FilmDAO  filmDAO  = new FilmDAO();
    private final StoreDAO storeDAO = new StoreDAO();

    @Override
    public boolean post(Inventory inventory) {
        String sql = "INSERT INTO inventory (film_id, store_id) VALUES (?, ?)";
        return executeUpdate(sql,
                inventory.getFilm().getFilmId(),
                inventory.getStore().getStoreId());
    }

    @Override
    public boolean put(Inventory inventory) {
        String sql = "UPDATE inventory SET film_id=?, store_id=? " +
                "WHERE inventory_id=?";
        return executeUpdate(sql,
                inventory.getFilm().getFilmId(),
                inventory.getStore().getStoreId(),
                inventory.getInventoryId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM inventory WHERE inventory_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Inventory get(int id) {
        String sql = "SELECT * FROM inventory WHERE inventory_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapInventory(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Inventory> getAll() {
        String sql = "SELECT * FROM inventory";
        List<Inventory> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapInventory(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca inventario por tienda.
     * @param storeId ID de la tienda
     * @return Lista de inventario de la tienda
     */
    public List<Inventory> getByStore(int storeId) {
        String sql = "SELECT * FROM inventory WHERE store_id=?";
        List<Inventory> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql, storeId)) {
            while (rs != null && rs.next()) {
                lista.add(mapInventory(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getByStore: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca inventario por película.
     * @param filmId ID de la película
     * @return Lista de inventario de la película
     */
    public List<Inventory> getByFilm(int filmId) {
        String sql = "SELECT * FROM inventory WHERE film_id=?";
        List<Inventory> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql, filmId)) {
            while (rs != null && rs.next()) {
                lista.add(mapInventory(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getByFilm: " + e.getMessage());
        }
        return lista;
    }

    private Inventory mapInventory(ResultSet rs) throws SQLException {
        Film  film  = filmDAO.get(rs.getInt("film_id"));
        Store store = storeDAO.get(rs.getInt("store_id"));
        return new Inventory(
                rs.getInt("inventory_id"),
                film,
                store
        );
    }
}