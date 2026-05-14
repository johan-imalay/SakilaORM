package data;

import java.util.regex.Pattern;

/**
 * Clase utilitaria para validar datos con expresiones regulares.

 */
public class Validator {

    // ── Patrones de validación ─────────────────────────────────

    // Cédula dominicana: 3 grupos de 3-7-1 dígitos separados por guiones
    private static final Pattern CEDULA =
            Pattern.compile("^\\d{3}-\\d{7}-\\d{1}$");

    // Teléfono dominicano: (809/829/849)-XXX-XXXX
    private static final Pattern TELEFONO =
            Pattern.compile("^(809|829|849)-\\d{3}-\\d{4}$");

    // Email estándar
    private static final Pattern EMAIL =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    // Fecha formato YYYY-MM-DD
    private static final Pattern FECHA =
            Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");

    // Nombre: solo letras y espacios, mínimo 2 caracteres
    private static final Pattern NOMBRE =
            Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,}$");

    // Código postal: 5 dígitos
    private static final Pattern CODIGO_POSTAL =
            Pattern.compile("^\\d{5}$");

    // Monto: número positivo con hasta 2 decimales
    private static final Pattern MONTO =
            Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    // ── Métodos de validación ──────────────────────────────────

    /**
     * Valida una cédula dominicana.
     * Formato: 000-0000000-0
     * @param cedula cédula a validar
     * @return true si es válida
     */
    public static boolean validarCedula(String cedula) {
        return cedula != null && CEDULA.matcher(cedula).matches();
    }

    /**
     * Valida un teléfono dominicano.
     * Formato: 809-000-0000
     * @param telefono teléfono a validar
     * @return true si es válido
     */
    public static boolean validarTelefono(String telefono) {
        return telefono != null && TELEFONO.matcher(telefono).matches();
    }

    /**
     * Valida un email.
     * Formato: usuario@dominio.com
     * @param email email a validar
     * @return true si es válido
     */
    public static boolean validarEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    /**
     * Valida una fecha.
     * Formato: YYYY-MM-DD
     * @param fecha fecha a validar
     * @return true si es válida
     */
    public static boolean validarFecha(String fecha) {
        return fecha != null && FECHA.matcher(fecha).matches();
    }

    /**
     * Valida un nombre o apellido.
     * Solo letras y espacios, mínimo 2 caracteres.
     * @param nombre nombre a validar
     * @return true si es válido
     */
    public static boolean validarNombre(String nombre) {
        return nombre != null && NOMBRE.matcher(nombre).matches();
    }

    /**
     * Valida un código postal.
     * Formato: 5 dígitos
     * @param codigo código postal a validar
     * @return true si es válido
     */
    public static boolean validarCodigoPostal(String codigo) {
        return codigo != null && CODIGO_POSTAL.matcher(codigo).matches();
    }

    /**
     * Valida un monto.
     * Número positivo con hasta 2 decimales.
     * @param monto monto a validar
     * @return true si es válido
     */
    public static boolean validarMonto(String monto) {
        return monto != null && MONTO.matcher(monto).matches();
    }

    /**
     * Muestra mensaje de error de validación.
     * @param campo campo que falló
     * @param formato formato esperado
     */
    public static void mostrarError(String campo, String formato) {
        System.out.println("✘ " + campo + " inválido. Formato esperado: " + formato);
    }
}