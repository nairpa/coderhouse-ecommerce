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
Configuración tomada del config server en [config file](https://github.com/nairpa/config-coderhouse-ecommerce/blob/main/config-main.yml)
Repositorio del config server [config server](https://github.com/nairpa/config-server)
Colección en postman del proyecto [colección postman](https://www.postman.com/cryosat-administrator-52756468/workspace/coderhouse/collection/15586413-a187de2f-9ce8-4b22-a5bd-2fcba02c0f3a?action=share&creator=15586413), recordar settear environment en coderhouse.