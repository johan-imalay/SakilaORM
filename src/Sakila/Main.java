package Sakila;

import data.*;
import models.*;
import java.util.List;

/**
 * Clase principal para probar el ORM de Sakila.

 *  */
public class Main {

    public static void main(String[] args) {

        // ── Probar ActorDAO ────────────────────────────────────
        System.out.println("===== ACTORES =====");
        ActorDAO actorDAO = new ActorDAO();

        // Obtener todos los actores
        List<Actor> actores = actorDAO.getAll();
        for (Actor a : actores) {
            System.out.println(a);
        }

        // Buscar actor por ID
        System.out.println("\n-- Actor con ID 1 --");
        Actor actor = actorDAO.get(1);
        System.out.println(actor);

        // Insertar nuevo actor
        Actor nuevoActor = new Actor(0, "Juan", "Perez");
        boolean insertado = actorDAO.post(nuevoActor);
        System.out.println("\n-- Insertar actor: " + (insertado ? "✔ Éxito" : "✘ Falló"));

        actorDAO.closeConnection();

        // ── Probar FilmDAO ─────────────────────────────────────
        System.out.println("\n===== PELÍCULAS =====");
        FilmDAO filmDAO = new FilmDAO();

        List<Film> films = filmDAO.getAll();
        for (Film f : films) {
            System.out.println(f);
        }

        filmDAO.closeConnection();

        // ── Probar CustomerDAO ─────────────────────────────────
        System.out.println("\n===== CLIENTES =====");
        CustomerDAO customerDAO = new CustomerDAO();

        List<Customer> customers = customerDAO.getAll();
        for (Customer c : customers) {
            System.out.println(c);
        }

        customerDAO.closeConnection();

        // ── Probar RentalDAO ───────────────────────────────────
        System.out.println("\n===== RENTAS =====");
        RentalDAO rentalDAO = new RentalDAO();

        Rental rental = rentalDAO.get(1);
        System.out.println(rental);

        rentalDAO.closeConnection();
    }
}