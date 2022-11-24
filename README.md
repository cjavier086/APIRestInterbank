# APIRestInterbank
Prueba técnica de Interbank

Se crearon 3 API REST

1) loadCliente: Servicio que carga los clientes a la base de datos H2
2) findClienteByCodigo: Servicio que busca la información del cliente por el código como parámetro
3) updateClient: Servicio que actualiza la información del cliente por el código unico

URL para los servicios

1) loadCliente: http://localhost:8080/interbank/clientes (GET)
2) findClienteByCodigo: http://localhost:8080/interbank/clientes/123 (GET)
3) updateClient: http://localhost:8080/interbank/clientes/123 (POST)

URL de imagen Docker

https://hub.docker.com/r/cjavier256/testimage
docker pull cjavier256/testimage
