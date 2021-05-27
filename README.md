"# ClimaAPI" 


La siguiente API Rest corresponde al ejercicio de los planetas.

Resumidamente, la misma dispone de cuatro endpoints.

1) 
(GET)
/api/healthcheck

Es un punto de control rapido para saber que el API esta levantado correctamente.

2)
(GET)
/api/clima?dia={valor}

Retorna el clima de un día (siendo el 0 el primero) dado. 
Requiere un parametro para indicar el valor del dia.

3)
(POST)
/api/clima/calcularDiezAnos

Ejecuta el job encargado de poblar la base de datos con los registros de diez años de pronosticos.
La DB es un postgresql. Se incluye un docker-compose para levantarla en un ambiente local. Adicionalmente, se deben cambiar las credenciales ya que quedo pendiente la generación de profiles.
El endpoint podría ser tranquilamente un GET, pero para seguir una convención donde genera datos, decidí utilizar un POST.
Aclaración: En cada ejecución, para controlar la cantidad de datos, el endpoint realiza un delete a la tabla antes de volver a poblarla.
4)
(GET)
/api/clima/resultadosDiezAnos

Retorna los resultados almacenados en la base de datos, con el pronóstico de diez años (3650 dias).



Algunas aclaraciones:

1) En el endpoint que retorna el clima de un día solicitado, se generó un DTO para poder cumplir con el formato de la consigna. Los primeros desarrollos retornaban la entidad, pero el id no era requerido.
2) En el metodo que genera los pronosticos de diez años, así como en los que recuperan dicha información, se intento ser lo mas prolijo en cuanto a sintaxis respecta, pero en algun que otro lado, es posible que amerite algun refactor para eliminar algun for, etc.
3) Algunas lógicas como la del cálculo de determinación de un punto y su posición respecto al triangulo formado por las posiciones de las 3 órbitas, fueron investigadas y obtenidas de internet.
4) Si bien no se realizó TDD, incluyo algunos tests, pero como TO-DO podrían agregarse muchos más.
5) Se manejan entidades mediante JPA y se agrego al Repository un metodo para permitir la búsqueda por día.
6) En la DB se almacenan registros del tipo del response del API requerido, por eso pueden visualizarse muchas conversiones desde lo planteado para calcular los pronosticos hasta ellos mismos.
7) Tanto el API Rest como la db no local, se hostearon en AWS, con los servicios correspondientes para cada uno.
8) Se agrego un Swagger para obtener una documentación básica del API.
9) Por temas de tiempo, se realizó en Java, pero la idea inicial era implementarlo en Go.

Desde ya muchas gracias, quedo atento.

Saludos cordiales, Martín



Swagger -> http://ec2-18-117-10-53.us-east-2.compute.amazonaws.com:8080/swagger-ui.html