API Pedido Caso Bookpoint
SpringBoot + JPA + MYSQL

Query para crear Base de Datos requerida:
CREATE DATABASE pedido_db

Endpoints de los metodos existentes

Factura:
Crear:
POST http://localhost:8088/api/v1/facturacion/facturas
{
  "ventaId": 1,
  "usuarioId": 10
}

Buscar factura por id:
GET http://localhost:8088/api/v1/facturacion/facturas/{id}

Buscar por id de usuario:
GET http://localhost:8088/api/v1/facturacion/facturas/usuario/{usuarioId}

Listar facturas:
GET http://localhost:8088/api/v1/facturacion/facturas

Boleta:
Crear:
POST http://localhost:8088/api/v1/facturacion/boletas
{
  "ventaId": 2,
  "rutEmisor": "9.306.474-7"
}

Buscar boleta por id:
GET http://localhost:8088/api/v1/facturacion/boletas/{id}

Buscar por id de usuario:
GET http://localhost:8088/api/v1/facturacion/boletas/usuario/{usuarioId}

Listar facturas:
GET http://localhost:8088/api/v1/facturacion/boletas

Pasos a seguir:
-Crear base de datos con el query
-Correr la aplicacion
-Crear productos