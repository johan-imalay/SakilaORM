package data;

import java.util.List;

/**
 * Interfaz estándar CRUD para todos los modelos de Sakila.
 * @param <T> Tipo del modelo
 */
public interface iDatapost<T> {

    boolean post(T entity);

    boolean put(T entity);

    boolean delete(int id);

    T get(int id);

    List<T> getAll();
}