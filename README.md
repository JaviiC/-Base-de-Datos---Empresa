# Gestión de Empleados y Oficinas

## Descripción

Este proyecto es una aplicación de escritorio en Java que permite gestionar empleados y oficinas de una empresa utilizando una interfaz gráfica. La aplicación se conecta a una base de datos para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre empleados y oficinas.

## Características

- **Gestión de Empleados**: Permite contratar, despedir y listar empleados.
- **Gestión de Oficinas**: Permite añadir, trasladar y listar oficinas.
- **Consultas**: Permite realizar consultas específicas como buscar empleados por edad o por oficina.
- **Interfaz Gráfica**: Proporciona una interfaz gráfica básica para la interacción con el usuario.

## Requisitos

- **Java 8** o superior.
- Una base de datos configurada con las tablas necesarias para almacenar empleados y oficinas.
- Librerías de JDBC para la conexión con la base de datos.

## Estructura del Proyecto

El proyecto está organizado en varios paquetes y clases:

- **`EMPRESA`**: Contiene las clases principales del proyecto.
  - **`AppDemo`**: La clase principal que configura la interfaz gráfica y gestiona los eventos de usuario.
  - **`ResultWindow`**: Una clase auxiliar para mostrar resultados en una nueva ventana.
  - **`EmpleadosDAO`**: Clase DAO para realizar operaciones CRUD sobre la tabla de empleados.
  - **`OficinasDAO`**: Clase DAO para realizar operaciones CRUD sobre la tabla de oficinas.

## Clases y Funcionalidades

### `AppDemo`

Esta clase extiende `JFrame` y configura la interfaz gráfica. Contiene varios componentes y listeners para gestionar las acciones del usuario.

#### Componentes Principales

- **JTextField**: Para la entrada de datos (ID, nombre, edad, etc.).
- **JButton**: Para realizar acciones (contratar, despedir, buscar, etc.).
- **JPanel**: Para organizar los componentes en la interfaz.

### `ResultWindow`

Esta clase extiende `JFrame` y proporciona una ventana para mostrar resultados en un `JTextArea` con un `JScrollPane`.

## Cómo Ejecutar el Proyecto

1. Clona el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/tu-repositorio.git
2. Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA).
3. Configura la conexión a la base de datos en las clases DAO (EmpleadosDAO y OficinasDAO).
4. Ejecuta la clase AppDemo.
## Contribuir
Las contribuciones son bienvenidas. Por favor, sigue los pasos a continuación para contribuir:

1. Haz un fork del repositorio.
2. Crea una rama nueva (git checkout -b feature/nueva-caracteristica).
3. Realiza tus cambios y haz commit (git commit -am 'Añadir nueva característica').
4. Envía los cambios a tu rama (git push origin feature/nueva-caracteristica).
5. Abre un Pull Request.
## Licencia
Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo LICENSE.

Puedes copiar este contenido y pegarlo en tu archivo `README.md` en GitHub. Este resumen explica el propósito del proyecto, sus características principales, los requisitos necesarios para ejecutarlo, la estructura del proyecto, y cómo contribuir.
