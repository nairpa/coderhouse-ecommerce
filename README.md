# Coderhouse Ecommerce
Proyecto realizado para el curso de coderhose de Java con Spring Boot. Conforma un API REST para la creación para un ecommerce.

# Caracteristicas principales
* Registro y autenticación de usuarios con JWT.
* ABM de productos.
* ABM de carrito.
* Alta y efecitivización de ordenes de compra.

# Tecnologías
* Java 11
* Spring boot
* Config server
* JWT
* MongoDB

# Estructura del proyecto
* Controllers: Controladores de la API.
* Exceptions: Manejo de excepciones.
* Filters
* Models: Entidades del proyecto separadas por documentos, responses y requests.
* Repository: Repositorios Mongo de las entidades.
* Security: Configuración de la seguridad con Spring security y JWT.
* Services: Servicios con la lógica de negocio.

# Configuración
Configuración en ```application.properties```:
```coderhouse.app.jwtSecret``` clave secreta que emplea JWT para la conformación del token.
```coderhouse.app.jwtExpirationMs``` tiempo de expiración del token creado por JWT.
Configuración tomada del config server en ```https://github.com/nairpa/config-coderhouse-ecommerce/blob/main/config-main.yml```
Repositorio del config server ```https://github.com/nairpa/config-server```