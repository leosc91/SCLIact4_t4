# API REST
Elaborado por Salinas Cenobio Leonel Isaac.

Materia: Programación Web.

## ¿Qué es?
El presente proyecto es un sistema CRUD (Create, Read, Update, Delete) basado en videojuegos, con dos tablas principales; videojuegos y estudios, con la implementación de seguridad al momento de realizar una petiición HTTP.

Cuenta con diferentes tipos de seguridad:

1. Registro: para poder realizar una petición es necesario crear un usuario y contraseña gratuito que brindará el token para poder efectuar las peticiones correctamente.
2. Login: una vez realizado el registro, es necesario hacer login para obtener el token de autenticación para las peticiones HTTP.
3. Petición: el token obtenido se autenticará como **Bearer Token** para realizar una petición HTTP.

## Capturas

1. Creación de usuario.

Para emplear la API es necesario crear una cuenta sencilla con el método POST, con los parámetros **username** y **password** como se muestra en la imagen.

<img width="1152" height="607" alt="image" src="https://github.com/user-attachments/assets/7fb70840-3b94-4fc3-81cf-b200de10b254" />

2. Obtención de token al iniciar sesión.

Una vez creada la cuenta, con los datos ingresados en el campo **username** y **password** se realiza un método POST para obtener el token de uso.

<img width="1156" height="608" alt="image" src="https://github.com/user-attachments/assets/e0aa7ed7-c6fa-4204-a8db-143f2a5916af" />

3. Realización de una petición GET sin token de autenticación para leer las filas y columnas.

Demostración de que no se puede acceder a las filas sin un token.

<img width="1148" height="603" alt="image" src="https://github.com/user-attachments/assets/cee5830a-6e8b-4f54-a99e-5639e04cd890" />

4. Realización de una petición GET con token de autenticación para leer las filas y columnas.

Una vez que obtener el token lo copiamos y pegamos en la parte de Auth como Bearer Token y mandamos una petición GET para leer el contenido.

<img width="1156" height="608" alt="image" src="https://github.com/user-attachments/assets/cf2162bf-0ae5-4de9-aaff-1e3b6a703ecc" />

5. Realización de una petición POST para crear una fila.

Con el mismo token volvemos a verificar en el apartado Auth como Bearer Token para realizar una petición POST para crear una fila como se indica en la imagen. Para rellenar el campo estudioId se selecciona en un rango de 1 a 13 (Estudios desarrolladores guardados en la base de datos debido a que se encuentra implementada la relación Muchos a Uno (@ManyToOne) desde la tabla Videojuegos hacia Estudios).

<img width="1153" height="602" alt="image" src="https://github.com/user-attachments/assets/12b5cd56-8fbe-4148-8343-91069e538342" />

6. Realización de una petición PUT para actualizar una fila.

Con el mismo token, y con la estructura indicada en la imagen, podemos modificar la columna deseada, si se desea modificar estudioId sucedería lo mismo que con anterioridad, el rango de estudios desarrolladores es 1 a 13. Así mismo en la parte final de la URL se especifíca la fila deseada a modificar.

<img width="1152" height="605" alt="image" src="https://github.com/user-attachments/assets/ae83b7ee-f142-4947-9ccd-47ed98f74220" />

7. Realización de una petición DELETE para borrar una fila.

Empleado el mismo token usamos la instrucción DELETE especificando en la parte final de la url la fila deseada a eliminar.

<img width="1155" height="605" alt="image" src="https://github.com/user-attachments/assets/1268bd1b-e153-407d-ace5-1b5c51506060" />

## Endpoints
| Método   | URL                                            | Descripción                                         |
| :------- | :--------------------------------------------- | :-------------------------------------------------- |
| `POST`   | `http://165.22.6.118:8090/api/auth/register`   | Registro con nombre de usuario y contraseña         |
| `POST`   | `http://165.22.6.118:8090/api/auth/login`      | Inicio de sesión con nombre de usuario y contraseña |
| `GET`    | `http://165.22.6.118:8090/api/videojuegos`     | Leer filas y columnas                               |
| `POST`   | `http://165.22.6.118:8090/api/videojuegos`     | Añadir fila                                         |
| `PUT`    | `http://165.22.6.118:8090/api/videojuegos/19`  | Modificar fila (Ej. 19)                             |
| `DELETE` | `http://165.22.6.118:8090/api/videojuegos/25`  | Eliminar fila (Ej. 25)                              |
