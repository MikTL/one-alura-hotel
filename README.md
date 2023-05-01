# ONE ALURA HOTEL

One Alura Hotel es una aplicación en Java para la gestión de un hotel, donde el personal autorizado podrá registrar huéspedes, asignar habitaciones, consultar disponibilidad, editar y eliminar los datos registrados en la Base de Datos. La aplicación se conecta a una base de datos MySQL mediante JDBC para almacenar y recuperar la información del hotel y sus clientes. Este proyecto es un challenge de Backend para el programa ONE de Oracle.

![ONE](https://www.oracle.com/a/ocom/img/rh03-one-v-black-lad.png)
![ONE](https://avatars.githubusercontent.com/u/4975968?s=280&v=4)

## Requisitos

Para ejecutar la aplicación se necesita:

- Java 8 o superior
- MySQL 8.0 o superior
- Un IDE compatible con Java, como Eclipse o NetBeans
- Configurar el buildPath con las librerias que estan la carpeta JARS

## Instalación

Para instalar la aplicación se deben seguir los siguientes pasos:

1. Clonar el repositorio de GitHub o descargar el código fuente como un archivo ZIP.
2. Importar el proyecto en el IDE de su preferencia.
3. Crear una base de datos MySQL siguiendo [estos pasos](./resources/database.md).
4. Ejecutar la clase `MenuPrincipal.java` que se encuentra en el paquete ` com.hotel_alura.views`.

## Uso

La aplicación tiene una interfaz gráfica que permite realizar las siguientes operaciones:

- Iniciar sesión con un usuario y contraseña válidos. Estos usuarios y contraseña deben estar en la tabla users de la BD.
- Registrar la fecha de entrada y salida del huesped, el costo se calculará automaticamente dependiendo del número de dias que se quedarán en el hotel.
- Elegir el tipo de pago:
  1. Tarjeta de Crédito.
  2. Tarjeta de Débito.
  3. Dinero en Efectivo.
- El id de la reserva se pone automaticamente (Este sería equivalente al número de habitación de un piso en específico, un por implementar).
- Registrar los datos personales del huesped y asignandole el id de la reserva como habitacion automaticamente.
- Visualizar todos las reservas y los huespedes.
- Buscar las reservas por el ID y a los huespedes por el apellido.
- Editar y borrar registros.

## Tecnologías utilizadas

- Java: lenguaje de programación utilizado para desarrollar la lógica de la aplicación y la interfaz gráfica.
- MySQL: sistema de gestión de bases de datos relacionales utilizado para almacenar y consultar la información del hotel y sus clientes.
- JDBC: API de Java que permite la conexión y comunicación con bases de datos como MySQL.
- Swing: biblioteca gráfica de Java que permite crear componentes para interfaces gráficas de usuario.

<div align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="50" width="50" alt="java logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="50" width="50" alt="mysql logo"  />
  <img src="https://www.oracle.com/a/ocom/img/jdbc.svg" height="70" width="70" alt="JDBC logo"  />
  <img src="https://4.bp.blogspot.com/-HFPxbS028bc/WbmoWbftOrI/AAAAAAAABOg/4OBei-kLpBk9Z5C92Qw6Pc6UzVzY7RLEwCLcBGAs/s1600/SwingUtils%2B-%2BLibrer%25C3%25ADa%2Bde%2BUtilidades%2Bpara%2BJava%2BSwing.png" height="50" width="50" alt="SWING JAVA"/>
</div>

## Colaboración

Este proyecto es de código abierto y se aceptan sugerencias y contribuciones. Para colaborar, puede:

- Reportar problemas o errores en la sección de Issues del repositorio.
- Proponer mejoras o nuevas funcionalidades en la sección de Pull requests del repositorio.