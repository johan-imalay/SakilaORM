package controllers;

import data.ActorDAO;
import models.Actor;
import java.util.List;

/**
 * Controlador MVC para gestionar los actores.
 * ///Johan Manuel Feliz Montero 100146608
 */
public class ActorController {

    private final ActorDAO actorDAO;

    public ActorController() {
        this.actorDAO = new ActorDAO();
    }

    /**
     * Retorna todos los actores.
     * @return Lista de actores
     */
    public List<Actor> listarTodos() {
        return actorDAO.getAll();
    }

    /**
     * Busca un actor por ID.
     * @param id ID del actor
     * @return objeto Actor o null
     */
    public Actor buscarPorId(int id) {
        return actorDAO.get(id);
    }

    /**
     * Crea un nuevo actor.
     * @param firstName nombre
     * @param lastName  apellido
     * @return true si se creó correctamente
     */
    public boolean crear(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() ||
                lastName  == null || lastName.isEmpty()) {
            System.out.println("✘ Nombre y apellido son obligatorios.");
            return false;
        }
        Actor actor = new Actor(0, firstName, lastName);
        return actorDAO.post(actor);
    }

    /**
     * Actualiza un actor existente.
     * @param id        ID del actor
     * @param firstName nuevo nombre
     * @param lastName  nuevo apellido
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(int id, String firstName, String lastName) {
        Actor actor = actorDAO.get(id);
        if (actor == null) {
            System.out.println("✘ Actor no encontrado.");
            return false;
        }
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        return actorDAO.put(actor);
    }

    /**
     * Elimina un actor por ID.
     * @param id ID del actor
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        Actor actor = actorDAO.get(id);
        if (actor == null) {
            System.out.println("✘ Actor no encontrado.");
            return false;
        }
        return actorDAO.delete(id);
    }

    /**
     * Cierra la conexión del DAO.
     */
    public void cerrar() {
        actorDAO.closeConnection();
    }
}