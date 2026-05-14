package Sakila;

import models.*;
import reports.ReportManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import controllers.ActorController;
import controllers.CustomerController;
import controllers.FilmController;
import controllers.InventoryController;
import controllers.PaymentController;
import controllers.RentalController;
import controllers.StoreController;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    private static final ActorController      actorCtrl      = new ActorController();
    private static final FilmController       filmCtrl       = new FilmController();
    private static final CustomerController   customerCtrl   = new CustomerController();
    private static final RentalController     rentalCtrl     = new RentalController();
    private static final StoreController      storeCtrl      = new StoreController();
    private static final InventoryController  inventoryCtrl  = new InventoryController();
    private static final PaymentController    paymentCtrl    = new PaymentController();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     SISTEMA SAKILA ORM           ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Gestión de Actores           ║");
            System.out.println("║  2. Gestión de Películas         ║");
            System.out.println("║  3. Gestión de Clientes          ║");
            System.out.println("║  4. Gestión de Rentas            ║");
            System.out.println("║  5. Gestión de Inventario        ║");
            System.out.println("║  6. Gestión de Tiendas           ║");
            System.out.println("║  7. Gestión de Pagos             ║");
            System.out.println("║  8. Reportes y Estadísticas      ║");
            System.out.println("║  0. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: menuActores();    break;
                case 2: menuFilms();      break;
                case 3: menuClientes();   break;
                case 4: menuRentas();     break;
                case 5: menuInventario(); break;
                case 6: menuTiendas();    break;
                case 7: menuPagos();      break;
                case 8: menuReportes();   break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("✘ Opción inválida.");
            }
        } while (opcion != 0);

        actorCtrl.cerrar();
        filmCtrl.cerrar();
        customerCtrl.cerrar();
        rentalCtrl.cerrar();
        storeCtrl.cerrar();
        inventoryCtrl.cerrar();
        paymentCtrl.cerrar();
    }

    // ── MENÚ ACTORES ──────────────────────────────────────────
    private static void menuActores() {
        int op;
        do {
            System.out.println("\n-- ACTORES --");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: actorCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Actor a = actorCtrl.buscarPorId(sc.nextInt());
                    System.out.println(a != null ? a : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    sc.nextLine();
                    System.out.print("Nombre: ");   String fn = sc.nextLine();
                    System.out.print("Apellido: "); String ln = sc.nextLine();
                    System.out.println(actorCtrl.crear(fn, ln) ? "✔ Creado." : "✘ Error.");
                    break;
                }
                case 4: {
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo nombre: ");   String fn = sc.nextLine();
                    System.out.print("Nuevo apellido: "); String ln = sc.nextLine();
                    System.out.println(actorCtrl.actualizar(id, fn, ln) ? "✔ Actualizado." : "✘ Error.");
                    break;
                }
                case 5: {
                    System.out.print("ID: ");
                    System.out.println(actorCtrl.eliminar(sc.nextInt()) ? "✔ Eliminado." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ PELÍCULAS ────────────────────────────────────────
    private static void menuFilms() {
        int op;
        do {
            System.out.println("\n-- PELÍCULAS --");
            System.out.println("1. Listar todas");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: filmCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Film f = filmCtrl.buscarPorId(sc.nextInt());
                    System.out.println(f != null ? f : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    sc.nextLine();
                    System.out.print("Título: ");      String title = sc.nextLine();
                    System.out.print("Descripción: "); String desc  = sc.nextLine();
                    System.out.print("Año: ");         int year     = Integer.parseInt(sc.nextLine());
                    System.out.print("Rating: ");      String rating = sc.nextLine();
                    System.out.println(filmCtrl.crear(title, desc, year, 1, 3,
                            new BigDecimal("2.99"), 90,
                            new BigDecimal("19.99"), rating)
                            ? "✔ Creada." : "✘ Error.");
                    break;
                }
                case 4: {
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo título: ");      String title  = sc.nextLine();
                    System.out.print("Nueva descripción: "); String desc   = sc.nextLine();
                    System.out.print("Nuevo rating: ");      String rating = sc.nextLine();
                    System.out.println(filmCtrl.actualizar(id, title, desc, rating)
                            ? "✔ Actualizada." : "✘ Error.");
                    break;
                }
                case 5: {
                    System.out.print("ID: ");
                    System.out.println(filmCtrl.eliminar(sc.nextInt()) ? "✔ Eliminada." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ CLIENTES ─────────────────────────────────────────
    private static void menuClientes() {
        int op;
        do {
            System.out.println("\n-- CLIENTES --");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: customerCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Customer c = customerCtrl.buscarPorId(sc.nextInt());
                    System.out.println(c != null ? c : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    sc.nextLine();
                    System.out.print("Nombre: ");   String fn    = sc.nextLine();
                    System.out.print("Apellido: "); String ln    = sc.nextLine();
                    System.out.print("Email: ");    String email = sc.nextLine();
                    System.out.println(customerCtrl.crear(1, fn, ln, email, true)
                            ? "✔ Creado." : "✘ Error.");
                    break;
                }
                case 4: {
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo nombre: ");   String fn    = sc.nextLine();
                    System.out.print("Nuevo apellido: "); String ln    = sc.nextLine();
                    System.out.print("Nuevo email: ");    String email = sc.nextLine();
                    System.out.println(customerCtrl.actualizar(id, fn, ln, email, true)
                            ? "✔ Actualizado." : "✘ Error.");
                    break;
                }
                case 5: {
                    System.out.print("ID: ");
                    System.out.println(customerCtrl.eliminar(sc.nextInt()) ? "✔ Eliminado." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ RENTAS ───────────────────────────────────────────
    private static void menuRentas() {
        int op;
        do {
            System.out.println("\n-- RENTAS --");
            System.out.println("1. Listar todas");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear renta");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: rentalCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Rental r = rentalCtrl.buscarPorId(sc.nextInt());
                    System.out.println(r != null ? r : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    System.out.print("ID Inventario: "); int inv   = sc.nextInt();
                    System.out.print("ID Cliente: ");    int cus   = sc.nextInt();
                    System.out.print("ID Empleado: ");   int staff = sc.nextInt();
                    System.out.println(rentalCtrl.crear(inv, cus, staff)
                            ? "✔ Renta creada." : "✘ Error.");
                    break;
                }
                case 4: {
                    System.out.print("ID Renta: ");
                    System.out.println(rentalCtrl.registrarDevolucion(sc.nextInt())
                            ? "✔ Devolución registrada." : "✘ Error.");
                    break;
                }
                case 5: {
                    System.out.print("ID: ");
                    System.out.println(rentalCtrl.eliminar(sc.nextInt()) ? "✔ Eliminada." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ INVENTARIO ───────────────────────────────────────
    private static void menuInventario() {
        int op;
        do {
            System.out.println("\n-- INVENTARIO --");
            System.out.println("1. Listar todo");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Buscar por Tienda");
            System.out.println("4. Buscar por Película");
            System.out.println("5. Agregar");
            System.out.println("6. Actualizar");
            System.out.println("7. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: inventoryCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Inventory i = inventoryCtrl.buscarPorId(sc.nextInt());
                    System.out.println(i != null ? i : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    System.out.print("ID Tienda: ");
                    inventoryCtrl.buscarPorTienda(sc.nextInt()).forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.print("ID Película: ");
                    inventoryCtrl.buscarPorFilm(sc.nextInt()).forEach(System.out::println);
                    break;
                }
                case 5: {
                    System.out.print("ID Película: "); int filmId  = sc.nextInt();
                    System.out.print("ID Tienda: ");   int storeId = sc.nextInt();
                    System.out.println(inventoryCtrl.crear(filmId, storeId)
                            ? "✔ Agregado." : "✘ Error.");
                    break;
                }
                case 6: {
                    System.out.print("ID Inventario: ");   int id      = sc.nextInt();
                    System.out.print("Nuevo ID Película: "); int filmId  = sc.nextInt();
                    System.out.print("Nuevo ID Tienda: ");   int storeId = sc.nextInt();
                    System.out.println(inventoryCtrl.actualizar(id, filmId, storeId)
                            ? "✔ Actualizado." : "✘ Error.");
                    break;
                }
                case 7: {
                    System.out.print("ID: ");
                    System.out.println(inventoryCtrl.eliminar(sc.nextInt()) ? "✔ Eliminado." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ TIENDAS ──────────────────────────────────────────
    private static void menuTiendas() {
        int op;
        do {
            System.out.println("\n-- TIENDAS --");
            System.out.println("1. Listar todas");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: storeCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Store s = storeCtrl.buscarPorId(sc.nextInt());
                    System.out.println(s != null ? s : "✘ No encontrada.");
                    break;
                }
                case 3: {
                    System.out.print("ID Dirección: ");
                    System.out.println(storeCtrl.crear(sc.nextInt())
                            ? "✔ Creada." : "✘ Error.");
                    break;
                }
                case 4: {
                    System.out.print("ID Tienda: ");     int id        = sc.nextInt();
                    System.out.print("ID Dirección: ");  int addressId = sc.nextInt();
                    System.out.println(storeCtrl.actualizar(id, addressId)
                            ? "✔ Actualizada." : "✘ Error.");
                    break;
                }
                case 5: {
                    System.out.print("ID: ");
                    System.out.println(storeCtrl.eliminar(sc.nextInt()) ? "✔ Eliminada." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ PAGOS ────────────────────────────────────────────
    private static void menuPagos() {
        int op;
        do {
            System.out.println("\n-- PAGOS --");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Buscar por Cliente");
            System.out.println("4. Buscar por Tienda");
            System.out.println("5. Registrar Pago");
            System.out.println("6. Total por Cliente");
            System.out.println("7. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: paymentCtrl.listarTodos().forEach(System.out::println); break;
                case 2: {
                    System.out.print("ID: ");
                    Payment p = paymentCtrl.buscarPorId(sc.nextInt());
                    System.out.println(p != null ? p : "✘ No encontrado.");
                    break;
                }
                case 3: {
                    System.out.print("ID Cliente: ");
                    paymentCtrl.buscarPorCliente(sc.nextInt()).forEach(System.out::println);
                    break;
                }
                case 4: {
                    System.out.print("ID Tienda: ");
                    paymentCtrl.buscarPorTienda(sc.nextInt()).forEach(System.out::println);
                    break;
                }
                case 5: {
                    System.out.print("ID Cliente: ");  int customerId = sc.nextInt();
                    System.out.print("ID Empleado: "); int staffId    = sc.nextInt();
                    System.out.print("ID Renta: ");    int rentalId   = sc.nextInt();
                    System.out.print("Monto: ");       BigDecimal amount = sc.nextBigDecimal();
                    System.out.println(paymentCtrl.crear(customerId, staffId,
                            rentalId, amount)
                            ? "✔ Pago registrado." : "✘ Error.");
                    break;
                }
                case 6: {
                    System.out.print("ID Cliente: ");
                    double total = paymentCtrl.totalPorCliente(sc.nextInt());
                    System.out.printf("Total pagado: $%.2f%n", total);
                    break;
                }
                case 7: {
                    System.out.print("ID: ");
                    System.out.println(paymentCtrl.eliminar(sc.nextInt()) ? "✔ Eliminado." : "✘ Error.");
                    break;
                }
            }
        } while (op != 0);
    }

    // ── MENÚ REPORTES ─────────────────────────────────────────
    private static void menuReportes() {
        ReportManager rm = new ReportManager();
        int op;
        do {
            System.out.println("\n-- REPORTES --");
            System.out.println("1. Exportar actores CSV");
            System.out.println("2. Exportar películas JSON");
            System.out.println("3. Estadísticas de películas");
            System.out.println("4. Estadísticas de rentas");
            System.out.println("5. Estadísticas de clientes");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1: rm.exportarActoresCSV();      break;
                case 2: rm.exportarFilmsJSON();       break;
                case 3: rm.estadisticasFilms();       break;
                case 4: rm.estadisticasRentas();      break;
                case 5: rm.estadisticasClientes();    break;
            }
        } while (op != 0);
        rm.cerrar();
    }
}