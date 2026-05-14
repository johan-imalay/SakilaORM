package data;

import java.sql.*;

/**
 * Clase abstracta padre de todos los DAOs.
 * Los hijos NO pueden sobrescribir los métodos finales.

 */
public abstract class DataContext<T> implements iDatapost<T> {

    private static final String URL  = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "mysql";

    protected Connection connection;

    /**
     * Constructor: abre la conexión a Sakila DB.
     */
    public DataContext() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✔ Conexión exitosa a Sakila DB");
        } catch (SQLException e) {
            System.err.println("✘ Error de conexión: " + e.getMessage());
        }
    }

    /**
     * Ejecuta INSERT, UPDATE o DELETE.
     * @param sql    sentencia SQL con ?
     * @param params valores en orden
     * @return true si afectó al menos 1 fila
     */
    protected final boolean executeUpdate(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setParams(ps, params);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("✘ Error executeUpdate: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ejecuta un SELECT y devuelve el ResultSet.
     * @param sql    sentencia SQL con ?
     * @param params valores en orden
     * @return ResultSet con los resultados
     */
    protected final ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            setParams(ps, params);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("✘ Error executeQuery: " + e.getMessage());
            return null;
        }
    }

    /**
     * Verifica si la conexión está activa.
     * @return true si está conectado
     */
    public final boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public final void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✔ Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("✘ Error cerrando conexión: " + e.getMessage());
        }
    }

    // ── Privado: asigna parámetros al PreparedStatement ───────
    private void setParams(PreparedStatement ps, Object... params)
            throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }
}