SakilaORM — Proyecto Final INF514
Universidad Autónoma de Santo Domingo (UASD)
Facultad de Ciencias, Escuela de Informática
Curso: INF514 - Programación II Java | Sección: Z06
Profesor: Silverio Del Orbe
 Descripción
Sistema ORM (Object Relational Mapping) desarrollado en Java para gestionar la base de datos Sakila de MySQL. Implementa el patrón de diseño MVC con operaciones CRUD completas para cada tabla de la base de datos.
 Arquitectura del Proyecto
com.sakila.data
	iDatapost.java        → Interfaz estándar CRUD
	DataContext.java       → Clase abstracta padre (híbrida)
	Validator.java         → Validaciones con expresiones regulares
	ActorDAO.java          → CRUD tabla actor
	FilmDAO.java           → CRUD tabla film
	CustomerDAO.java       → CRUD tabla customer
	RentalDAO.java         → CRUD tabla rental
	PaymentDAO.java        → CRUD tabla payment
	InventoryDAO.java      → CRUD tabla inventory
	StoreDAO.java          → CRUD tabla store
	StaffDAO.java          → CRUD tabla staff
	AddressDAO.java        → CRUD tabla address
	CityDAO.java           → CRUD tabla city
	CountryDAO.java        → CRUD tabla country
com.sakila.models
	Actor.java
	Film.java
	Customer.java
	Rental.java
	Payment.java
	Inventory.java
	Store.java
	Staff.java
	Address.java
	City.java
	Country.java
com.sakila.controllers
	ActorController.java
	FilmController.java
	CustomerController.java
	RentalController.java
	InventoryController.java
	StoreController.java
	PaymentController.java
com.sakila.reports
	ReportManager.java     → Reportes y estadísticas
✅ Requerimientos Implementados
1. Interfaz iDatapost
Define el estándar CRUD con los métodos:
	post(T entity)  → INSERT
	put(T entity)   → UPDATE
	delete(int id)  → DELETE
	get(int id)     → SELECT por ID
	getAll()        → SELECT todos
2. Clase Abstracta DataContext
	Padre híbrido con métodos final (no sobreescribibles)
	Gestiona la conexión JDBC a MySQL
	Métodos: executeUpdate(), executeQuery(), isConnected(), closeConnection()
3. DAOs — Hijos concretos y finales
	Un DAO por cada tabla de Sakila
	Las Foreign Keys se gestionan como composición de objetos
	Ejemplo: City contiene un objeto Country, Address contiene un objeto City
4. Controladores MVC
	Un controlador por cada entidad del modelo
	Gestiona la lógica entre los DAOs y la interfaz de usuario
	Validaciones de negocio antes de ejecutar operaciones
5. Interfaz de Usuario (Consola)
Menú interactivo con gestión CRUD para:
	Actores
	Películas
	Clientes
	Rentas
	Inventario
	Tiendas
	Pagos
6. Reportes y Estadísticas
	Exportar actores a CSV
	Exportar películas a JSON
	Estadísticas de películas (total, precio promedio, por clasificación)
	Estadísticas de rentas (total, pendientes, cliente más activo)
	Estadísticas de clientes (total, activos, inactivos)
7. Validaciones con Expresiones Regulares
	Cédula dominicana: 000-0000000-0
	Teléfono dominicano: 809/829/849-000-0000
	Email: usuario@dominio.com
	Fecha: YYYY-MM-DD
	Nombre: solo letras, mínimo 2 caracteres
	Monto: número positivo con hasta 2 decimales
8. Colecciones Genéricas
	ArrayList<T> para listados de entidades
	HashMap<K,V> para estadísticas y búsquedas agrupadas
 Tecnologías Utilizadas
	Java 11+
	MySQL 8.0+
	MySQL Connector/J 9.0.33
	Sakila Database
	Eclipse IDEA 2025
⚙️ Configuración
Prerrequisitos
	Tener instalado MySQL 8.0+ con la base de datos Sakila
	Tener instalado Java 11+
Configurar la conexión
En el archivo DataContext.java cambia las credenciales:
private static final String URL  = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static final String USER = "root";
    private static final String PASS = "mysql";
Autor
Nombre: Johan Manuel Feliz Montero
Matrícula: 100146608
Email:johan.m.feliz@gmail.com
GitHub: https://github.com/johan-imalay/SakilaORM
Video: https://youtu.be/Y0SsAIjmEnM 
 Fecha de Entrega
15 de Mayo de 2026
──────────────────────────────────────────────────────────────────────
2026 © Universidad Autónoma de Santo Domingo — Santo Domingo, Rep. Dom.

