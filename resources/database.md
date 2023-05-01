# Base de Datos del Hotel en MySQL.

1. Crear la base de datos.
    ```sql
    CREATE DATABASE  IF NOT EXISTS `alura_hotel`;
    ```
2. Seleccionarla:
    ```sql
      USE `alura_hotel`;
    ```
3. Crear la tabla de los usuarios (Colaboradores del Hotel):
    ```sql
      CREATE TABLE `users` (
      `id` int NOT NULL,
      `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
      `last_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
      `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `user_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
      `password` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
      PRIMARY KEY (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```



4. Crear la tabla de las reservas:
    ```sql
      CREATE TABLE `bookings` (
      `id_bookings` int NOT NULL AUTO_INCREMENT,
      `entry_date` date DEFAULT NULL,
      `exit_date` date DEFAULT NULL,
      `value` decimal(10,2) DEFAULT NULL,
      `payment_method` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      PRIMARY KEY (`id_bookings`)
      ) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```

5. Crear la tabla de los huespedes: En esta tabla se añadió la opción: ```DELETE ON CASCADE``` . De esta manera, cuando se elimina un registro de la tabla **bookings**, el registro de la tabla guests que esté relacionado con el registro eliminado también se eliminarán automáticamente, es obvio.
    ```sql
      CREATE TABLE `guests` (
      `id_guest` int NOT NULL AUTO_INCREMENT,
      `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `date_birth` date DEFAULT NULL,
      `nationality` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `id_booking` int DEFAULT NULL,
      KEY `idx_guests_id_guest` (`id_guest`),
      KEY `fk_id_bookings_idx` (`id_booking`),
      CONSTRAINT `fk_id_bookings` FOREIGN KEY (`id_booking`) REFERENCES `bookings` (`id_bookings`) ON DELETE CASCADE
      ) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```
6. Por ultimo, creamos un disparador que se activará cuando se elimina una fila en la tabla guests y que elimina todas las filas relacionadas en la tabla bookings que tienen la clave foránea correspondiente, esto hará que se borre la reserva automáticamente cuando borramos un Huesped.
    ```sql
      DELIMITER
      CREATE TRIGGER delete_bookings_on_guest_delete
      AFTER DELETE ON guests
      FOR EACH ROW
      BEGIN
        DELETE FROM bookings WHERE id_bookings = OLD.id_booking;
      END
      DELIMITER ;
    ```
