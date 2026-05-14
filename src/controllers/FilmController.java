package controllers;

import data.FilmDAO;
import models.Film;
import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador MVC para gestionar las películas.
///Johan Manuel Feliz Montero 100146608
 */
public class FilmController {

    private final FilmDAO filmDAO;

    public FilmController() {
        this.filmDAO = new FilmDAO();
    }

    /**
     * Retorna todas las películas.
     * @return Lista de películas
     */
    public List<Film> listarTodos() {
        return filmDAO.getAll();
    }

    /**
     * Busca una película por ID.
     * @param id ID de la película
     * @return objeto Film o null
     */
    public Film buscarPorId(int id) {
        return filmDAO.get(id);
    }

    /**
     * Crea una nueva película.
     * @param title           título
     * @param description     descripción
     * @param releaseYear     año de lanzamiento
     * @param languageId      ID del idioma
     * @param rentalDuration  duración de renta en días
     * @param rentalRate      precio de renta
     * @param length          duración en minutos
     * @param replacementCost costo de reposición
     * @param rating          clasificación
     * @return true si se creó correctamente
     */
    public boolean crear(String title, String description, int releaseYear,
                         int languageId, int rentalDuration, BigDecimal rentalRate,
                         int length, BigDecimal replacementCost, String rating) {
        if (title == null || title.isEmpty()) {
            System.out.println("✘ El título es obligatorio.");
            return false;
        }
        Film film = new Film(0, title, description, releaseYear, languageId,
                rentalDuration, rentalRate, length, replacementCost, rating);
        return filmDAO.post(film);
    }

    /**
     * Actualiza una película existente.
     * @param id ID de la película a actualizar
     * @param title nuevo título
     * @param description nueva descripción
     * @param rating nueva clasificación
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(int id, String title, String description, String rating) {
        Film film = filmDAO.get(id);
        if (film == null) {
            System.out.println("✘ Película no encontrada.");
            return false;
        }
        film.setTitle(title);
        film.setDescription(description);
        film.setRating(rating);
        return filmDAO.put(film);
    }

    /**
     * Elimina una película por ID.
     * @param id ID de la película
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Film film = filmDAO.get(id);
        if (film == null) {
            System.out.println("✘ Película no encontrada.");
            return false;
        }
        return filmDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        filmDAO.closeConnection();
    }
}