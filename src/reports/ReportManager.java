package reports;

import data.*;
import models.*;
import java.io.*;
import java.util.*;

/**
 * Clase de reportes y estadísticas del sistema Sakila.
///Johan Manuel Feliz Montero 100146608
 */
public class ReportManager {

    private final ActorDAO    actorDAO    = new ActorDAO();
    private final FilmDAO     filmDAO     = new FilmDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final RentalDAO   rentalDAO   = new RentalDAO();

    // ── REPORTE 1: Listar actores y exportar CSV ───────────────

    /**
     * Exporta todos los actores a un archivo CSV.
     */
    public void exportarActoresCSV() {
        List<Actor> actores = actorDAO.getAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter("actores.csv"))) {
            pw.println("ID,Nombre,Apellido");
            for (Actor a : actores) {
                pw.printf("%d,%s,%s%n",
                        a.getActorId(), a.getFirstName(), a.getLastName());
            }
            System.out.println("✔ actores.csv exportado. (" + actores.size() + " registros)");
        } catch (IOException e) {
            System.err.println("✘ Error exportando CSV: " + e.getMessage());
        }
    }

    // ── REPORTE 2: Exportar películas a JSON ───────────────────

    /**
     * Exporta todas las películas a un archivo JSON.
     */
    public void exportarFilmsJSON() {
        List<Film> films = filmDAO.getAll();
        try (PrintWriter pw = new PrintWriter(new FileWriter("films.json"))) {
            pw.println("[");
            for (int i = 0; i < films.size(); i++) {
                Film f = films.get(i);
                pw.printf("  {\"id\":%d,\"title\":\"%s\",\"year\":%d,\"rating\":\"%s\",\"price\":%s}%s%n",
                        f.getFilmId(), f.getTitle(), f.getReleaseYear(),
                        f.getRating(), f.getRentalRate(),
                        i < films.size() - 1 ? "," : "");
            }
            pw.println("]");
            System.out.println("✔ films.json exportado. (" + films.size() + " registros)");
        } catch (IOException e) {
            System.err.println("✘ Error exportando JSON: " + e.getMessage());
        }
    }

    // ── REPORTE 3: Estadísticas de películas ──────────────────

    /**
     * Muestra estadísticas generales de las películas.
     */
    public void estadisticasFilms() {
        List<Film> films = filmDAO.getAll();
        int total = films.size();
        double promedioPrecio = films.stream()
                .mapToDouble(f -> f.getRentalRate().doubleValue())
                .average().orElse(0);
        double promedioDuracion = films.stream()
                .mapToInt(Film::getLength)
                .average().orElse(0);

        // Contar por rating usando HashMap
        HashMap<String, Integer> porRating = new HashMap<>();
        for (Film f : films) {
            porRating.merge(f.getRating(), 1, Integer::sum);
        }

        System.out.println("\n===== ESTADÍSTICAS DE PELÍCULAS =====");
        System.out.println("Total películas    : " + total);
        System.out.printf("Precio promedio    : $%.2f%n", promedioPrecio);
        System.out.printf("Duración promedio  : %.0f min%n", promedioDuracion);
        System.out.println("Por clasificación  :");
        porRating.forEach((k, v) -> System.out.println("   " + k + ": " + v));
    }

    // ── REPORTE 4: Estadísticas de rentas ─────────────────────

    /**
     * Muestra estadísticas generales de las rentas.
     */
    public void estadisticasRentas() {
        List<Rental> rentas = rentalDAO.getAll();
        int total = rentas.size();

        // Rentas por cliente usando HashMap
        HashMap<Integer, Integer> porCliente = new HashMap<>();
        for (Rental r : rentas) {
            porCliente.merge(r.getCustomerId(), 1, Integer::sum);
        }

        // Cliente con más rentas
        int clienteTop = porCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(0);
        int rentasTop = porCliente.getOrDefault(clienteTop, 0);

        // Rentas pendientes (sin fecha de devolución)
        long pendientes = rentas.stream()
                .filter(r -> r.getReturnDate() == null)
                .count();

        System.out.println("\n===== ESTADÍSTICAS DE RENTAS =====");
        System.out.println("Total rentas       : " + total);
        System.out.println("Rentas pendientes  : " + pendientes);
        System.out.println("Cliente más activo : ID " + clienteTop +
                " con " + rentasTop + " rentas");
    }

    // ── REPORTE 5: Estadísticas de clientes ───────────────────

    /**
     * Muestra estadísticas generales de los clientes.
     */
    public void estadisticasClientes() {
        List<Customer> customers = customerDAO.getAll();
        int total = customers.size();
        long activos = customers.stream().filter(Customer::isActive).count();
        long inactivos = total - activos;

        System.out.println("\n===== ESTADÍSTICAS DE CLIENTES =====");
        System.out.println("Total clientes     : " + total);
        System.out.println("Activos            : " + activos);
        System.out.println("Inactivos          : " + inactivos);
    }

    /**
     * Cierra todas las conexiones.
     */
    public void cerrar() {
        actorDAO.closeConnection();
        filmDAO.closeConnection();
        customerDAO.closeConnection();
        rentalDAO.closeConnection();
    }
}