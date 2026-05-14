package data;

import models.Rental;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class RentalDAO extends DataContext<Rental> {

    @Override
    public boolean post(Rental rental) {
        String sql = "INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                Timestamp.valueOf(rental.getRentalDate()),
                rental.getInventoryId(),
                rental.getCustomerId(),
                rental.getReturnDate() != null ? Timestamp.valueOf(rental.getReturnDate()) : null,
                rental.getStaffId());
    }

    @Override
    public boolean put(Rental rental) {
        String sql = "UPDATE rental SET rental_date=?, inventory_id=?, customer_id=?, return_date=?, staff_id=? WHERE rental_id=?";
        return executeUpdate(sql,
                Timestamp.valueOf(rental.getRentalDate()),
                rental.getInventoryId(),
                rental.getCustomerId(),
                rental.getReturnDate() != null ? Timestamp.valueOf(rental.getReturnDate()) : null,
                rental.getStaffId(),
                rental.getRentalId());
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM rental WHERE rental_id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public Rental get(int id) {
        String sql = "SELECT * FROM rental WHERE rental_id=?";
        try (ResultSet rs = executeQuery(sql, id)) {
            if (rs != null && rs.next()) {
                return mapRental(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en get: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rental> getAll() {
        String sql = "SELECT * FROM rental";
        List<Rental> lista = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql)) {
            while (rs != null && rs.next()) {
                lista.add(mapRental(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en getAll: " + e.getMessage());
        }
        return lista;
    }

    private Rental mapRental(ResultSet rs) throws SQLException {
        Timestamp returnTs = rs.getTimestamp("return_date");
        return new Rental(
                rs.getInt("rental_id"),
                rs.getTimestamp("rental_date").toLocalDateTime(),
                rs.getInt("inventory_id"),
                rs.getInt("customer_id"),
                returnTs != null ? returnTs.toLocalDateTime() : null,
                rs.getInt("staff_id")
        );
    }
}